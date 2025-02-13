import { Component, OnInit } from '@angular/core';
import { CinemaService } from '../../services/cinema.service';
import {NgForOf} from '@angular/common';
import {Card} from 'primeng/card';
import {Button} from 'primeng/button';

@Component({
  selector: 'app-cinema-list',
  templateUrl: './cinema-list.component.html',
  imports: [
    NgForOf,
    Card,
    Button
  ]
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
