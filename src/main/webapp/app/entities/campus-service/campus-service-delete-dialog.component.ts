import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICampusService } from 'app/shared/model/campus-service.model';
import { CampusServiceService } from './campus-service.service';

@Component({
  templateUrl: './campus-service-delete-dialog.component.html'
})
export class CampusServiceDeleteDialogComponent {
  campusService?: ICampusService;

  constructor(
    protected campusServiceService: CampusServiceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.campusServiceService.delete(id).subscribe(() => {
      this.eventManager.broadcast('campusServiceListModification');
      this.activeModal.close();
    });
  }
}
