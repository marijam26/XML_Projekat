import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AutorskoPravoFormComponent} from "./a1/autorsko-pravo-form/autorsko-pravo-form.component";
import {PatentFormComponent} from "./p1/patent-form/patent-form.component";

const routes: Routes = [
  { path: '', component: PatentFormComponent },
  { path: 'a1', component: AutorskoPravoFormComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
