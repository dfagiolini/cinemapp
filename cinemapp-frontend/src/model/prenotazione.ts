export interface Prenotazione {
  id: number | null;
  numeroBiglietti: number;
  dataPrenotazione: string | null;
  proiezioneId: number;
  utenteId: number | null;
}
