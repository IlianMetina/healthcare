import { Component, EventEmitter, inject, Output, Input } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { NotesService } from '../../../../services/notes/notes-service';

@Component({
  selector: 'app-add-notes-modal',
  imports: [ReactiveFormsModule],
  templateUrl: './add-notes-modal.html',
  styleUrl: './add-notes-modal.css',
})
export class AddNotesModal {

  @Output() close = new EventEmitter<void>();
  @Output() created = new EventEmitter<void>();
  @Input() patientId!: string;

  isSubmitting = false;
  errorMessage: string | null = null;
  private notesService = inject(NotesService);

  notesForm = new FormGroup({
    notes: new FormControl('', {
      validators: [Validators.required],
      nonNullable: true
    }),
  });


  onSubmit(): void {
    this.notesService.addPatientNote(this.patientId, this.notesForm.getRawValue().notes).subscribe({
      next: () => {
        this.created.emit();
        this.close.emit();
        console.log("addPatientNote");
      },
      error: (error: any) => {
        this.errorMessage = error.message;
      }
    })
  }

  onClick() {
    console.log("test");
  }

  onCancel(): void {
    this.close.emit();
  }

  onBackdropClick(event: MouseEvent): void {
    if (event.target == event.currentTarget) {
      this.close.emit();
    }
  }

}
