import {Proiezione} from './proiezione';
import {Film} from './film';

export interface ProgrammazioneCinemaModel {
  film: Film;
  proiezioni: Proiezione[];
}
