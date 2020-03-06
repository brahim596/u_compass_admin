import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICampusService } from 'app/shared/model/campus-service.model';

type EntityResponseType = HttpResponse<ICampusService>;
type EntityArrayResponseType = HttpResponse<ICampusService[]>;

@Injectable({ providedIn: 'root' })
export class CampusServiceService {
  public resourceUrl = SERVER_API_URL + 'api/campus-services';

  constructor(protected http: HttpClient) {}

  create(campusService: ICampusService): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(campusService);
    return this.http
      .post<ICampusService>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(campusService: ICampusService): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(campusService);
    return this.http
      .put<ICampusService>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICampusService>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICampusService[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(campusService: ICampusService): ICampusService {
    const copy: ICampusService = Object.assign({}, campusService, {
      openingTime: campusService.openingTime && campusService.openingTime.isValid() ? campusService.openingTime.toJSON() : undefined,
      closureTime: campusService.closureTime && campusService.closureTime.isValid() ? campusService.closureTime.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.openingTime = res.body.openingTime ? moment(res.body.openingTime) : undefined;
      res.body.closureTime = res.body.closureTime ? moment(res.body.closureTime) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((campusService: ICampusService) => {
        campusService.openingTime = campusService.openingTime ? moment(campusService.openingTime) : undefined;
        campusService.closureTime = campusService.closureTime ? moment(campusService.closureTime) : undefined;
      });
    }
    return res;
  }
}
