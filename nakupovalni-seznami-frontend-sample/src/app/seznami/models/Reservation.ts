import {Charger} from './Charger';
import {User} from './User';
import {Time} from '@angular/common';

export class Reservation {
    id_reservation: number;
    charger: Charger;
    user: User;
    from: Time;
    to: Time;
}