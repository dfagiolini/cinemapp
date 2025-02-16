export interface SearchModel {
  filmId: number | null;
  cinemaId: number | null;
  orario: Date | null;
  isAsc: boolean;
  orderByColumn: string | null;
}
