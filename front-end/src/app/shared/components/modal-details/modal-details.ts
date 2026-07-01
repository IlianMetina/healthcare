import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Patient } from '../../../core/models/patient';

@Component({
  selector: 'app-modal-details',
  imports: [],
  templateUrl: './modal-details.html',
  styleUrl: './modal-details.css',
})
export class ModalDetails {

  @Input() patient!: Patient;
  @Output() close = new EventEmitter<void>();

}
