import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Marker} from "leaflet";

@Injectable({
  providedIn: 'root'
})

export class RouteService {
  constructor(private http: HttpClient) {
  }

  private apiUrl = environment.apiUrl;

  public getInstructionsList(start: Marker, finish: Marker): Promise<any[]> {
    const url = `${this.apiUrl}/routes?start_lat=${start.getLatLng().lat}&start_lon=${start.getLatLng().lng}&
                                  finish_lat=${finish.getLatLng().lat}&finish_lon=${finish.getLatLng().lng}`;
    return this.http.get(url)
                    .toPromise()
                    .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occured', error.error.errmsg || error);
    return Promise.reject(error.error.errmsg || error);
  }
}
