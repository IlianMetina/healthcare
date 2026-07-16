import { Component, EventEmitter, inject, Input, Output } from '@angular/core';
import { MedicalRecord, NotesResponse, PatientNotes } from '../../../../core/models/patient';
import { NotesService } from '../../../../services/notes/notes-service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-medical-timeline',
  imports: [],
  templateUrl: './medical-timeline.html',
  styleUrl: './medical-timeline.css',
})
export class MedicalTimeline {
  @Input() records: MedicalRecord[] = [];
  @Input() patientNotes: NotesResponse[] = [];
  @Output() noteDeleted = new EventEmitter();

  private notesService = inject(NotesService);
  private datePipe = inject(DatePipe);

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

  deleteNote(notesId: string): void {
    this.notesService.deleteNote(notesId).subscribe({
      next: () => {
        console.log("Note successfully deleted");
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

  formatDate(dateString: string): string | null {
    return this.datePipe.transform(dateString, 'dd MMM yyyy');
  }
}
