import {Reservation} from './Reservation';
import {Owner} from './Owner';
import {Time} from '@angular/common';

export class Charger {
    id_charger: number;
    name: string;
    specifications: string;
    location: string;
    price: number;
    opening: Time;
    closing: Time;
    owner: Owner;
    chargerReservations: Reservation[];
}