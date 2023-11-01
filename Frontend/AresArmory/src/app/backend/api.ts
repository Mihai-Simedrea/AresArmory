import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthModel } from '../components/shared/models/auth.model';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthClient {
  private baseUrl = `${environment.backendApiUrl}/user`;

  constructor(private http: HttpClient) {}

  signUp(authModel: AuthModel): Observable<any> {
    return this.http.post<any>(`http://localhost:8080/user/signup`, authModel);
  }
}
