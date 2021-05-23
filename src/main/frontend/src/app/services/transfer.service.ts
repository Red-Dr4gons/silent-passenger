import {Injectable} from "@angular/core";
import {environment} from "../../environments/environment";
import {AuthenticationService} from "../authentication/authentication.service";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class TransferService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient, private authService: AuthenticationService) {}

  getTransfers(): Promise<any> {
    const url = `${this.apiUrl}transfer`;
    return this.http
      .get(url, this.authService.getHeaders())
      .toPromise();
  }

  create(data: any): Promise<any> {
    const url = `${this.apiUrl}transfer`;
    console.log(url)
    return this.http
      .post(url, data, this.authService.getHeaders())
      .toPromise();
  }

  delete(id: number): Promise<any> {
    const url = `${this.apiUrl}transfer/${id}`;
    return this.http
      .delete(url, this.authService.getHeaders())
      .toPromise();
  }
}
