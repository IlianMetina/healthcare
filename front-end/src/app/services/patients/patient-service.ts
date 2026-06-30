import { Injectable } from '@angular/core';
import { get } from 'http';
import { Patient } from '../../core/models/patient';

@Injectable({
  providedIn: 'root',
})
export class PatientService {

  private patientsUrl = "http://localhost:8072/api/v1/patients/";

  async getAllPatients(): Promise<Patient[]>{

    try{

      const response = await fetch(this.patientsUrl + 'all');
      if(!response.ok){
        throw new Error("Erreur HTTP : " + response.status);
      }
  
      const data: Patient[] = await response.json();
      console.log("Data reçue : ");
      console.log(data);
  
      return data

    }catch(error){
      console.error("Erreur : " + error);
      throw error;
    }

  }

}
