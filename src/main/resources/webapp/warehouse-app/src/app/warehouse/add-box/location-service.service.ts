import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  private url = 'http://localhost:8080/warehouse/location/';

  constructor(private http: HttpClient) {
  }

  getLocations(): Observable<any> {
    return this.http.get(`${this.url}`);
  }
}
