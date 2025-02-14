import {Component, input} from '@angular/core';
import {InputGroup} from 'primeng/inputgroup';
import {InputGroupAddon} from 'primeng/inputgroupaddon';
import {FormControl, FormsModule} from '@angular/forms';
import {FormGroup} from '@angular/forms';
import {Film} from '../../model/film';
import {FilmService} from '../../services/film.service';

@Component({
  selector: 'app-insert-film',
  imports: [
    InputGroup,
    InputGroupAddon,
    FormsModule
  ],
  templateUrl: './insert-film.component.html',
  styleUrl: './insert-film.component.css'
})
export class InsertFilmComponent {
  film: Film = {copertina: '', durata: 0, genere: '', id: null, regia: '', titolo: ''};
  constructor(private filmService: FilmService) {
  }
  onSubmit() {
    console.log('Form submitted:', this.film);
    this.filmService.postFilm(this.film).subscribe(film => {});
  }

  protected readonly input = input;
}
