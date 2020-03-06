import { Moment } from 'moment';
import { IEvenement } from 'app/shared/model/evenement.model';
import { Affluence } from 'app/shared/model/enumerations/affluence.model';
import { Type } from 'app/shared/model/enumerations/type.model';

export interface ICampusService {
  id?: number;
  idd?: string;
  name?: string;
  open?: boolean;
  description?: string;
  longitude?: number;
  latitude?: number;
  estimatedWaitingTime?: number;
  attente?: number;
  openingTime?: Moment;
  closureTime?: Moment;
  affluence?: Affluence;
  type?: Type;
  evenements?: IEvenement[];
}

export class CampusService implements ICampusService {
  constructor(
    public id?: number,
    public idd?: string,
    public name?: string,
    public open?: boolean,
    public description?: string,
    public longitude?: number,
    public latitude?: number,
    public estimatedWaitingTime?: number,
    public attente?: number,
    public openingTime?: Moment,
    public closureTime?: Moment,
    public affluence?: Affluence,
    public type?: Type,
    public evenements?: IEvenement[]
  ) {
    this.open = this.open || false;
  }
}
