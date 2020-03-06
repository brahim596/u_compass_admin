import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { UCompassAdminTestModule } from '../../../test.module';
import { CampusServiceUpdateComponent } from 'app/entities/campus-service/campus-service-update.component';
import { CampusServiceService } from 'app/entities/campus-service/campus-service.service';
import { CampusService } from 'app/shared/model/campus-service.model';

describe('Component Tests', () => {
  describe('CampusService Management Update Component', () => {
    let comp: CampusServiceUpdateComponent;
    let fixture: ComponentFixture<CampusServiceUpdateComponent>;
    let service: CampusServiceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UCompassAdminTestModule],
        declarations: [CampusServiceUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CampusServiceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CampusServiceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CampusServiceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CampusService(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new CampusService();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
