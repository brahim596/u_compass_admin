import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICampusService } from 'app/shared/model/campus-service.model';

@Component({
  selector: 'jhi-campus-service-detail',
  templateUrl: './campus-service-detail.component.html'
})
export class CampusServiceDetailComponent implements OnInit {
  campusService: ICampusService | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ campusService }) => (this.campusService = campusService));
  }

  previousState(): void {
    window.history.back();
  }
}
