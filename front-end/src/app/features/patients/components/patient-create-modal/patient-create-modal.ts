import {Component, EventEmitter, inject, Output} from '@angular/core';
import {ReactiveFormsModule, FormControl, FormGroup, Validators} from '@angular/forms';
import {PatientService} from '../../../../services/patients/patient-service';

@Component({
  selector: 'app-patient-create-modal',
  imports: [ReactiveFormsModule],
  templateUrl: './patient-create-modal.html',
  styleUrl: './patient-create-modal.css',
})
export class PatientCreateModal {

  private patientService = inject(PatientService);

  @Output() close = new EventEmitter<void>();
  @Output() created = new EventEmitter<void>();

  isSubmitting = false;
  errorMessage: string | null = null;

  genders = ["HOMME", "FEMME"];

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
    if(this.patientForm.invalid){
      this.patientForm.markAsTouched();
      return;
    }

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
        this.errorMessage = "Une erreur est survenue lors de la création du patient.";
        console.error(err);
      }
    })
  }

  onCancel(): void {
    this.close.emit();
  }

  onBackdropClick(event: MouseEvent): void {
    if(event.target === event.currentTarget){
      this.close.emit();
    }
  }


}
