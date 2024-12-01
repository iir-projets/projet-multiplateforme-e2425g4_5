import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from '../src/app/app.component';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { authInterceptor } from './app/service_sante/services/login/auth-interceptor.service';

// Initialiser l'application avec les intercepteurs et les clients HTTP
bootstrapApplication(AppComponent, {
  providers: [
    // Fournit le client HTTP et configure l'intercepteur
    provideHttpClient(withInterceptors([authInterceptor])),
  ],
});
