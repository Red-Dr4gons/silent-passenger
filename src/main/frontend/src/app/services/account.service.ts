import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthenticationService} from "../authentication/authentication.service";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private ACCOUNT_URL = environment.apiUrl + 'account';

  constructor(private http: HttpClient, private authService: AuthenticationService) {
  }

  getMyInfo(): Promise<any> {
    return this.http.get(this.ACCOUNT_URL, this.authService.getHeaders()).toPromise();
  }

  update(data: any): Promise<any> {
    return this.http.put(this.ACCOUNT_URL, data, this.authService.getHeaders()).toPromise();
  }
}
