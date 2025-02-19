import type {Cinema} from './cinema';
import type {Proiezione} from './proiezione';

export interface ProgrammazioneFilm {
  cinema: Cinema;
  proiezioni: Proiezione[];
}
