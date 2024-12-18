import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './service_sante/dashboard/dashboard.component';
import { AllergiesComponent } from './service_sante/allergies/allergies.component';
import { MaladiesComponent } from './service_sante/maladies/maladies.component';
import { MedicamentsComponent } from './service_sante/medicaments/medicaments.component';

import { DashboardadminComponent } from './admin/dashboardadmin/dashboardadmin.component';
import { StatsComponent } from './admin/stats/stats.component';
import { HerbsComponent } from './admin/herbs/herbs.component';
import { ArticlesComponent } from './admin/articles/articles.component';
import { ClientsComponent } from './admin/clients/clients.component';


import {ProfileComponent} from './client/profil/profil.component';
import { AccueilComponent } from './client/accueil/accueil.component';
import { PlantesComponent } from './client/plantes/plantes.component';
import { RoleGuard } from '../guards/auth.guard';
import { UnauthorizedComponent } from './unauthorized/unauthorized.component';

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

    {
        path: 'dashboard',
        component: DashboardadminComponent,
        canActivate: [RoleGuard],
        data: { role: 'ROLE_ADMIN' },
        children:[
            { path: 'statistiques', component: StatsComponent },
            { path: 'herbs', component: HerbsComponent },
            { path: 'articles', component: ArticlesComponent },
            { path: 'clients', component: ClientsComponent },
            { path: 'allergies', component: StatsComponent },
            { path: 'medicines', component: StatsComponent },
            { path: 'diseases', component: StatsComponent },
            
        ]
    },


    {  path: 'profil',
       component: ProfileComponent,
    },

    {
        path: 'accueil',
        component: AccueilComponent,
        canActivate: [RoleGuard],
        data: { role: 'ROLE_CLIENT' },
    },

   {
            path: 'plantes',
            component: PlantesComponent,
   }
    ,

    {   path: 'dashboard_sante', 
        component: DashboardComponent,
        canActivate: [RoleGuard],
        data: { role: 'ROLE_SERVICE_SANTE' }, 
        children:[
            { path: 'allergies', component: AllergiesComponent },
            { path: 'diseases', component: MaladiesComponent },
            { path: 'medicine', component: MedicamentsComponent }
        ]
    },
    
    {
        path: 'unauthorized',
        component: UnauthorizedComponent, // Redirect to UnauthorizedComponent
    },


];
