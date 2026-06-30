import { Component } from '@angular/core';

@Component({
  selector: 'app-card',
  imports: [],
  templateUrl: './card.html',
  styleUrl: './card.css',
})
export class Card {

  patient = {
    firstName: "Jean",
    lastName: "Valjean"
  };

  get patientInitials(): string {
    const first = this.patient.firstName?.charAt(0).toUpperCase() ?? '';
    const second = this.patient.lastName?.charAt(0).toUpperCase() ?? '';
    return first + second;
  }
}
