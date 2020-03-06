import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { CampusServiceService } from 'app/entities/campus-service/campus-service.service';
import { ICampusService, CampusService } from 'app/shared/model/campus-service.model';
import { Affluence } from 'app/shared/model/enumerations/affluence.model';
import { Type } from 'app/shared/model/enumerations/type.model';

describe('Service Tests', () => {
  describe('CampusService Service', () => {
    let injector: TestBed;
    let service: CampusServiceService;
    let httpMock: HttpTestingController;
    let elemDefault: ICampusService;
    let expectedResult: ICampusService | ICampusService[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CampusServiceService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new CampusService(
        0,
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        0,
        0,
        0,
        0,
        currentDate,
        currentDate,
        Affluence.LOW,
        Type.RESTAURATION
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            openingTime: currentDate.format(DATE_TIME_FORMAT),
            closureTime: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a CampusService', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            openingTime: currentDate.format(DATE_TIME_FORMAT),
            closureTime: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            openingTime: currentDate,
            closureTime: currentDate
          },
          returnedFromService
        );

        service.create(new CampusService()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CampusService', () => {
        const returnedFromService = Object.assign(
          {
            idd: 'BBBBBB',
            name: 'BBBBBB',
            open: true,
            description: 'BBBBBB',
            longitude: 1,
            latitude: 1,
            estimatedWaitingTime: 'BBBBBB',
            attente: 1,
            openingTime: currentDate.format(DATE_TIME_FORMAT),
            closureTime: currentDate.format(DATE_TIME_FORMAT),
            affluence: 'BBBBBB',
            type: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            openingTime: currentDate,
            closureTime: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of CampusService', () => {
        const returnedFromService = Object.assign(
          {
            idd: 'BBBBBB',
            name: 'BBBBBB',
            open: true,
            description: 'BBBBBB',
            longitude: 1,
            latitude: 1,
            estimatedWaitingTime: 'BBBBBB',
            attente: 1,
            openingTime: currentDate.format(DATE_TIME_FORMAT),
            closureTime: currentDate.format(DATE_TIME_FORMAT),
            affluence: 'BBBBBB',
            type: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            openingTime: currentDate,
            closureTime: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a CampusService', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
