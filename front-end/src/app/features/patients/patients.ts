import { Component, inject } from '@angular/core';
import { Card } from '../../shared/components/card/card';
import { AuthService } from '../../services/auth/auth-service';
import { PatientService } from '../../services/patients/patient-service';
import { Patient } from '../../core/models/patient';

@Component({
  selector: 'app-patients',
  imports: [Card],
  templateUrl: './patients.html',
  styleUrl: './patients.css',
})
export class Patients {
  
  private allPatients: Patient[] = [];

  private authService = inject(AuthService);
  private patientSerivce = inject(PatientService);

  async loadPatients(){
    try{
      this.allPatients = await this.patientSerivce.getAllPatients();
      
    }catch(error){
      console.error("Erreur :", error);
    }
  }
}
