import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Utils} from '../util/utils';
import { JwtHelperService } from '@auth0/angular-jwt';
import {environment} from '../../environments/environment';

@Injectable()
export class AuthenticationService {
  private helper = new JwtHelperService();

  private LOGIN_URL = environment.apiUrl + 'auth/login';
  private REGISTRATION_URL = environment.apiUrl + 'auth/register';
  private ACTIVATION_RESEND_URL = environment.apiUrl + 'auth/resend-activation';
  private FORGOT_PASSWORD_URL = environment.apiUrl + 'auth/forgot-password';
  private ACTIVATE_URL = environment.apiUrl + 'auth/activate/';
  private RESET_PASSWORD_URL = environment.apiUrl + 'auth/reset-password/';
  private CANCEL_RESET_PASSWORD_URL = environment.apiUrl + 'auth/cancel-reset-password/';

  private headers = {headers: new HttpHeaders({'Content-Type': 'application/json'})};
  public token: string;

  constructor(private http: HttpClient, private utils: Utils) {
  }

  login(data): Promise<any> {
    return this.utils.checkStatus(this.http.post(this.LOGIN_URL, data, this.headers)
      .toPromise()
      .then((res) => {
        const status = (<any>res).status;
        if (status.status === 'SUCCESS') {
          localStorage.setItem('jwt', JSON.stringify({token: (<any>res).jwt}));
          return Promise.resolve(status);
        }
        return Promise.resolve(status);
      })
      .catch(err => {
        const status = (<any>err).status;
        if (status === 'FAILURE') {
          return Promise.resolve(status);
        }
        return Promise.resolve(err);
      }));
  }

  register(data): Promise<any> {
    return this.utils.checkStatus(this.http.post(this.REGISTRATION_URL, data, this.headers).toPromise());
  }

  isLoggedIn(): boolean {
    const token = this.getToken();
    if (token === '') {
      return false;
    }
    return !this.helper.isTokenExpired(token);
  }

  getToken(): string {
    const jwt = JSON.parse(localStorage.getItem('jwt'));
    const token = jwt && jwt.token;
    return token ? token : '';
  }

  logout(): void {
    // clear token remove user from local storage to log user out
    localStorage.removeItem('jwt');
    localStorage.removeItem('user');
    this.token = '';
  }

  activationResend(email: string): Promise<any> {
    const data = {email: email};
    return this.utils.checkStatus(this.http.post(this.ACTIVATION_RESEND_URL, data, this.headers).toPromise());
  }

  forgotPassword(email: string): Promise<any> {
    const data = {email: email};
    return this.utils.checkStatus(this.http.post(this.FORGOT_PASSWORD_URL, data, this.headers).toPromise());
  }

  verifyActivationToken(token: string): Promise<boolean> {
    return this.utils.checkStatus(this.http.get(this.ACTIVATE_URL + token, this.headers).toPromise());
  }

  disablePasswordResetToken(token: string): Promise<boolean> {
    return this.utils.checkStatus(this.http.get(this.CANCEL_RESET_PASSWORD_URL + token, this.headers).toPromise());
  }

  resetPassword(token: string, password: string, passwordConfirmation: string): Promise<boolean> {
    const data = {
      'token': token,
      'password': password,
      'passwordConfirmation': passwordConfirmation
    };

    return this.utils.checkStatus(this.http.post(this.RESET_PASSWORD_URL, data, this.headers).toPromise());
  }
}
