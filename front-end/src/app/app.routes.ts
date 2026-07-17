import { Routes } from '@angular/router';
import { Home } from './features/home/home';
import { Patients } from './features/patients/patients';
import { Notes } from './features/notes/notes';
import { Layout } from './shared/components/layout/layout';
import { Login } from './features/auth/login/login';
import { About } from './features/about/about';
import { authGuard } from './services/auth/auth-guard';

export const routes: Routes = [
    { path: "login", component: Login },
    {
        path: '', component: Layout, canActivate: [authGuard], children: [
            { path: '', component: Home },
            { path: 'patients', component: Patients },
            { path: 'notes', component: Notes },
            { path: 'about', component: About }
            // {path: "register", component: Register},
        ]
    },
];