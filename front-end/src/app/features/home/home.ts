import { Component, inject } from '@angular/core';
import { Card } from '../../shared/components/card/card';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {

  private router = inject(Router);

  redirectToPatients(){
    this.router.navigate(['patients']);
  }

  redirectToAbout(){
    this.router.navigate(['about']);
  }
}
