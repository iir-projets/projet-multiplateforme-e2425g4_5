import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './service_sante/components/login/login.component';
import { DashboardComponent } from './service_sante/components/dashboard/dashboard.component';
import { NgModule } from '@angular/core';

const routes: Routes = [
    { path: '', component: LoginComponent }, // Route par défaut pour afficher LoginComponent
    { path: 'dashboard', component: DashboardComponent }, // Route pour le Dashboard
  ];
  
  @NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
  })
  export class AppRoutingModule {}