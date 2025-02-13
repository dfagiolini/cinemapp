import { Component, OnInit } from '@angular/core';
import { CinemaService } from '../../services/cinema.service';

@Component({
  selector: 'app-cinema-list',
  templateUrl: './cinema-list.component.html',
})
export class CinemaListComponent implements OnInit {
  cinemas: any[] = [];

  constructor(private cinemaService: CinemaService) {}

  ngOnInit(): void {
    this.cinemaService.getCinemas().subscribe(data => {
      this.cinemas = data;
    });
  }
}
