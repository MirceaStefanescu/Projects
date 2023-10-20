import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RawDataComponent } from './rawData.component';

describe('DashboardComponent', () => {
  let component: RawDataComponent;
  let fixture: ComponentFixture<RawDataComponent>;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RawDataComponent],
    }).compileComponents();
    fixture = TestBed.createComponent(RawDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
