import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { NotesResponse } from '../../core/models/patient';

@Injectable({
  providedIn: 'root',
})
export class NotesService {

  private http = inject(HttpClient);
  private notesUrl = "http://localhost:8072/api/v1/notes/";

  getPatientNotes(patientId: string): Observable<NotesResponse[]> {
    return this.http.get<NotesResponse[]>(this.notesUrl + "patient/" + patientId);
  }

  addPatientNote(patientId: string, data: string): Observable<string> {
    const body = {
      patientId: patientId,
      remarks: data
    };
    return this.http.post<string>(this.notesUrl + "create", body);
  }

  deleteNote(notesId: string): Observable<void> {
    return this.http.delete<void>(this.notesUrl + "delete/" + notesId, { withCredentials: true });
  }
}
