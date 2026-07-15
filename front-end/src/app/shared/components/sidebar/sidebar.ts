import { Component, inject, signal } from '@angular/core';
import { Avatar } from '../avatar/avatar';
import { RouterLink } from '@angular/router';
import { DoctorService } from '../../../services/doctor/doctor-service';

@Component({
  selector: 'app-sidebar',
  imports: [Avatar, RouterLink],
  templateUrl: './sidebar.html',
  styleUrl: './sidebar.css',
})
export class Sidebar {

  firstName = signal("");
  lastName = signal("");

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
