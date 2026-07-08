import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Patient, StatusConfig } from '../../../../core/models/patient';

@Component({
  selector: 'app-patient-list-item',
  imports: [],
  templateUrl: './patient-list-item.html',
  styleUrl: './patient-list-item.css',
})
export class PatientListItem {
  @Input() patient!: Patient;
  @Input() isSelected = false;
  @Input() statusConfig!: Record<string, StatusConfig>;
  @Output() selectPatient = new EventEmitter<Patient>();

  get status(): StatusConfig {
    return this.statusConfig[this.patient.status || 'stable'];
  }

  onSelect(): void {
    this.selectPatient.emit(this.patient);
  }
}
