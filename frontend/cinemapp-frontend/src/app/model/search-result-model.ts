import {Proiezione} from './proiezione';
import {Cinema} from './cinema';
import {Sala} from './sala';
import {Film} from './film';

export interface SearchResultModel {
  film: Film;
  proiezione: Proiezione;
  cinema: Cinema;
  sala: Sala;
}
