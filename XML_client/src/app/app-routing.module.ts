import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PatentFormComponent } from './p1/patent-form/patent-form.component';
import { ViewA1Component } from './a1/components/view-a1/view-a1.component';
import { CreateA1Component } from './a1/components/create-a1/create-a1.component';
import { ZigFormComponent } from './z1/components/zig-form/zig-form.component';

const routes: Routes = [
  { path: '', component: PatentFormComponent },
  { path: 'a1', component: ViewA1Component },
  { path: 'create-a1', component: CreateA1Component },
  { path: 'z1', component: ZigFormComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
