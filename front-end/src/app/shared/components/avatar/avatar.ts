import { Component, inject, Input } from '@angular/core';

@Component({
  selector: 'app-avatar',
  imports: [],
  templateUrl: './avatar.html',
  styleUrl: './avatar.css',
})
export class Avatar {

  @Input() firstName: string = "Utilisateur";
  @Input() lastName: string = "Invité";


  get getInitials(): string {
    if (this.firstName != "Utilisateur") {
      const firstInitial = this.firstName[0];
      const secondInitial = this.lastName[0];
      const initials = firstInitial + secondInitial;
      return initials;
    }

    return "IU";
  }
}