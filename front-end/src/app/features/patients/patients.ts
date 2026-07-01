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
  
  selectedPatient: Patient | null = null;
  isModalOpen = false;

  allPatients: Patient[] = [
    {
      firstName: "Test",
      lastName: "ReTest",
      birthDate: "1995-12-12",
      gender: "M",
      phoneNumber: "0612457885",
      address: "123 Rue de la République",
      visits: 14
    },
    {
      firstName: "Ernest",
      lastName: "SestRe",
      birthDate: "2000-12-12",
      gender: "F",
      phoneNumber: "0612457885",
      address: "123 Rue de la République",
      visits: 14
    },
    {
      firstName: "Past",
      lastName: "RePast",
      birthDate: "2003-12-12",
      gender: "M",
      phoneNumber: "0612457885",
      address: "123 Rue de la République",
      visits: 14
    }
  ];
  
  private authService = inject(AuthService);
  private patientSerivce = inject(PatientService);

  ngOnInit(): void{
    this.loadPatients();
  }

  getAge(isoDate: string): number{
    const birthDate = new Date(isoDate);
    const today = new Date();

    let age = today.getFullYear() - birthDate.getFullYear();
    const monthDiff = today.getMonth() - birthDate.getMonth();

    if(monthDiff < 0 || (monthDiff == 0 && today.getDate() < birthDate.getDate())){
      age;
    }

    return age;
  }

  isMaleOrFemale(gender: string): string{
    if(gender == 'M'){
      return "Homme";
    }else if(gender == 'F'){
      return "Femme";
    }else{
      return "Inconnu";
    }
  }

  openPatientDetails(patient: Patient): void{
    this.selectedPatient = patient;
    this.isModalOpen = true;
  }

  closeModal(){
    this.isModalOpen = false;
    this.selectedPatient = null;
  }

  async loadPatients(){
    try{
      this.allPatients = await this.patientSerivce.getAllPatients();
    }catch(error){
      console.error("Erreur :", error);
    }
  }

  
}
