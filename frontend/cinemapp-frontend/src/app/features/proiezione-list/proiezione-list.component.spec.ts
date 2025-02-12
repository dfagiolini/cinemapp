import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProiezioneListComponent } from './proiezione-list.component';

describe('ProiezioneListComponent', () => {
  let component: ProiezioneListComponent;
  let fixture: ComponentFixture<ProiezioneListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProiezioneListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProiezioneListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
