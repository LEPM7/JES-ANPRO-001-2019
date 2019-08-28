import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FiscaliaComponent } from './fiscalia.component';

describe('FiscaliaComponent', () => {
  let component: FiscaliaComponent;
  let fixture: ComponentFixture<FiscaliaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FiscaliaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FiscaliaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
