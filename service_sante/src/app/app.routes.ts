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
    {   path: 'dashboard_sante', 
        component: DashboardComponent, 
        children:[
            { path: 'allergies', component: AllergiesComponent },
            { path: 'diseases', component: MaladiesComponent },
            { path: 'medicine', component: MedicamentsComponent }
        ]
    },
    
    


];
