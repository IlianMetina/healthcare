import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class NotesService {

  private http = inject(HttpClient);
  private notesUrl = "http://localhost:8072/api/v1/notes/";

  getPatientNotes(patientId: number): Observable<string[]> {
    return this.http.get<string[]>(this.notesUrl + patientId);
  }

  addPatientNote(patientId: string, data: string): Observable<string> {
    const body = {
      patientId: patientId,
      remarks: data
    };
    return this.http.post<string>(this.notesUrl + "create", body);
  }
}
