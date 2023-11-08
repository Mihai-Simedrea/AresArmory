import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { AuthModel } from '../components/shared/models/auth.model';
import { LoginModel } from '../components/shared/models/login.model';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthClient {
  private baseUrl = `${environment.backendApiUrl}/user`;

  constructor(private http: HttpClient) {}

  signUp(authModel: AuthModel): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/signup`, authModel);
  }
  
  login(loginModel: LoginModel): Observable<any> {
    // return this.http.get<any>(`${this.baseUrl}/login`, loginModel);
    return of();
  }
}
