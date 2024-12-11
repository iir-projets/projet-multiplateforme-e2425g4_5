import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './service_sante/dashboard/dashboard.component';
import { AllergiesComponent } from './service_sante/allergies/allergies.component';
import { MaladiesComponent } from './service_sante/maladies/maladies.component';
import { MedicamentsComponent } from './service_sante/medicaments/medicaments.component';

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
    {   path: 'dashboard_sante', 
        component: DashboardComponent, 
        children:[
            { path: 'allergies', component: AllergiesComponent },
            { path: 'maladies', component: MaladiesComponent },
            { path: 'medicaments', component: MedicamentsComponent }
        ]
    }
    


];
