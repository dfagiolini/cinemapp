
<template>
  <div>
    <div class="pi-grid search-select">
      <div class="p-col">
        <Select
          :options="optionsFilm"
          optionLabel="titolo"
          optionValue="id"
          v-model="searchModel.filmId"
          placeholder="Seleziona Film"
          @change="onFilterChange"
        />
        <Select
          :options="optionsCinema"
          optionLabel="nome"
          optionValue="id"
          v-model="searchModel.cinemaId"
          placeholder="Seleziona Cinema"
          @change="onFilterChange"
        />
      </div>
      <div class="p-col">
        <Select
          :options="optionsOrderBy"
          optionLabel="label"
          optionValue="value"
          v-model="searchModel.orderByColumn"
          placeholder="Ordina per"
          @change="onFilterChange"
        />
      </div>
      <div class="p-col">
        <Select
          :options="optionsAsc"
          optionLabel="label"
          optionValue="value"
          v-model="searchModel.isAsc"
          placeholder="Ordinamento"
          @change="onFilterChange"
        />
      </div>
      <div>
      <DatePicker v-model="searchModel.orario" @dateSelect="fetchTableData()" />
      </div>
    </div>

    <DataTable :value="tableData" :paginator="true" :rows="10">
      <Column field="cinema.nome" header="Cinema"></Column>
      <Column field="film.titolo" header="Film"></Column>
      <Column field="proiezione.dataOraInizio" header="Orario"></Column>
    </DataTable>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import Select from 'primevue/select';
import DataTable from 'primevue/datatable';
import Column from 'primevue/column';
import type { SearchModel } from '@/model/search-model';
import type { SearchResultModel } from '@/model/search-result-model';
import Service from '@/services/service';
import DatePicker from 'primevue/datepicker';
import type {Film} from "@/model/film.ts";
import type {Cinema} from "@/model/cinema.ts";
import { onMounted } from 'vue'



const searchModel = ref<SearchModel>({
  cinemaId: null,
  filmId: null,
  isAsc: true,
  orario: null,
  orderByColumn: 'cinema'
});

const optionsOrderBy = ref([
  { label: 'Cinema', value: 'cinema' },
  { label: 'Orario', value: 'proiezione' },
  { label: 'Film', value: 'film' }
]);

const optionsAsc = ref([
  { label: 'Ascendente', value: true },
  { label: 'Discendente', value: false }
]);

const optionsFilm = ref<Film[]>([]);

const fetchOptionsFilm = async () => {
  try {
    optionsFilm.value = await Service.get<Film[]>('/film');
  }
  catch (error) {
    console.log(error)
  }
}

const optionsCinema = ref<Cinema[]>([]);

const fetchOptionsCinema = async () => {
  try {
    optionsCinema.value = await Service.get<Cinema[]>('/cinema');
  }
  catch (error) {
    console.log(error)
  }
}

const tableData = ref<SearchResultModel[]>([]);

const fetchTableData = async () => {
  try {
    const response = await Service.post<SearchResultModel[]>('/search', searchModel.value);
    tableData.value = response.data;
  } catch (error) {
    console.error('Error fetching table data:', error);
  }
};

const onFilterChange = () => {
  fetchTableData();
};




onMounted(() => {
  fetchTableData();
  fetchOptionsFilm();
  fetchOptionsCinema();
});
</script>

<style scoped>
.p-col {
  margin-bottom: 1rem;
}

.search-select{
  display: flex;
  flex-flow: row;
}
</style>
