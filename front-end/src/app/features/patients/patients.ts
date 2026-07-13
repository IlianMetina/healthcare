import {Component, inject, signal, OnInit} from '@angular/core';
import { Patient, StatusConfig, Appointment, MedicalRecord, VitalData } from '../../core/models/patient';
import { PatientListItem } from './components/patient-list-item/patient-list-item';
import { PatientDetail } from './components/patient-detail/patient-detail';
import { VitalCard } from './components/vital-card/vital-card';
import { MedicalTimeline } from './components/medical-timeline/medical-timeline';
import { RightPanel } from './components/right-panel/right-panel';
import {AuthService} from '../../services/auth/auth-service';
import { PatientService } from '../../services/patients/patient-service';
import {AddPatientModale} from './components/add-patient-modale/add-patient-modale';

@Component({
  selector: 'app-patients',
  imports: [PatientListItem, PatientDetail, VitalCard, MedicalTimeline, RightPanel, AddPatientModale],
  templateUrl: './patients.html',
  styleUrl: './patients.css',
})
export class Patients {

  private authService = inject(AuthService);
  private patientService = inject(PatientService);
  patientsList = signal<Patient[]>([]);
  isModalOpen = signal(false);

  openModal(): void{
    this.isModalOpen.set(true);
  }

  closeModal(): void{
    this.isModalOpen.set(false);
  }

  afterPatientCreated(): void{
    //refaire l'appel api pour recharger les patients
  }

  ngOnInit(): void{
    this.patientService.getAllPatientsByDoctor().subscribe({
      next: (patients) => this.patientsList.set(patients),
      error: (err) => console.error("Erreur :", err)
    });

    console.log(this.patientsList());
  }

  searchQuery = '';

  statusConfig: Record<string, StatusConfig> = {
    critical: { label: 'Critique', bgColor: 'var(--status-critical-bg)', textColor: 'var(--status-critical-text)', borderColor: 'var(--status-critical-border)' },
    stable: { label: 'Stable', bgColor: 'var(--status-stable-bg)', textColor: 'var(--status-stable-text)', borderColor: 'var(--status-stable-border)' },
    observation: { label: 'Observation', bgColor: 'var(--status-observation-bg)', textColor: 'var(--status-observation-text)', borderColor: 'var(--status-observation-border)' },
  };

  allPatients: Patient[] = [
    { id: 1, firstName: 'Marie', lastName: 'Dupont', name: 'Marie Dupont', age: 54, dob: '12/03/1970', birthDate: '1970-03-12', gender: 'F', status: 'critical', room: 'B-214', condition: 'Insuffisance cardiaque', avatar: 'MD', phoneNumber: '0612457885', address: '15 Rue de la Paix', visits: 24 },
    { id: 2, firstName: 'Jean-Pierre', lastName: 'Moreau', name: 'Jean-Pierre Moreau', age: 67, dob: '08/11/1957', birthDate: '1957-11-08', gender: 'M', status: 'stable', room: 'A-108', condition: 'Diabète type 2', avatar: 'JM', phoneNumber: '0612457886', address: '42 Avenue Victor Hugo', visits: 18 },
    { id: 3, firstName: 'Isabelle', lastName: 'Laurent', name: 'Isabelle Laurent', age: 41, dob: '22/06/1983', birthDate: '1983-06-22', gender: 'F', status: 'observation', room: 'C-302', condition: 'Hypertension', avatar: 'IL', phoneNumber: '0612457887', address: '8 Boulevard Haussmann', visits: 7 },
    { id: 4, firstName: 'Thomas', lastName: 'Bernard', name: 'Thomas Bernard', age: 29, dob: '05/09/1995', birthDate: '1995-09-05', gender: 'M', status: 'stable', room: 'A-115', condition: 'Fracture tibia', avatar: 'TB', phoneNumber: '0612457888', address: '23 Rue du Commerce', visits: 3 },
    { id: 5, firstName: 'Françoise', lastName: 'Petit', name: 'Françoise Petit', age: 72, dob: '30/01/1952', birthDate: '1952-01-30', gender: 'F', status: 'stable', room: 'B-207', condition: 'Arthrite rhumatoïde', avatar: 'FP', phoneNumber: '0612457889', address: '5 Place de la République', visits: 31 },
  ];

  selectedPatient: Patient = this.allPatients[0];

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
    if (!this.searchQuery.trim()) return this.allPatients;
    const q = this.searchQuery.toLowerCase();
    return this.allPatients.filter(p =>
      (p.name || '').toLowerCase().includes(q) ||
      (p.condition || '').toLowerCase().includes(q)
    );
  }

  selectPatient(patient: Patient): void {
    this.selectedPatient = patient;
  }

  onSearchChange(event: Event): void {
    this.searchQuery = (event.target as HTMLInputElement).value;
  }
}
