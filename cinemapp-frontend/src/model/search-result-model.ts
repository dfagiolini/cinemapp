import type {Proiezione} from './proiezione';
import type {Cinema} from './cinema';
import type {Sala} from './sala';
import type {Film} from './film';

export interface SearchResultModel {
  film: Film;
  proiezione: Proiezione;
  cinema: Cinema;
  sala: Sala;
}
