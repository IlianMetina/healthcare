import { Component, Input } from '@angular/core';
import { Patient, StatusConfig } from '../../../../core/models/patient';

@Component({
  selector: 'app-patient-detail',
  imports: [],
  templateUrl: './patient-detail.html',
  styleUrl: './patient-detail.css',
})
export class PatientDetail {
  @Input() patient!: Patient;
  @Input() statusConfig!: Record<string, StatusConfig>;

  infoItems = [
    { label: 'Groupe sanguin', value: 'A+' },
    { label: 'Allergie', value: 'Pénicilline' },
    { label: 'Médecin traitant', value: 'Dr. Fontaine' },
    { label: 'N° dossier', value: 'PAT-2024-0841' },
  ];

  get status(): StatusConfig {
    return this.statusConfig[this.patient.status || 'stable'];
  }
}
