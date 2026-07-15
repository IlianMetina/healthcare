import { Component, EventEmitter, inject, Output } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PatientService } from '../../../../services/patients/patient-service';

@Component({
  selector: 'app-add-patient-modale',
  imports: [ReactiveFormsModule],
  templateUrl: './add-patient-modale.html',
  styleUrl: './add-patient-modale.css',
})
export class AddPatientModale {
  @Output() close = new EventEmitter<void>();
  @Output() created = new EventEmitter<void>();

  private patientService = inject(PatientService);

  isSubmitting = false;
  errorMessage: string | null = null;

  genders = ['M', 'F'];

  patientForm = new FormGroup({
    firstName: new FormControl('', {
      validators: [Validators.required],
      nonNullable: true
    }),
    lastName: new FormControl('', {
      validators: [Validators.required],
      nonNullable: true
    }),
    birthDate: new FormControl('', {
      validators: [Validators.required],
      nonNullable: true
    }),
    gender: new FormControl('', {
      validators: [Validators.required],
      nonNullable: true
    }),
    address: new FormControl('', {
      validators: [Validators.required],
      nonNullable: true
    }),
    phoneNumber: new FormControl('', {
      validators: [Validators.required],
      nonNullable: true
    }),
  });

  onSubmit(): void {
    if (this.patientForm.invalid) {
      this.patientForm.markAllAsTouched();
      return;
    }

    console.log(this.patientForm.getRawValue());

    this.isSubmitting = true;
    this.errorMessage = null;

    this.patientService.createPatient(this.patientForm.getRawValue()).subscribe({
      next: () => {
        this.isSubmitting = false;
        this.created.emit();
        this.close.emit();
      },
      error: (err) => {
        this.isSubmitting = false;
        this.errorMessage = "Erreur survenue lors de la création du patient.";
        console.error(err);
      }
    });
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
