import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEvenement } from 'app/shared/model/evenement.model';
import { EvenementService } from './evenement.service';
import { EvenementDeleteDialogComponent } from './evenement-delete-dialog.component';

@Component({
  selector: 'jhi-evenement',
  templateUrl: './evenement.component.html'
})
export class EvenementComponent implements OnInit, OnDestroy {
  evenements?: IEvenement[];
  eventSubscriber?: Subscription;

  constructor(protected evenementService: EvenementService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.evenementService.query().subscribe((res: HttpResponse<IEvenement[]>) => (this.evenements = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEvenements();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEvenement): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEvenements(): void {
    this.eventSubscriber = this.eventManager.subscribe('evenementListModification', () => this.loadAll());
  }

  delete(evenement: IEvenement): void {
    const modalRef = this.modalService.open(EvenementDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.evenement = evenement;
  }
}
