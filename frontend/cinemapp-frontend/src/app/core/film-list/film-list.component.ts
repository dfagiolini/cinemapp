import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-film-list',
  templateUrl: './film-list.component.html',
  styleUrls: ['./film-list.component.scss'],
})
export class FilmListComponent implements OnInit {
  films = [
    {
      logo: 'assets/movielogo1.png',
      title: 'Film 1',
      date: '05/02/2025',
      sala: 'Sala 1',
      orari: ['15:00', '18:00', '21:00'],
    },
    {
      logo: 'assets/movielogo2.png',
      title: 'Film 2',
      date: '06/02/2025',
      sala: 'Sala 2',
      orari: ['16:00', '19:00'],
    },
  ];

  constructor(){}

  ngOnInit(): void {
  }
}
