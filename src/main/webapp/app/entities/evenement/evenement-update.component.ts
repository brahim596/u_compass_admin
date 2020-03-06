import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IEvenement, Evenement } from 'app/shared/model/evenement.model';
import { EvenementService } from './evenement.service';
import { ICampusService } from 'app/shared/model/campus-service.model';
import { CampusServiceService } from 'app/entities/campus-service/campus-service.service';

@Component({
  selector: 'jhi-evenement-update',
  templateUrl: './evenement-update.component.html'
})
export class EvenementUpdateComponent implements OnInit {
  isSaving = false;
  campusservices: ICampusService[] = [];

  editForm = this.fb.group({
    id: [],
    dateDebut: [],
    dateFin: [],
    title: [],
    description: [],
    campusService: []
  });

  constructor(
    protected evenementService: EvenementService,
    protected campusServiceService: CampusServiceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ evenement }) => {
      if (!evenement.id) {
        const today = moment().startOf('day');
        evenement.dateDebut = today;
        evenement.dateFin = today;
      }

      this.updateForm(evenement);

      this.campusServiceService.query().subscribe((res: HttpResponse<ICampusService[]>) => (this.campusservices = res.body || []));
    });
  }

  updateForm(evenement: IEvenement): void {
    this.editForm.patchValue({
      id: evenement.id,
      dateDebut: evenement.dateDebut ? evenement.dateDebut.format(DATE_TIME_FORMAT) : null,
      dateFin: evenement.dateFin ? evenement.dateFin.format(DATE_TIME_FORMAT) : null,
      title: evenement.title,
      description: evenement.description,
      campusService: evenement.campusService
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const evenement = this.createFromForm();
    if (evenement.id !== undefined) {
      this.subscribeToSaveResponse(this.evenementService.update(evenement));
    } else {
      this.subscribeToSaveResponse(this.evenementService.create(evenement));
    }
  }

  private createFromForm(): IEvenement {
    return {
      ...new Evenement(),
      id: this.editForm.get(['id'])!.value,
      dateDebut: this.editForm.get(['dateDebut'])!.value ? moment(this.editForm.get(['dateDebut'])!.value, DATE_TIME_FORMAT) : undefined,
      dateFin: this.editForm.get(['dateFin'])!.value ? moment(this.editForm.get(['dateFin'])!.value, DATE_TIME_FORMAT) : undefined,
      title: this.editForm.get(['title'])!.value,
      description: this.editForm.get(['description'])!.value,
      campusService: this.editForm.get(['campusService'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEvenement>>): void {
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

  trackById(index: number, item: ICampusService): any {
    return item.id;
  }
}
