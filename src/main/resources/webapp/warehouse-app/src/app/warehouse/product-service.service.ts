import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private url = 'http://localhost:8080/warehouse/product/';

  constructor(private http: HttpClient) {
  }

  getProducts(): Observable<any> {
    return this.http.get(`${this.url}`);
  }

  addProduct(product: Object): Observable<Object> {
    return this.http.post(`${this.url}`, product);
  }

}
