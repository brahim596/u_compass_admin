import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { UCompassAdminTestModule } from '../../../test.module';
import { CampusServiceDetailComponent } from 'app/entities/campus-service/campus-service-detail.component';
import { CampusService } from 'app/shared/model/campus-service.model';

describe('Component Tests', () => {
  describe('CampusService Management Detail Component', () => {
    let comp: CampusServiceDetailComponent;
    let fixture: ComponentFixture<CampusServiceDetailComponent>;
    const route = ({ data: of({ campusService: new CampusService(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UCompassAdminTestModule],
        declarations: [CampusServiceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CampusServiceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CampusServiceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load campusService on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.campusService).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
