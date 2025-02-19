<template>
  <Dialog header="Prenotazione" :visible="visible" modal @hide="closeModal">
    <Card>
      <template #content>
        <p><strong>Inizio:</strong> {{ proiezione.dataOraInizio }}</p>
        <p><strong>Fine:</strong> {{ proiezione.dataOraFine }}</p>
        <p><strong>Prezzo:</strong> {{ proiezione.prezzo }}€</p>
      </template>
    </Card>
    <form @submit.prevent="submitBooking">
      <div class="p-field">
        <label for="numeroBiglietti">Numero di Biglietti</label>
        <InputNumber id="numeroBiglietti" v-model="numeroBiglietti" :max="maxTickets" required />
      </div>
      <Button type="submit" label="Prenota" />
    </form>
  </Dialog>
</template>

<script setup lang="ts">
import { ref, watch } from 'vue';
import Dialog from 'primevue/dialog';
import InputNumber from 'primevue/inputnumber';
import Button from 'primevue/button';
import Card from 'primevue/card';
import Service from '@/services/service';
import type { Proiezione } from '@/model/proiezione';
import type { Prenotazione } from '@/model/prenotazione';

const props = defineProps<{
  visible: boolean;
  proiezioneId: number | null;
}>();

const emit = defineEmits(['close']);

const numeroBiglietti = ref<number>(0);
const maxTickets = ref<number>(0);
const proiezione = ref<Proiezione>({ id: null, dataOraFine: null, dataOraInizio: null, filmId: -1, prezzo: 0, salaId: -1 });

watch(
  () => props.proiezioneId,
  (newId) => {
    if (newId !== null) {
      fetchProiezione(newId);
      checkAvailability(newId);
    }
  },
  { immediate: true }
);

const fetchProiezione = async (id: number) => {
  try {
    const response = await Service.get<Proiezione>(`/proiezione/${id}`);
    if (response) {
      proiezione.value = response;
    }
  } catch (error) {
    console.error('Error fetching proiezione:', error);
  }
};

const checkAvailability = async (id: number) => {
  try {
    const availableSeats = await Service.get<number>(`/getDisponibilita/${id}`);
    if (availableSeats) {
      maxTickets.value = availableSeats;
    }
  } catch (error) {
    console.error('Error fetching availability:', error);
  }
};

const closeModal = () => {
  emit('close');
};

const submitBooking = async () => {
  const dataPrenotazione = new Date().toISOString().split('T')[0];
  const booking: Prenotazione = {
    id: null,
    numeroBiglietti: numeroBiglietti.value,
    dataPrenotazione: dataPrenotazione,
    proiezioneId: props.proiezioneId!,
    utenteId: -1,
  };

  try {
    await Service.post('/insertPrenotazione', booking);
    closeModal();
  } catch (error) {
    console.error('Error submitting booking:', error);
  }
};
</script>

<style scoped>
.p-field {
  margin-bottom: 1em;
}
</style>
