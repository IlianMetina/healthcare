import { Component, EventEmitter, inject, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Patient } from '../../../../core/models/patient';
import { PatientService } from '../../../../services/patients/patient-service';

@Component({
  selector: 'app-edit-patient-modal',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './edit-patient-modal.html',
  styleUrl: './edit-patient-modal.css'
})
export class EditPatientModal implements OnInit {
  @Input() patient!: Patient;
  @Output() close = new EventEmitter<void>();
  @Output() patientUpdated = new EventEmitter<Patient>();

  private patientService = inject(PatientService);

  editForm = new FormGroup({
    firstName: new FormControl('', { validators: [Validators.required], nonNullable: true }),
    lastName: new FormControl('', { validators: [Validators.required], nonNullable: true }),
    birthDate: new FormControl('', { validators: [Validators.required], nonNullable: true }),
    gender: new FormControl('', { validators: [Validators.required], nonNullable: true }),
    address: new FormControl('', { validators: [Validators.required], nonNullable: true }),
    phoneNumber: new FormControl('', { validators: [Validators.required], nonNullable: true }),
  });

  isSubmitting = false;

  ngOnInit(): void {
    if (this.patient) {
      this.editForm.patchValue({
        firstName: this.patient.firstName,
        lastName: this.patient.lastName,
        birthDate: this.patient.birthDate,
        gender: this.patient.gender,
        address: this.patient.address,
        phoneNumber: this.patient.phoneNumber
      });
    }
  }

  onSubmit(): void {
    if (this.editForm.invalid) {
      this.editForm.markAllAsTouched();
      return;
    }

    this.isSubmitting = true;
    const updatedData = this.editForm.getRawValue();

    this.patientService.updatePatient(this.patient.patientId, updatedData).subscribe({
      next: (updatedPatient) => {
        this.isSubmitting = false;
        // Merge the original patient with the updated fields so we emit a full Patient object
        this.patientUpdated.emit({ ...this.patient, ...updatedData } as unknown as Patient);
        this.close.emit();
      },
      error: (err) => {
        console.error('Erreur lors de la mise à jour du patient:', err);
        this.isSubmitting = false;
      }
    });
  }

  closeModal(): void {
    this.close.emit();
  }
}
