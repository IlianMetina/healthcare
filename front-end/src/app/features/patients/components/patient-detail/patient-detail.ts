import { Component, Input, Output, EventEmitter } from '@angular/core';
import { AddNotesModal } from '../add-notes-modal/add-notes-modal';
import { EditPatientModal } from '../edit-patient-modal/edit-patient-modal';
import { Patient, StatusConfig } from '../../../../core/models/patient';

@Component({
  selector: 'app-patient-detail',
  imports: [AddNotesModal, EditPatientModal],
  templateUrl: './patient-detail.html',
  styleUrl: './patient-detail.css',
})
export class PatientDetail {
  @Input() patient!: Patient;
  @Input() statusConfig!: Record<string, StatusConfig>;
  @Output() noteCreated = new EventEmitter<void>();

  showNotesModal = false;
  showEditModal = false;

  infoItems = [
    { label: 'Groupe sanguin', value: 'A+' },
    { label: 'Allergie', value: 'Pénicilline' },
    { label: 'Médecin traitant', value: 'Dr. Fontaine' },
    { label: 'N° dossier', value: 'PAT-2024-0841' },
  ];

  get status(): StatusConfig {
    return this.statusConfig[this.patient.status || 'stable'];
  }

  onPatientUpdated(updatedPatient: Patient): void {
    this.patient = updatedPatient;
  }
}
