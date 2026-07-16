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
    const first = this.firstName?.charAt(0)?.toUpperCase() || 'I';
    const last = this.lastName?.charAt(0)?.toUpperCase() || 'U';
    return first + last;
  }
}