import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FiscaliasComponent } from './fiscalias.component';

describe('FiscaliasComponent', () => {
  let component: FiscaliasComponent;
  let fixture: ComponentFixture<FiscaliasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FiscaliasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FiscaliasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
