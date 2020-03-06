import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICampusService, CampusService } from 'app/shared/model/campus-service.model';
import { CampusServiceService } from './campus-service.service';

@Component({
  selector: 'jhi-campus-service-update',
  templateUrl: './campus-service-update.component.html'
})
export class CampusServiceUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    idd: [],
    name: [],
    open: [],
    description: [],
    longitude: [],
    latitude: [],
    estimatedWaitingTime: [],
    attente: [],
    openingTime: [],
    closureTime: [],
    affluence: [],
    type: []
  });

  constructor(protected campusServiceService: CampusServiceService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ campusService }) => {
      if (!campusService.id) {
        const today = moment().startOf('day');
        campusService.openingTime = today;
        campusService.closureTime = today;
      }

      this.updateForm(campusService);
    });
  }

  updateForm(campusService: ICampusService): void {
    this.editForm.patchValue({
      id: campusService.id,
      idd: campusService.idd,
      name: campusService.name,
      open: campusService.open,
      description: campusService.description,
      longitude: campusService.longitude,
      latitude: campusService.latitude,
      estimatedWaitingTime: campusService.estimatedWaitingTime,
      attente: campusService.attente,
      openingTime: campusService.openingTime ? campusService.openingTime.format(DATE_TIME_FORMAT) : null,
      closureTime: campusService.closureTime ? campusService.closureTime.format(DATE_TIME_FORMAT) : null,
      affluence: campusService.affluence,
      type: campusService.type
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const campusService = this.createFromForm();
    if (campusService.id !== undefined) {
      this.subscribeToSaveResponse(this.campusServiceService.update(campusService));
    } else {
      this.subscribeToSaveResponse(this.campusServiceService.create(campusService));
    }
  }

  private createFromForm(): ICampusService {
    return {
      ...new CampusService(),
      id: this.editForm.get(['id'])!.value,
      idd: this.editForm.get(['idd'])!.value,
      name: this.editForm.get(['name'])!.value,
      open: this.editForm.get(['open'])!.value,
      description: this.editForm.get(['description'])!.value,
      longitude: this.editForm.get(['longitude'])!.value,
      latitude: this.editForm.get(['latitude'])!.value,
      estimatedWaitingTime: this.editForm.get(['estimatedWaitingTime'])!.value,
      attente: this.editForm.get(['attente'])!.value,
      openingTime: this.editForm.get(['openingTime'])!.value
        ? moment(this.editForm.get(['openingTime'])!.value, DATE_TIME_FORMAT)
        : undefined,
      closureTime: this.editForm.get(['closureTime'])!.value
        ? moment(this.editForm.get(['closureTime'])!.value, DATE_TIME_FORMAT)
        : undefined,
      affluence: this.editForm.get(['affluence'])!.value,
      type: this.editForm.get(['type'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICampusService>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
