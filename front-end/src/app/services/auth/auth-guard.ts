import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth-service';

export const authGuard: CanActivateFn = (route, state) => {

  const authService = inject(AuthService);
  const router = inject(Router);

  const isAuth = authService.isAuthenticated();
  console.log("GUARD: L'utilisateur tente d'accéder à la route. isAuthenticated vaut :", isAuth);

  if (isAuth) {
    return true;
  }
  else {
    console.log("GUARD: Accès refusé, redirection vers /login");
    router.navigate(['/login']);
    return false;
  }
};
