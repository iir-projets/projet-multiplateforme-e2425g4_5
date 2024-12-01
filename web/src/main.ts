import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter } from '@angular/router';
import { importProvidersFrom } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppComponent } from '../src/app/app.component';
import { DashboardSanteComponent } from '../src/app/service_sante/dashboard-sante/dashboard-sante.component';
import { SectionComponent } from '../src/app/service_sante/section/section.component';

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter([
      // Ajoutez vos routes ici, si nécessaire
      { path: '', component: DashboardSanteComponent }
    ]),
    importProvidersFrom(FormsModule) // Nécessaire pour ngModel
  ]
}).catch(err => console.error(err));
