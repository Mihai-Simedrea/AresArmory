import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { AuthModel } from '../components/shared/models/auth.model';
import { LoginModel } from '../components/shared/models/login.model';
import { environment } from '../environments/environment';
import { CategoryModel } from '../components/shared/models/category.model';
import { ProductModel } from '../components/shared/models/product.model';
import { CartModel } from '../components/shared/models/cart.model';

@Injectable({
  providedIn: 'root',
})
export class AuthClient {
  private baseUrl = `${environment.backendApiUrl}/user`;

  constructor(private http: HttpClient) { }

  signUp(authModel: AuthModel): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/signup`, authModel);
  }

  login(loginModel: LoginModel): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/login`, loginModel);
  }
}

@Injectable({
  providedIn: 'root'
})
export class CategoryClient {
  private baseUrl = `${environment.backendApiUrl}/category`;

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(`${this.baseUrl}/get`);
  }

  add(categoryModel: CategoryModel): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/add`, categoryModel);
  }

  delete(categoryId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${categoryId}`);
  }
}

@Injectable({
  providedIn: 'root'
})
export class ProductClient {
  private baseUrl = `${environment.backendApiUrl}/product`;

  constructor(private http: HttpClient) { }

  getByCategory(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/getByCategory/${id}`);
  }

  add(productModel: ProductModel): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/add`, productModel);
  }

  delete(productId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${productId}`);
  }
}

@Injectable({
  providedIn: 'root'
})
export class CartClient {
  private baseUrl = `${environment.backendApiUrl}/cart_item`;

  constructor(private http: HttpClient) { }

  add(cartModel: CartModel): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/add`, cartModel);
  }

  remove(id: number, cartId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${id}/${cartId}`);
  }

  get(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/get/${id}`);
  }
}

@Injectable({
  providedIn: 'root'
})
export class CartUserClient {
  private baseUrl = `${environment.backendApiUrl}/cart`;

  constructor(private http: HttpClient) { }

  getUserCart(email: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/get/${email}`);
  }

  deleteUserCart(email: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${email}`);
  }
}
