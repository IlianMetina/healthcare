import { Component, inject } from '@angular/core';
import { ReactiveFormsModule, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../../services/auth/auth-service';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  private authService = inject(AuthService);

  loginForm = new FormGroup({
    email: new FormControl("", {
      validators: [
        Validators.required,
        Validators.email
      ],
      nonNullable: true
    }
    ),
    password: new FormControl("", {
      validators: [
        Validators.required,
        Validators.minLength(8)
      ],
      nonNullable: true
    }
    ),
  });

  onSubmit(): void {
    console.log(this.loginForm.value);
    if (this.loginForm.invalid) {
      this.loginForm.markAllAsTouched();
      return;
    }

    console.log("ça marche!");
    this.authService.connect(this.loginForm.getRawValue()).subscribe({
      next: response => {
        console.log(response);
      }
    });

  }

  get email() {
    return this.loginForm.controls.email;
  }

  get password() {
    return this.loginForm.controls.password;
  }
}
