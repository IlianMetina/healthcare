import { HttpInterceptorFn } from '@angular/common/http';
import { isPlatformServer } from '@angular/common';
import { inject, PLATFORM_ID, REQUEST } from '@angular/core';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const platformId = inject(PLATFORM_ID);

  if (isPlatformServer(platformId)) {
    const serverRequest = inject(REQUEST, { optional: true });

    if (serverRequest) {
      const cookieHeader = serverRequest.headers.get('cookie') ?? '';

      if (cookieHeader) {
        const authReq = req.clone({
          setHeaders: { Cookie: cookieHeader }
        });
        return next(authReq);
      }
    }

    return next(req);
  }

  const authReq = req.clone({
    withCredentials: true
  });

  return next(authReq);
};
