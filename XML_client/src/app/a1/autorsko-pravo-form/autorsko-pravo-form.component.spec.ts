import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AutorskoPravoFormComponent } from './autorsko-pravo-form.component';

describe('AutorskoPravoFormComponent', () => {
  let component: AutorskoPravoFormComponent;
  let fixture: ComponentFixture<AutorskoPravoFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AutorskoPravoFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AutorskoPravoFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
