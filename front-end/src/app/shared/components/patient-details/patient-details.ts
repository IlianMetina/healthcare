import { Component, inject, signal } from '@angular/core';
import { Avatar } from '../avatar/avatar';
import { CurveCard } from '../curve-card/curve-card';
import { DoctorService } from '../../../services/doctor/doctor-service';

@Component({
  selector: 'app-patient-details',
  imports: [Avatar, CurveCard],
  templateUrl: './patient-details.html',
  styleUrl: './patient-details.css',
})
export class PatientDetails {

  firstName = signal<string>("Médecin");
  lastName = signal<string>("traitant");
  private doctorService = inject(DoctorService);

  ngOnInit(): void {
    this.doctorService.getMyInfo().subscribe({
      next: (doctor) => {
        this.firstName.set(doctor.firstName);
        this.lastName.set(doctor.lastName);
      }
    })
  }

}
