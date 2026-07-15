import { Component, signal } from '@angular/core';
import { AddPatientModale } from '../../../../app/features/patients/components/add-patient-modale/add-patient-modale';

@Component({
  selector: 'app-header',
  imports: [AddPatientModale],
  templateUrl: './header.html',
  styleUrl: './header.css'
})
export class Header {

  username = "Martin";

  isModalOpen = signal(false);

  openModal(): void {
    console.log("open modal");
    this.isModalOpen.set(true);
  }

  closeModal(): void {
    this.isModalOpen.set(false);
  }

  afterPatientCreated(): void {
    //refaire l'appel api pour recharger les patients
  }

}
