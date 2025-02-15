import {Cinema} from './cinema';
import {Proiezione} from './proiezione';

export interface ProgrammazioneFilm {
  cinema: Cinema;
  proiezioni: Proiezione[];
}
