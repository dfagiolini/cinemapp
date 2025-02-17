<template>
  <div class="CRUD-Table">
    <Button label="Aggiungi" icon="pi pi-plus" @click="addNew"/>
    <DataTable
      v-model:editingRows="editingRows"
      :value="tableData"
      editMode="row"
      dataKey="id"
      @row-edit-save="onRowEditSave"
    >
      <Column field="cinemaId" header="Cinema">
        <template #editor="slotProps">
          <Dropdown v-model="slotProps.data.cinemaId" :options="cinema" optionLabel="nome"
                    optionValue="id"/>
        </template>
        <template #body="slotProps">
          <span>{{ getCinemaName(slotProps.data.cinemaId) }}</span>
        </template>
      </Column>
      <Column field="capacita" header="Capacità">
        <template #editor="slotProps">
          <InputText v-model="slotProps.data.capacita"/>
        </template>
      </Column>
      <Column field="numero" header="Numero">
        <template #editor="slotProps">
          <InputText v-model="slotProps.data.numero"/>
        </template>
      </Column>
      <Column :rowEditor="true" style="width: 10%; min-width: 8rem"
              bodyStyle="text-align:center"></Column>
      <Column bodyStyle="text-align:center">
        <template #body="slotProps">
          <Button label="Elimina" icon="pi pi-trash" @click="() => deleteRow(slotProps.data)"/>
        </template>
      </Column>
    </DataTable>
  </div>
</template>

<script setup lang="ts">
import {ref, onMounted} from 'vue';
import type {Sala} from '@/model/sala';
import type {Cinema} from '@/model/cinema';
import Service from "@/services/service.ts";
import {Button, DataTable, Column, Dropdown, InputText} from "primevue";

const tableData = ref<Sala[]>([]);
const editingRows = ref([]);
const cinema = ref<Cinema[]>([]);
const cinemaOptions = ref([]);
const getCinemaName = (cinemaId: number) => {
  const cin = cinema.value.find(c => c.id === cinemaId);
  if(cin)
    return cin.nome;
  else return '';
};
// Fetch initial data
onMounted(() => {
  fetchSale();
  fetchCinema();
});

const fetchSale = async () => {
  try {
    tableData.value = await Service.get<Sala[]>('/sale');
  } catch (err) {
    console.error('Failed to fetch data:', err);
  }
};

const fetchCinema = async () => {
  try {
    cinema.value = await Service.get<Cinema[]>('/cinema');

  } catch (err) {
    console.error('Failed to fetch cinemas:', err);
  }
};


const addNew = () => {
  const newRow: Sala = {id: Date.now(), cinemaId: 0, capacita: 0, numero: '', isNew: true};
  tableData.value.push(newRow);
};

const onRowEditSave = async (event: any) => {
  const {newData, index} = event;
  if (newData.isNew) {
    await Service.post('/insertSala', newData);
    delete newData.isNew;
  } else {
    await Service.put('/updateSala', newData);
  }
  tableData.value[index] = newData;
};

const deleteRow = async (rowData: Sala) => {
  try {
    await Service.delete('/deleteSala/' + rowData.id);
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
