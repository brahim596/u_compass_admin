import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'campus-service',
        loadChildren: () => import('./campus-service/campus-service.module').then(m => m.UCompassAdminCampusServiceModule)
      },
      {
        path: 'evenement',
        loadChildren: () => import('./evenement/evenement.module').then(m => m.UCompassAdminEvenementModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class UCompassAdminEntityModule {}
