import {Charger} from './Charger';
import {Reservation} from './Reservation';

export class Owner {
    firstname_owner: string;
    lastname_owner: string;
    username_owner: string;
    personReservations: Reservation[];
    chargers: Charger[]
}