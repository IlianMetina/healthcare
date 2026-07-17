import { isPlatformBrowser } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { inject, Injectable, PLATFORM_ID, signal } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, of, tap } from 'rxjs';

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
    return this.http.post("http://localhost:8072/api/v1/users/login", data, { responseType: 'text', withCredentials: true }).pipe(
      tap(() => {
        this.isAuthenticated.set(true);
      })
    );
  }

  logout(): Observable<string> {
    return this.http.post("http://localhost:8072/api/v1/users/logout", {}, { responseType: 'text', withCredentials: true });
  }

  checkAuthStatus() {
    if (!isPlatformBrowser(this.platformId)) {
      this.isAuthenticated.set(true);
      return of(null);
    }
    console.log("APP_INITIALIZER: Lancement de la vérification du token...");
    return this.http.get("http://localhost:8072/api/v1/users/me").pipe(
      tap(() => {
        console.log("Vérification OK : Le backend a renvoyé un succès (200).");
        this.isAuthenticated.set(true);
      }),
      catchError((err) => {
        console.log("Vérification ERREUR : Le backend a refusé (401 ou autre). Erreur :", err.status);
        this.isAuthenticated.set(false);
        return of(null);
      })
    )
  }

}
