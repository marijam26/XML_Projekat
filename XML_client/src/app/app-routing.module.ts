import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PatentFormComponent } from './p1/patent-form/patent-form.component';
import { ViewA1Component } from './a1/components/view-a1/view-a1.component';
import { CreateA1Component } from './a1/components/create-a1/create-a1.component';
import { ZigFormComponent } from './z1/components/zig-form/zig-form.component';
import {LoginRegistrationComponent} from "./auth/components/login-registration/login-registration.component";
import {HomepageComponent} from "./components/homepage/homepage.component";
import {ViewApplicationsComponent} from "./components/view-applications/view-applications.component";
import {SearchApplicationsComponent} from "./components/search-applications/search-applications.component";

const routes: Routes = [
  { path: 'p1', component: PatentFormComponent },
  { path: 'a1', component: ViewA1Component },
  { path: 'create-a1', component: CreateA1Component },
  { path: 'z1', component: ZigFormComponent },
  { path: '', component: LoginRegistrationComponent},
  { path: 'home', component: HomepageComponent},
  { path: 'view', component: ViewApplicationsComponent},
  { path: 'search', component: SearchApplicationsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
