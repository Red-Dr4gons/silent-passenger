import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {AuthenticationService} from "../authentication/authentication.service";

@Injectable({
  providedIn: 'root'
})
export class DeliveryService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient, private authService: AuthenticationService) {
  }

  getDeliveries(): Promise<any> {
    const url = `${this.apiUrl}delivery`;
    return this.http
      .get(url, this.authService.getHeaders())
      .toPromise();
  }

  create(data: any): Promise<any> {
    const url = `${this.apiUrl}delivery`;
    console.log(url)
    return this.http
      .post(url, data, this.authService.getHeaders())
      .toPromise();
  }
}
