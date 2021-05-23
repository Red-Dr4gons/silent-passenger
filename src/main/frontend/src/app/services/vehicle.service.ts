import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {AuthenticationService} from "../authentication/authentication.service";

@Injectable({
  providedIn: 'root'
})
export class VehicleService {
  private VEHICLE_URL = environment.apiUrl + 'vehicle';

  constructor(private http: HttpClient, private authService: AuthenticationService) {
  }

  getMyVehicles(): Promise<any> {
    return this.http.get(this.VEHICLE_URL, this.authService.getHeaders()).toPromise();
  }

  add(data: any): Promise<any> {
    return this.http.post(this.VEHICLE_URL, data, this.authService.getHeaders()).toPromise();
  }

  deleteVehicle(data: any): Promise<any> {
    return this.http.delete(this.VEHICLE_URL + '/' + data, this.authService.getHeaders()).toPromise();
  }
}
