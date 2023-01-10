import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { PatentFormComponent } from './p1/patent-form/patent-form.component';
import {FormsModule} from "@angular/forms";
import { AutorskoPravoFormComponent } from './a1/autorsko-pravo-form/autorsko-pravo-form.component';

@NgModule({
  declarations: [
    AppComponent,
    PatentFormComponent,
    AutorskoPravoFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
