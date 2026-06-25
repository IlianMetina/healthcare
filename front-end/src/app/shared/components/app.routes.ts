import { Routes } from '@angular/router';
import { Home } from '../../features/home/home';
import { Patients } from '../../features/patients/patients';
import { Notes } from '../../features/notes/notes';

export const routes: Routes = [
    {path: '', component: Home},
    {path: 'patients', component: Patients},
    {path: 'notes', component: Notes}
];
