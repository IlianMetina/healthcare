import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../../services/auth/auth-service';

export const authInterceptor: HttpInterceptorFn = (req, next) => {

  const authService = inject(AuthService);
  // getToken() renvoie le token ou null
  const token = authService.getToken();

  // Si la requête est destinée à la connexion, token est forcément égal à null
  // donc on laisse passer la requête normalement
  if (req.url.includes("api/v1/users/login")) {
    return next(req);
  }

  // Si la méthode renvoie bien le token, alors on le met dans les headers:
  if (token) {

    // On utilise la fonction d'HttpRequest .clone() qui permet de cloner la requête
    // car la requête est en lecture seule et non modifiable
    const authReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });

    return next(authReq);
  }

  // Si token == null, alors on laisse la requête de base sans modification des headers
  return next(req);
};
