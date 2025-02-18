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
      <Column field="proiezioneId" header="Proiezione">
        <template #editor="slotProps">
          <InputNumber v-model="slotProps.data.proiezioneId" />
        </template>
        <template #body="slotProps">
          <span>{{ slotProps.data.proiezioneId }}</span>
        </template>
      </Column>
      <Column field="utenteId" header="Utente">
        <template #editor="slotProps">
          <InputNumber v-model="slotProps.data.utenteId" />
        </template>
        <template #body="slotProps">
          <span>{{ slotProps.data.utenteId }}</span>
        </template>
      </Column>
      <Column field="numeroBiglietti" header="Numero Biglietti">
        <template #editor="slotProps">
          <InputNumber v-model="slotProps.data.numeroBiglietti" />
        </template>
      </Column>
      <Column field="dataPrenotazione" header="Data Prenotazione">
        <template #editor="slotProps">
          <Calendar v-model="slotProps.data.dataPrenotazione" showTime />
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
import type { Prenotazione } from '@/model/prenotazione.ts';
import Service from "@/services/service.ts";
import { Button, DataTable, Column, InputNumber, Calendar } from "primevue";

const tableData = ref<Prenotazione[]>([]);
const editingRows = ref([]);

// Fetch initial data
onMounted(() => {
  fetchPrenotazioni();
});

const fetchPrenotazioni = async () => {
  try {
    tableData.value = await Service.get<Prenotazione[]>('/prenotazioni');
  } catch (err) {
    console.error('Failed to fetch data:', err);
  }
};

const addNew = () => {
  const newRow: Prenotazione = { id: Date.now(), numeroBiglietti: 0, dataPrenotazione: new Date(), proiezioneId: 0, utenteId: 0, isNew: true };
  tableData.value.push(newRow);
};

const onRowEditSave = async (event: any) => {
  const { newData, index } = event;
  if (newData.isNew) {
    await Service.post('/insertPrenotazione', newData);
    delete newData.isNew;
  } else {
    await Service.put('/updatePrenotazione', newData);
  }
  tableData.value[index] = newData;
};

const deleteRow = async (rowData: Prenotazione) => {
  try {
    await Service.delete('/deletePrenotazione/' + rowData.id);
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
