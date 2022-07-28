import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Box} from "./box";

@Injectable({
  providedIn: 'root'
})
export class BoxService {

  private url = 'http://localhost:8080/warehouse/box/';

  constructor(private http: HttpClient) {
  }

  getBoxes(): Observable<any> {
    return this.http.get(`${this.url}`);
  }

  addBox(box: Object): Observable<Object> {
    console.log("box===", box);
    return this.http.post(`${this.url}`, box);
  }

  searchBox(search: String): Observable<any> {

    let params = new HttpParams();
      // @ts-ignore
    params = params.append('query', search);
    console.log("query===", search);
    // @ts-ignore
    return this.http.get(`${this.url + 'search/'}`, {params : params});
  }
}
