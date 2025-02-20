// src/components/Profilo.vue
<script setup lang="ts">
import { ref, onMounted } from 'vue';
import Service from '@/services/service';
import type { UserDetails, Prenotazione, Film, Proiezione } from '@/types';
import DataTable  from 'primevue/datatable';
import  Column  from 'primevue/column';
import  Card  from 'primevue/card';
import {useAuthStore} from "@/stores/auth.ts";

const userDetails = ref<UserDetails | null>(null);
const prenotazioni = ref<Prenotazione[]>([]);
const films = ref<{ [key: number]: Film }>({});
const authStore = useAuthStore();

const fetchUserDetails =  () => {
  userDetails.value = authStore.getUserDetailsFromLocalStorage();
};

const fetchUserPrenotazioni = async () => {
  prenotazioni.value = await Service.get("/prenotazioni");

  // Fetch related film details for each prenotazione
  for (const prenotazione of prenotazioni.value) {
    const proiezione = await Service.get(`proiezione/${prenotazione.proiezioneId}`);
    const film = await Service.get(`/film/${proiezione.filmId}`);
    films.value[film.id as number] = film;
  }
};

onMounted(() => {
  fetchUserDetails();
  fetchUserPrenotazioni();
});
</script>

<template>
  <div>
    <h2>Profilo</h2>
    <Card v-if="userDetails" class="user-details">
      <template #title>
        <h3>{{ userDetails.nome }}</h3>
      </template>
      <template #content>
        <p>Email: {{ userDetails.email }}</p>
      </template>
    </Card>

    <h2>Le tue prenotazioni</h2>
    <DataTable :value="prenotazioni">
      <Column field="dataPrenotazione" header="Booking Date" />
      <Column field="numeroBiglietti" header="Number of Tickets" />
      <Column header="Film">
        <template #body="slotProps">
          <img :src="films[slotProps.data.proiezioneId]?.copertina" alt="Film cover" width="50" />
          <span>{{ films[slotProps.data.proiezioneId]?.titolo }}</span>
        </template>
      </Column>
    </DataTable>
  </div>
</template>

<style scoped>
.user-details {
  margin-bottom: 1em;
}
</style>
