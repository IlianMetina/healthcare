export interface Patient {
  id: number;
  firstName: string;
  lastName: string;
  birthDate: string;
  gender: string;
  address: string;
  phoneNumber: string;
  visits: number;

  // Fields from the maquette
  age?: number;
  dob?: string;
  status?: string;
  room?: string;
  condition?: string;
  avatar?: string;
  name?: string;
}

export interface CreatePatientRequest {
  firstName: string;
  lastName: string;
  birthDate: string;
  gender: string;
  address: string;
  phoneNumber: string;
}

export interface StatusConfig {
  label: string;
  bgColor: string;
  textColor: string;
  borderColor: string;
}

export interface Appointment {
  time: string;
  name: string;
  type: string;
  done: boolean;
  current?: boolean;
}

export interface MedicalRecord {
  date: string;
  type: string;
  label: string;
  icon: string;
}

export interface VitalData {
  icon: string;
  label: string;
  value: string;
  unit: string;
  trend: 'up' | 'down' | 'stable';
  data: number[];
  color: string;
}

export interface DayStat {
  label: string;
  value: string;
  icon: string;
}
