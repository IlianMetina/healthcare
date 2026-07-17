import { ApplicationConfig, inject, provideBrowserGlobalErrorListeners, provideZoneChangeDetection, provideAppInitializer } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration, withEventReplay, withHttpTransferCacheOptions } from '@angular/platform-browser';
import { provideHttpClient, withFetch, withInterceptors } from '@angular/common/http';
import { authInterceptor } from './core/interceptors/auth-interceptor';
import { AuthService } from './services/auth/auth-service';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideHttpClient(
      withFetch(),
      withInterceptors([
        authInterceptor
      ])
    ),
    provideRouter(routes),
    provideClientHydration(withEventReplay(), withHttpTransferCacheOptions({
      filter: req => false
    })),

    provideAppInitializer(() => {
      const authService = inject(AuthService);
      return authService.checkAuthStatus();
    })

  ]
};
