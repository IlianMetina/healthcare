import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Patient } from '../../../core/models/patient';
import { Avatar } from '../avatar/avatar';

@Component({
  selector: 'app-card',
  imports: [Avatar],
  templateUrl: './card.html',
  styleUrl: './card.css',
})
export class Card {

  patientTest = {
    firstName: "Jean",
    lastName: "Valjean",
    birthDate: "1995-12-12",
    gender: "M",
    address: "123 Rue de la République",
    phoneNumber: "0612457852",
    visits: 14
  };

  @Input() patient!: Patient;
  @Output() openDetails = new EventEmitter<Patient>();

  get patientInitials(): string {
    const first = this.patient.firstName?.charAt(0).toUpperCase() ?? '';
    const second = this.patient.lastName?.charAt(0).toUpperCase() ?? '';
    return first + second;
  }

  onCardClick(): void{
    this.openDetails.emit(this.patient);
  }
}
