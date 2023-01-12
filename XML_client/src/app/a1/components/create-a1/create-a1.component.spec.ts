import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateA1Component } from './create-a1.component';

describe('CreateA1Component', () => {
  let component: CreateA1Component;
  let fixture: ComponentFixture<CreateA1Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateA1Component ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateA1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
