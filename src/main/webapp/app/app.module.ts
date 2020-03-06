import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { UCompassAdminSharedModule } from 'app/shared/shared.module';
import { UCompassAdminCoreModule } from 'app/core/core.module';
import { UCompassAdminAppRoutingModule } from './app-routing.module';
import { UCompassAdminHomeModule } from './home/home.module';
import { UCompassAdminEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    UCompassAdminSharedModule,
    UCompassAdminCoreModule,
    UCompassAdminHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    UCompassAdminEntityModule,
    UCompassAdminAppRoutingModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent]
})
export class UCompassAdminAppModule {}
