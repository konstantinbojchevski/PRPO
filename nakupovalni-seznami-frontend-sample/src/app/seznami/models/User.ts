import {Reservation} from './Reservation';

export class User {
    id_user: number;
    firstname: string;
    lastname: string;
    username: string;
    personReservations: Reservation[];
}