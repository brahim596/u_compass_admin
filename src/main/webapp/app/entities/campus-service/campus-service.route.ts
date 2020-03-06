import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICampusService, CampusService } from 'app/shared/model/campus-service.model';
import { CampusServiceService } from './campus-service.service';
import { CampusServiceComponent } from './campus-service.component';
import { CampusServiceDetailComponent } from './campus-service-detail.component';
import { CampusServiceUpdateComponent } from './campus-service-update.component';

@Injectable({ providedIn: 'root' })
export class CampusServiceResolve implements Resolve<ICampusService> {
  constructor(private service: CampusServiceService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICampusService> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((campusService: HttpResponse<CampusService>) => {
          if (campusService.body) {
            return of(campusService.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CampusService());
  }
}

export const campusServiceRoute: Routes = [
  {
    path: '',
    component: CampusServiceComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'uCompassAdminApp.campusService.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CampusServiceDetailComponent,
    resolve: {
      campusService: CampusServiceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'uCompassAdminApp.campusService.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CampusServiceUpdateComponent,
    resolve: {
      campusService: CampusServiceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'uCompassAdminApp.campusService.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CampusServiceUpdateComponent,
    resolve: {
      campusService: CampusServiceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'uCompassAdminApp.campusService.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
