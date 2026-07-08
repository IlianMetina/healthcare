import { Component, Input } from '@angular/core';
import { MedicalRecord } from '../../../../core/models/patient';

@Component({
  selector: 'app-medical-timeline',
  imports: [],
  templateUrl: './medical-timeline.html',
  styleUrl: './medical-timeline.css',
})
export class MedicalTimeline {
  @Input() records: MedicalRecord[] = [];

  iconMap: Record<string, string> = {
    pill: 'ri-capsule-line',
    check: 'ri-checkbox-circle-line',
    activity: 'ri-pulse-line',
    file: 'ri-file-text-line',
    alert: 'ri-alert-line',
  };

  getIcon(iconKey: string): string {
    return this.iconMap[iconKey] || 'ri-file-text-line';
  }

  getIconColor(iconKey: string): string {
    if (iconKey === 'alert') return '#ef4444';
    if (iconKey === 'check') return '#0d9488';
    return '#6b7a96';
  }
}
