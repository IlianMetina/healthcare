import { HttpClient } from '@angular/common/http';
import { inject, Injectable, PLATFORM_ID, signal } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

interface LoginRequest {
  email: string;
  password: string;
}

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private token: string | null = null;
  private http = inject(HttpClient);
  private router = inject(Router);
  private platformId = inject(PLATFORM_ID);

  isAuthenticated = signal<boolean>(false);

  getToken(): string | null {
    return this.token;
  }

  connect(data: LoginRequest): Observable<string> {
    return this.http.post("http://localhost:8072/api/v1/users/login", data, { responseType: 'text', withCredentials: true });
  }

  logout(): Observable<string> {
    return this.http.post("http://localhost:8072/api/v1/users/logout", {}, { responseType: 'text', withCredentials: true });
  }

}
