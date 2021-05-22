import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {JwtHelperService} from '@auth0/angular-jwt';
import {environment} from '../../environments/environment';

@Injectable()
export class AuthenticationService {
  private helper = new JwtHelperService();

  private LOGIN_URL = environment.apiUrl + 'login';
  private REGISTRATION_URL = environment.apiUrl + 'register';

  private headers = {headers: new HttpHeaders({'Content-Type': 'application/json'})};
  private token: string | undefined;

  constructor(private http: HttpClient) {
  }

  login(data: any): Promise<any> {
    return this.http.post(this.LOGIN_URL, data, this.headers).toPromise();
  }

  register(data: any): Promise<any> {
    return this.http.post(this.REGISTRATION_URL, data, this.headers).toPromise();
  }

  isLoggedIn(): boolean {
    const token = this.getToken();
    if (token === '') {
      return false;
    }
    return !this.helper.isTokenExpired(token);
  }

  getToken(): string {
    const jwt = localStorage.getItem('jwt');
    return jwt ? jwt : '';
  }

  logout(): void {
    // clear token remove user from local storage to log user out
    localStorage.removeItem('jwt');
    this.token = '';
  }
}
