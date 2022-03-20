import {Component, Inject, OnInit} from '@angular/core';
import {Router} from '@angular/router';

import { NakupovalniSeznam } from './models/seznam';
import { SeznamiService } from './services/seznami.service';
import {Reservation} from "./models/Reservation";
import {DOCUMENT} from "@angular/common";

@Component({
    moduleId: module.id,
    selector: 'vsi-seznami',
    templateUrl: 'seznami.component.html'
})
export class SeznamiComponent implements OnInit {
    reservations: Reservation[];
    seznami: NakupovalniSeznam[];
    seznam: NakupovalniSeznam;

    constructor(private seznamiService: SeznamiService,
                private router: Router, @Inject(DOCUMENT) private document) {
    }

    ngOnInit(): void {
        this.getReservations();
    }

    getReservations(): void {
        this.seznamiService
            .getReservations()
            .subscribe(seznami => this.reservations = seznami);
    }

    getSeznami(): void {
        this.seznamiService
            .getSeznami()
            .subscribe(seznami => this.seznami = seznami);
    }

    naPodrobnosti(id: number): void {
        this.document.getElementById(id).hidden = false;
    }

    delete(id: number): void {
        this.seznamiService
            .delete(id)
            .subscribe(() => {
                this.getReservations();
            });
    }
}
