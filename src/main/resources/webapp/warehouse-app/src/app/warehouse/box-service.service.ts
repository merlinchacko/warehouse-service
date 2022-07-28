import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpParams} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {Box} from "./box";
import {catchError} from "rxjs/operators";

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
    return this.http.post(`${this.url}`, box)
    .pipe(
      catchError(this.handleError)
    );;
  }

  searchBox(search: String): Observable<any> {

    let params = new HttpParams();
      // @ts-ignore
    params = params.append('query', search);
    console.log("query===", search);
    // @ts-ignore
    return this.http.get(`${this.url + 'search/'}`, {params : params});
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      console.error('An error occurred:', error.error);
    } else {
      console.error(`Backend returned code ${error.status}, body was: `, error.error);
    }
    return throwError(error.error);
  }

}
