import { inject, Injectable } from '@angular/core';
import { get } from 'http';
import { CreatePatientRequest, Patient } from '../../core/models/patient';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Patients } from '../../features/patients/patients';

@Injectable({
  providedIn: 'root',
})
export class PatientService {

  private patientsUrl = "http://localhost:8072/api/v1/patients/";
  private assessmentUrl = "http://localhost:8072/api/v1/assessment/patient/";
  private http = inject(HttpClient);

  getAllPatientsByDoctor(): Observable<Patient[]> {
    return this.http.get<Patient[]>(this.patientsUrl + 'my-patients');
  }

  getPatientAssessment(patientId: number): Observable<string> {
    return this.http.get<string>(this.assessmentUrl + patientId);
  }

  createPatient(data: CreatePatientRequest): Observable<CreatePatientRequest> {
    return this.http.post<CreatePatientRequest>(this.patientsUrl + 'create', data);
  }

}
