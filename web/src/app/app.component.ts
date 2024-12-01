import { Component } from '@angular/core';
import { DashboardSanteComponent } from './service_sante/dashboard-sante/dashboard-sante.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [DashboardSanteComponent],
  template: `
    <app-dashboard-sante></app-dashboard-sante>
  `
})
export class AppComponent {}
