import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './service_sante/dashboard/dashboard.component';
import { AllergiesComponent } from './service_sante/allergies/allergies.component';
import { MaladiesComponent } from './service_sante/maladies/maladies.component';
import { MedicamentsComponent } from './service_sante/medicaments/medicaments.component';
import {ProfileComponent} from './client/profil/profil.component';
import { AccueilComponent } from './client/accueil/accueil.component';
import { PlantesComponent } from './client/plantes/plantes.component';
export const routes: Routes = [
    {
        path:'',
        redirectTo: 'login',
        pathMatch:'full',
    },
    {   path: 'login', 
        component: LoginComponent 
    },
    {   path: 'register', 
        component: RegisterComponent 
    },

    {  path: 'profil',
       component: ProfileComponent,
    },

    {
        path: 'accueil',
        component: AccueilComponent,
    },

   {
            path: 'plantes',
            component: PlantesComponent,
   }
    ,
    {   path: 'dashboard_sante', 
        component: DashboardComponent, 
        children:[
            { path: 'allergies', component: AllergiesComponent },
            { path: 'maladies', component: MaladiesComponent },
            { path: 'medicaments', component: MedicamentsComponent }
        ]
    }
    


];
