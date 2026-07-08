import { Component } from '@angular/core';
import { Avatar } from '../avatar/avatar';
import { CurveCard } from '../curve-card/curve-card';

@Component({
  selector: 'app-patient-details',
  imports: [Avatar, CurveCard],
  templateUrl: './patient-details.html',
  styleUrl: './patient-details.css',
})
export class PatientDetails {

}
