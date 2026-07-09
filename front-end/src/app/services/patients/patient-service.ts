import { inject, Injectable } from '@angular/core';
import { get } from 'http';
import { Patient } from '../../core/models/patient';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class PatientService {

  private patientsUrl = "http://localhost:8072/api/v1/patients/";
  private http = inject(HttpClient);

  async getAllPatients(): Promise<Patient[]> {

    try {

      const response = await fetch(this.patientsUrl + 'all');
      if (!response.ok) {
        throw new Error("Erreur HTTP : " + response.status);
      }

      const data: Patient[] = await response.json();
      console.log("Data reçue : ");
      console.log(data);

      return data

    } catch (error) {
      console.error("Erreur : " + error);
      throw error;
    }

  }

  getAllPatientsByDoctor(): Observable<Patient[]> {
    return this.http.get<Patient[]>("api/v1/patients")
  }

}
