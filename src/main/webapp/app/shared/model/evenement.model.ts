import { Moment } from 'moment';
import { ICampusService } from 'app/shared/model/campus-service.model';

export interface IEvenement {
  id?: number;
  dateDebut?: Moment;
  dateFin?: Moment;
  title?: string;
  description?: string;
  campusService?: ICampusService;
}

export class Evenement implements IEvenement {
  constructor(
    public id?: number,
    public dateDebut?: Moment,
    public dateFin?: Moment,
    public title?: string,
    public description?: string,
    public campusService?: ICampusService
  ) {}
}
