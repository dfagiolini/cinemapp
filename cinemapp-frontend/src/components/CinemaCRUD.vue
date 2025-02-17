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
      <Column field="nome" header="Nome">
        <template #editor="slotProps">
          <InputText v-model="slotProps.data.nome" />
        </template>
      </Column>
      <Column field="indirizzo" header="Indirizzo">
        <template #editor="slotProps">
          <InputText v-model="slotProps.data.indirizzo" />
        </template>
      </Column>
      <Column field="telefono" header="Telefono">
        <template #editor="slotProps">
          <InputText v-model="slotProps.data.telefono" />
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
import type { Film } from '@/model/film';
import Service from "@/services/service.ts";
import { Button, DataTable, Column, InputText } from "primevue";
import type {Cinema} from "@/model/cinema.ts";

const tableData = ref<Cinema[]>([]);
const editingRows = ref([]);

// Fetch initial data
onMounted(() => {
  fetchCinema();
});

const fetchCinema = async () => {
  try {
    tableData.value = await Service.get<Cinema[]>('/cinema');
  } catch (err) {
    console.error('Failed to fetch data:', err);
  }
};

const addNew = () => {
  const newRow: Cinema = {nome: '', id:null, telefono:'', indirizzo:'', isNew: true};
  tableData.value.push(newRow);
};

const onRowEditSave = async (event: any) => {
  const { newData, index } = event;
  if (newData.isNew) {
    await Service.post('/insertCinema', newData);
    delete newData.isNew;
  } else {
    await Service.put('/updateCinema', newData);
  }
  tableData.value[index] = newData;
};

const deleteRow = async (rowData: Film) => {
  try {
    await Service.delete('/deleteCinema/' + rowData.id);
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
