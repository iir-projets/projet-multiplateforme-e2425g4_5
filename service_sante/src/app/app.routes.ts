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
import { ClientsComponent } from './admin/clients/clients.component';

import { ArticleComponent } from './client/articles/articles.component';



import {ProfileComponent} from './client/profil/profil.component';
import { AccueilComponent } from './client/accueil/accueil.component';
import { PlantesComponent } from './client/plantes/plantes.component';
import { RoleGuard } from '../guards/auth.guard';
import { UnauthorizedComponent } from './unauthorized/unauthorized.component';



import { DetailsPlantesComponent } from './client/details-plantes/details-plantes.component';
import { NotificationsComponent } from './client/notifications/notifications.component';
import { FavorisComponent } from './client/favoris/favoris.component';
import { ArticleDetailsComponent } from './client/details-articles/details-articles.component';
import { SavedArticlesComponent } from './client/saved-articles/saved-articles.component';
import { MedicamentsAdminComponent } from './admin/medicaments-admin/medicaments-admin.component';
import { AllergiesAdminComponent } from './admin/allergies-admin/allergies-admin.component';
import { MaladiesAdminComponent } from './admin/maladies-admin/maladies-admin.component';
import { ArticlesAdminComponent } from './admin/articles-admin/articles-admin.component';

export const routes: Routes = [

    { path: '', component: PlantesComponent },
    { path: 'details_plantes/:id', component: DetailsPlantesComponent },

    { path: '', component: ArticleComponent }, // Route for the article listing page
  { path: 'details_articles/:id', component: ArticleDetailsComponent },

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
            { path: 'articles', component: ArticlesAdminComponent },
            { path: 'clients', component: ClientsComponent },
            { path: 'allergies', component: AllergiesAdminComponent },
            { path: 'medicines', component: MedicamentsAdminComponent },
            { path: 'diseases', component: MaladiesAdminComponent },
            
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
    path:'articles',
    component:ArticleComponent
  }
    ,

   {
            path: 'plantes',
            component: PlantesComponent,
   },
 
{
    path: 'favoris',
    component: FavorisComponent
}
   ,


{
    path:'saved_articles',
    component:SavedArticlesComponent
}
   ,

    {
        path: 'details_plantes',
        component: DetailsPlantesComponent,
 
    },


   {
    path: 'details_articles',
    component:ArticleDetailsComponent
   }
    ,

    {
    path: 'notifications',
    component: NotificationsComponent
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
