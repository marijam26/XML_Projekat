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
import { LoginRegistrationComponent } from './auth/components/login-registration/login-registration.component';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomepageComponent } from './components/homepage/homepage.component';
import { ViewApplicationsComponent } from './components/view-applications/view-applications.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SearchApplicationsComponent } from './components/search-applications/search-applications.component';
import { AdvancedSearchComponent } from './components/advanced-search/advanced-search.component';
import { PatentXonomyComponent } from './p1/patent-xonomy/patent-xonomy.component';
import 'xml2js';

@NgModule({
  declarations: [
    AppComponent,
    PatentFormComponent,
    AutorskoPravoFormComponent,
    ViewA1Component,
    CreateA1Component,
    ZigFormComponent,
    LoginRegistrationComponent,
    HomepageComponent,
    ViewApplicationsComponent,
    NavbarComponent,
    SearchApplicationsComponent,
    AdvancedSearchComponent,
    PatentXonomyComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    FormsModule,
    ToastrModule.forRoot({
      timeOut: 3000,
      positionClass: 'toast-top-center',
      preventDuplicates: true,
    }),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
