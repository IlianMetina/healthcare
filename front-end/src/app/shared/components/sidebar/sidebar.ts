import { Component, inject, signal } from '@angular/core';
import { Avatar } from '../avatar/avatar';
import { Router, RouterLink } from '@angular/router';
import { DoctorService } from '../../../services/doctor/doctor-service';
import { AuthService } from '../../../services/auth/auth-service';

@Component({
  selector: 'app-sidebar',
  imports: [Avatar, RouterLink],
  templateUrl: './sidebar.html',
  styleUrl: './sidebar.css',
})
export class Sidebar {

  firstName = signal("Invité");
  lastName = signal("");
  private doctorService = inject(DoctorService);
  private authService = inject(AuthService);
  private router = inject(Router);

  ngOnInit(): void {
    this.doctorService.getMyInfo().subscribe({
      next: (doctor) => {
        this.firstName.set(doctor.firstName);
        this.lastName.set(doctor.lastName);
      }
    })
  }

  logout() {
    this.authService.logout().subscribe({
      next: () => {
        this.firstName.set("Invité");
        this.lastName.set("");
        this.router.navigate(['/login']);
      },
      error: (err) => {
        console.error("Erreur :", err);
      }
    })
  }
}
