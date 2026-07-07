import { Component } from '@angular/core';
import { Avatar } from '../avatar/avatar';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  imports: [Avatar, RouterLink],
  templateUrl: './sidebar.html',
  styleUrl: './sidebar.css',
})
export class Sidebar {

  username = "JC";
}
