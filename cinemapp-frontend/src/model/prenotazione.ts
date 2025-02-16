export interface Prenotazione {
  id: number | null;
  numeroBiglietti: number;
  dataPrenotazione: Date;
  proiezioneId: number;
  utenteId: number;
}
