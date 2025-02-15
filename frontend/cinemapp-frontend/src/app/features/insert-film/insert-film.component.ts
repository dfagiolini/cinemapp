import {Component, input, OnInit} from '@angular/core';
import {InputGroup} from 'primeng/inputgroup';
import {InputGroupAddon} from 'primeng/inputgroupaddon';
import {FormBuilder, FormControl, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {FormGroup} from '@angular/forms';
import {Film} from '../../model/film';
import {FilmService} from '../../services/film.service';
import {NgForOf} from '@angular/common';


@Component({
  selector: 'app-insert-film',
  imports: [
    InputGroup,
    InputGroupAddon,
    FormsModule,
    ReactiveFormsModule,
    NgForOf
  ],
  templateUrl: './insert-film.component.html',
  styleUrl: './insert-film.component.css'
})
export class InsertFilmComponent implements OnInit {
  film: Film = {copertina: '', durata: 0, genere: '', id: null, regia: '', titolo: ''};
  selectedFilm!: Film;
  filmForm!: FormGroup;
  films: Film[] = [];

  constructor(private filmService: FilmService, private fb: FormBuilder) {

  }

  ngOnInit() {
    this.filmForm = this.fb.group({
      titolo: [''],
      durata: [''],
      regia: [''],
      genere: [''],
      copertina: ['']
    });
    this.filmService.getFilms().subscribe((value: Film[]) => {
      this.films = value
    });
  }

  onFilmSelect(event: Event): void {
    const selectElement = event.target as HTMLSelectElement;
    const selectedIndex = selectElement.selectedIndex;
    this.selectedFilm = this.films[selectedIndex];
    this.filmForm.patchValue({
      titolo: this.selectedFilm.titolo,
      durata: this.selectedFilm.durata,
      regia: this.selectedFilm.regia,
      genere: this.selectedFilm.genere,
      copertina: this.selectedFilm.copertina
    });
  }

  onUpdate() {
    const updatedFilm = this.filmForm.value as Film;
    console.log(updatedFilm)
    this.filmService.updateFilm(updatedFilm).subscribe(value => {});
  }

  onDelete() {
    const id = this.selectedFilm.id;
    console.log(this.selectedFilm)
    if (id != null)
      this.filmService.deleteFilmById(id).subscribe(value => {});
  }

  onSubmit() {
    this.filmService.postFilm(this.film).subscribe(value => {
    });
  }

}
