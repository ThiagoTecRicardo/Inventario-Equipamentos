import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EquipamentoList } from './equipamento-list';

describe('EquipamentoList', () => {
  let component: EquipamentoList;
  let fixture: ComponentFixture<EquipamentoList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EquipamentoList],
    }).compileComponents();

    fixture = TestBed.createComponent(EquipamentoList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
