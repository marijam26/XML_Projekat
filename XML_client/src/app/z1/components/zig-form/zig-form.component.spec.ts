import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZigFormComponent } from './zig-form.component';

describe('ZigFormComponent', () => {
  let component: ZigFormComponent;
  let fixture: ComponentFixture<ZigFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZigFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ZigFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
