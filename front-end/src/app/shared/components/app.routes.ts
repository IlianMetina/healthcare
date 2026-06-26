import { Routes } from '@angular/router';
import { Home } from '../../features/home/home';
import { Patients } from '../../features/patients/patients';
import { Notes } from '../../features/notes/notes';
import { Layout } from './layout/layout';
import { Login } from '../../features/auth/login/login';
import { Register } from '../../features/auth/register/register';

export const routes: Routes = [
    {path: "login", component: Login},
    {path: "register", component: Register},
    {path: '', component: Layout, children: [
        {path: '', component: Home},
        {path: 'patients', component: Patients},
        {path: 'notes', component: Notes}
    ]},
];