<template>
  <Dialog header="Prenotazione" :visible.sync="visible" modal>
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
        <InputNumber
          id="numeroBiglietti"
          v-model="numeroBiglietti"
          :max="maxTickets"
          required
          @input="checkAvailability"
        />
      </div>
      <Button type="submit" label="Prenota" />
    </form>
  </Dialog>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import Dialog from 'primevue/dialog';
import InputNumber from 'primevue/inputnumber';
import Button from 'primevue/button';
import Card from "primevue/card";
import { useRoute } from 'vue-router';
import Service from "@/services/service";
import type { Prenotazione } from "@/model/prenotazione";
import type {Proiezione} from "@/model/proiezione.ts";

// Declare refs with their types
const visible = ref<boolean>(true);
const numeroBiglietti = ref<number>(0);
const maxTickets = ref<number>(0);
const proiezioneId = ref<number | null>(null);
const proiezione = ref<Proiezione>({id: null, dataOraFine: null, dataOraInizio: null, filmId: -1, prezzo: 0, salaId: -1});

const route = useRoute();

onMounted(() => {
  if (route.params.proiezioneId && !Array.isArray(route.params.proiezioneId)) {
    proiezioneId.value = parseInt(route.params.proiezioneId, 10);
    fetchProiezione(parseInt(route.params.proiezioneId, 10));
  }

  checkAvailability();
});
const fetchProiezione = async (id: number) => {
  try {
   const response = await Service.get<Proiezione>(`/proiezione/${id}`);
   if(response){
     proiezione.value = response;
   }
  }
  catch (error) {
    console.log(error);
  }
}
const checkAvailability = async () => {
  try {
    const availableSeats: number = await Service.get<number>(`/getDisponibilita/${proiezioneId.value}`);
    if (availableSeats) {
      maxTickets.value = availableSeats;
    }
  } catch (error) {
    console.error('Error fetching availability:', error);
  }
};

const submitBooking = async () => {
  const dataPrenotazione = new Date().toISOString().split('T')[0];
  const booking: Prenotazione = {
    id: null,
    numeroBiglietti: numeroBiglietti.value,
    dataPrenotazione: dataPrenotazione,
    proiezioneId: proiezioneId.value!,
    utenteId: -1,
  };

  try {
    await Service.post('/insertPrenotazione', booking);

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
