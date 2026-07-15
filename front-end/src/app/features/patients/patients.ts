import { Component, inject, signal } from '@angular/core';
import { Patient, StatusConfig, Appointment, MedicalRecord, VitalData } from '../../core/models/patient';
import { PatientListItem } from './components/patient-list-item/patient-list-item';
import { PatientDetail } from './components/patient-detail/patient-detail';
import { VitalCard } from './components/vital-card/vital-card';
import { MedicalTimeline } from './components/medical-timeline/medical-timeline';
import { RightPanel } from './components/right-panel/right-panel';
import { AuthService } from '../../services/auth/auth-service';
import { PatientService } from '../../services/patients/patient-service';
import { NotesService } from '../../services/notes/notes-service';

@Component({
  selector: 'app-patients',
  imports: [PatientListItem, PatientDetail, VitalCard, MedicalTimeline, RightPanel],
  templateUrl: './patients.html',
  styleUrl: './patients.css',
})
export class Patients {

  private authService = inject(AuthService);
  private patientService = inject(PatientService);
  private notesService = inject(NotesService);
  patientsList = signal<Patient[]>([]);
  patientAssessment = signal<string>("Inconnu");


  enrichPatient(p: Patient): Patient {
    const firstName = p.firstName || '';
    const lastName = p.lastName || '';
    const name = `${firstName} ${lastName}`.trim();
    const avatar = `${firstName.charAt(0) || ''}${lastName.charAt(0) || ''}`.toUpperCase();

    let age = 0;
    let dob = '';
    if (p.birthDate) {
      const birthDateObj = new Date(p.birthDate);
      const ageDifMs = Date.now() - birthDateObj.getTime();
      const ageDate = new Date(ageDifMs);
      age = Math.abs(ageDate.getUTCFullYear() - 1970);
      dob = birthDateObj.toLocaleDateString('fr-FR');
    }

    return {
      ...p,
      name,
      avatar,
      age: p.age || age,
      dob: p.dob || dob,
      condition: p.condition || 'Non renseigné',
      room: p.room || 'N/A',
      status: p.status || 'stable'
    };
  }

  ngOnInit(): void {
    this.patientService.getAllPatientsByDoctor().subscribe({
      next: (patients) => {
        const enriched = patients.map(p => this.enrichPatient(p));
        this.patientsList.set(enriched);

        if (enriched.length > 0 && !this.selectedPatient) {
          this.selectPatient(enriched[0]);
        }
      },
      error: (err) => console.error("Erreur :", err)
    });
  }

  searchQuery = '';

  statusConfig: Record<string, StatusConfig> = {
    NONE: { label: 'Aucun risque', bgColor: 'var(--status-stable-bg)', textColor: 'var(--status-stable-text)', borderColor: 'var(--status-stable-border)' },
    BORDERLINE: { label: 'Risque limité', bgColor: 'var(--status-observation-bg)', textColor: 'var(--status-observation-text)', borderColor: 'var(--status-observation-border)' },
    IN_DANGER: { label: 'En danger', bgColor: 'var(--status-critical-bg)', textColor: 'var(--status-critical-text)', borderColor: 'var(--status-critical-border)' },
    EARLY_ONSET: { label: 'Apparition précoce', bgColor: 'var(--status-critical-bg)', textColor: 'var(--status-critical-text)', borderColor: 'var(--status-critical-border)' },
    // Gardés pour fallback
    critical: { label: 'Critique', bgColor: 'var(--status-critical-bg)', textColor: 'var(--status-critical-text)', borderColor: 'var(--status-critical-border)' },
    stable: { label: 'Stable', bgColor: 'var(--status-stable-bg)', textColor: 'var(--status-stable-text)', borderColor: 'var(--status-stable-border)' },
    observation: { label: 'Observation', bgColor: 'var(--status-observation-bg)', textColor: 'var(--status-observation-text)', borderColor: 'var(--status-observation-border)' },
    'Chargement...': { label: 'Analyse...', bgColor: '#f3f4f6', textColor: '#6b7280', borderColor: '#d1d5db' }
  };



  selectedPatient: Patient | null = null;

  appointments: Appointment[] = [
    { time: '08:30', name: 'Marie Dupont', type: 'Suivi cardiologie', done: true },
    { time: '09:15', name: 'Karim Benali', type: 'Consultation initiale', done: true },
    { time: '10:00', name: 'Jean-Pierre Moreau', type: 'Bilan glycémique', done: false, current: true },
    { time: '11:30', name: 'Sophie Renard', type: 'Post-opératoire', done: false },
    { time: '14:00', name: 'Isabelle Laurent', type: 'Contrôle tension', done: false },
    { time: '15:45', name: 'Paul Girard', type: 'Résultats analyses', done: false },
  ];

  records: MedicalRecord[] = [
    { date: '02 Jul 2026', type: 'Ordonnance', label: 'Metformine 850mg — 3 mois', icon: 'pill' },
    { date: '18 Jun 2026', type: 'Analyse', label: 'Bilan biologique complet — Normal', icon: 'check' },
    { date: '05 Jun 2026', type: 'Imagerie', label: 'ECG — Rythme sinusal normal', icon: 'activity' },
    { date: '20 May 2026', type: 'Consultation', label: 'Ajustement traitement antihypertenseur', icon: 'file' },
    { date: '08 Apr 2026', type: 'Hospitalisation', label: 'Admit. urgence — 3 jours — Sortie stable', icon: 'alert' },
  ];

  vitals: VitalData[] = [
    { icon: 'ri-heart-pulse-line', label: 'Fréq. cardiaque', value: '76', unit: 'bpm', trend: 'stable', data: [72, 75, 71, 80, 78, 74, 76, 73, 79, 77, 75, 78], color: '#ef4444' },
    { icon: 'ri-pulse-line', label: 'Tension artérielle', value: '121/80', unit: 'mmHg', trend: 'stable', data: [120, 118, 122, 125, 119, 121, 123, 118, 120, 122, 121, 119], color: '#3b82f6' },
    { icon: 'ri-temp-hot-line', label: 'Température', value: '37.2', unit: '°C', trend: 'stable', data: [37.1, 37.3, 37.0, 37.4, 37.2, 37.1, 37.3, 37.2, 37.0, 37.1, 37.2, 37.3], color: '#f59e0b' },
    { icon: 'ri-windy-line', label: 'SpO₂', value: '98', unit: '%', trend: 'stable', data: [97, 98, 98, 99, 98, 97, 98, 99, 98, 98, 97, 98], color: '#0d9488' },
  ];

  get filteredPatients(): Patient[] {
    const patients = this.patientsList();
    if (!this.searchQuery.trim()) return patients;
    const q = this.searchQuery.toLowerCase();
    return patients.filter(p =>
      (p.name || '').toLowerCase().includes(q) ||
      (p.condition || '').toLowerCase().includes(q)
    );
  }

  selectPatient(patient: Patient): void {
    this.selectedPatient = patient;
    patient.status = 'Chargement...';

    this.patientService.getPatientAssessment(patient.patientId).subscribe({
      next: (assessment) => {
        patient.status = assessment;
      },
      error: (err) => {
        console.error(err);
        patient.status = 'stable';
      }
    });
  }

  onSearchChange(event: Event): void {
    this.searchQuery = (event.target as HTMLInputElement).value;
  }
}
