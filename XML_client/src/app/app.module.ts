import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { PatentFormComponent } from './p1/patent-form/patent-form.component';
import { FormsModule } from '@angular/forms';
import { AutorskoPravoFormComponent } from './a1/components/autorsko-pravo-form/autorsko-pravo-form.component';
import { HttpClientModule } from '@angular/common/http';
import { ViewA1Component } from './a1/components/view-a1/view-a1.component';
import { CreateA1Component } from './a1/components/create-a1/create-a1.component';
import { ZigFormComponent } from './z1/components/zig-form/zig-form.component';

@NgModule({
  declarations: [
    AppComponent,
    PatentFormComponent,
    AutorskoPravoFormComponent,
    ViewA1Component,
    CreateA1Component,
    ZigFormComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
