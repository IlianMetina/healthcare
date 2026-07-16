import { Component, Input } from '@angular/core';
import { Appointment, DayStat } from '../../../../core/models/patient';

@Component({
  selector: 'app-right-panel',
  imports: [],
  templateUrl: './right-panel.html',
  styleUrl: './right-panel.css',
})
export class RightPanel {
  @Input() appointments: Appointment[] = [];
  @Input() patientsCount: number = 0;

  get stats(): DayStat[] {
    return [
      { label: 'Patients', value: this.patientsCount, icon: 'ri-group-line' },
      { label: 'Consultations', value: 6, icon: 'ri-clipboard-line' },
      { label: 'Urgences', value: 2, icon: 'ri-alert-line' },
      { label: 'En attente', value: 4, icon: 'ri-time-line' },
    ];
  }

}
