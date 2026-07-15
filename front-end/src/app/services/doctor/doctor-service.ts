import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Doctor } from '../../core/models/patient';

@Injectable({
  providedIn: 'root',
})
export class DoctorService {

  private http = inject(HttpClient);
  private doctorUrl = "http://localhost:8072/api/v1/doctor/"

  getMyInfo(): Observable<Doctor> {
    return this.http.get<Doctor>(this.doctorUrl + "my-info");
  }
}
