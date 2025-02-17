<template>
  <div class="CRUD-Table">
    <Button label="Aggiungi" icon="pi pi-plus" @click="addNew" />
    <DataTable
      v-model:editingRows="editingRows"
      :value="tableData"
      editMode="row"
      dataKey="id"
      @row-edit-save="onRowEditSave"
    >
      <Column field="filmId" header="Film">
        <template #editor="slotProps">
          <Dropdown v-model="slotProps.data.filmId" :options="film" optionLabel="titolo" optionValue="id" />
        </template>
        <template #body="slotProps">
          <span>{{ getFilmName(slotProps.data.filmId) }}</span>
        </template>
      </Column>
      <Column field="salaId" header="Sala">
        <template #editor="slotProps">
          <Dropdown v-model="slotProps.data.salaId" :options="sale" optionLabel="numero" optionValue="id" />
        </template>
        <template #body="slotProps">
          <span>{{ getSalaNumber(slotProps.data.salaId) }}</span>
        </template>
      </Column>
      <Column field="dataOraInizio" header="Data Ora Inizio">
        <template #editor="slotProps">
          <Calendar v-model="slotProps.data.dataOraInizio" showTime />
        </template>
      </Column>
      <Column field="dataOraFine" header="Data Ora Fine">
        <template #editor="slotProps">
          <Calendar v-model="slotProps.data.dataOraFine" showTime />
        </template>
      </Column>
      <Column field="prezzo" header="Prezzo">
        <template #editor="slotProps">
          <InputNumber v-model="slotProps.data.prezzo" mode="currency" currency="EUR" locale="it-IT" />
        </template>
      </Column>
      <Column :rowEditor="true" style="width: 10%; min-width: 8rem" bodyStyle="text-align:center"></Column>
      <Column bodyStyle="text-align:center">
        <template #body="slotProps">
          <Button label="Elimina" icon="pi pi-trash" @click="() => deleteRow(slotProps.data)" />
        </template>
      </Column>
    </DataTable>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import type { Proiezione } from '@/model/proiezione.ts';
import type { Film } from '@/model/film.ts';
import type { Sala } from '@/model/sala.ts';
import Service from "@/services/service.ts";
import { Button, DataTable, Column, Dropdown, Calendar, InputNumber } from "primevue";

const tableData = ref<Proiezione[]>([]);
const editingRows = ref([]);
const film = ref<Film[]>([]);
const sale = ref<Sala[]>([]);
const filmOptions = ref([]);
const salaOptions = ref([]);

// Fetch initial data
onMounted(() => {
  fetchProiezioni();
  fetchFilm();
  fetchSale();
});

const fetchProiezioni = async () => {
  try {
    tableData.value = await Service.get<Proiezione[]>('/proiezioni');
  } catch (err) {
    console.error('Failed to fetch data:', err);
  }
};

const fetchFilm = async () => {
  try {
    film.value = await Service.get<Film[]>('/film');

  } catch (err) {
    console.error('Failed to fetch films:', err);
  }
};

const fetchSale = async () => {
  try {
    sale.value = await Service.get<Sala[]>('/sale');

  } catch (err) {
    console.error('Failed to fetch salas:', err);
  }
};

const getFilmName = (filmId: number) => {
  const fil = film.value.find(f => f.id === filmId);
  if(fil)
    return fil.titolo;
  else
    return '';
};

const getSalaNumber = (salaId: number) => {
  const sala = sale.value.find(s => s.id === salaId);
  if(sala)
    return sala.numero;
  else
    return '';
};

const addNew = () => {
  const newRow: Proiezione = { id: Date.now(), dataOraInizio: new Date(), dataOraFine: new Date(), prezzo: 0, filmId: 0, salaId: 0, isNew: true };
  tableData.value.push(newRow);
};

const onRowEditSave = async (event: any) => {
  const { newData, index } = event;
  if (newData.isNew) {
    await Service.post('/insertProiezione', newData);
    delete newData.isNew;
  } else {
    await Service.put('/updateProiezione', newData);
  }
  tableData.value[index] = newData;
};

const deleteRow = async (rowData: Proiezione) => {
  try {
    await Service.delete('/deleteProiezione/' + rowData.id);
    tableData.value = tableData.value.filter(item => item.id !== rowData.id);
  } catch (err) {
    console.error('Failed to delete row:', err);
  }
};
</script>

<style scoped>
.pi {
  margin-right: 8px;
}
.CRUD-Table {
  width: 100%;
}
</style>
