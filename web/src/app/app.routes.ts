import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './service_sante/components/login/login.component';
import { DashboardComponent } from './service_sante/components/dashboard/dashboard.component';
import { ProfileComponent } from './client/profil/profil.component';

export const routes: Routes = [
  {
      path:'',
      redirectTo: 'login',
      pathMatch:'full',
  },
  {   path: 'login', 
      component: LoginComponent 
  },
  {
    path : 'profil',
    component : ProfileComponent
  }
  


];
