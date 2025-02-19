<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { Card, Badge, Divider, Button } from 'primevue';
import type { Cinema } from "@/model/cinema.ts";
import type { Proiezione } from "@/model/proiezione.ts";
import type { ProgrammazioneFilm } from "@/model/programmazione-film.ts";
import Service from "@/services/service.ts";
import { useRoute } from 'vue-router';
import FilmCard from "@/components/FilmCard.vue";
import type { Film } from "@/model/film.ts";
import PrenotazioneComponent from "@/components/PrenotazioneComponent.vue";

const route = useRoute();

export interface ProiezioneDisp extends Proiezione {
  disponibilita: number;
}

interface ProgrammazioneFilmConDisp {
  cinema: Cinema;
  proiezioni: ProiezioneDisp[];
}

const programmazione = ref<ProgrammazioneFilm[]>([]);
const programmazioneConDisp = ref<ProgrammazioneFilmConDisp[]>([]);
const film = ref<Film>();
const isModalVisible = ref(false);
const selectedProiezioneId = ref<number | null>(null);

const openModal = (proiezioneId: number) => {
  selectedProiezioneId.value = proiezioneId;
  isModalVisible.value = true;
};

const fetchProgrammazione = async () => {
  try {
    programmazione.value = await Service.get<ProgrammazioneFilm[]>(`/ProgrammazioneFilm/${route.params.id}`);
    console.log('Fetched Programmazione:', programmazione.value); // Debugging log
  } catch (error) {
    console.log(error);
  }
}

const fetchDisponibilita = async (id: number | null): Promise<number> => {
  try {
    const disponibilita = await Service.get<number>(`/getDisponibilita/${id}`);
    console.log(`Fetched Disponibilita for ${id}:`, disponibilita); // Debugging log
    return disponibilita;
  } catch (error) {
    console.log(error);
    return 0;
  }
}

const fetchFilm = async () => {
  try {
    if (!Array.isArray(route.params.id)) {
      film.value = await Service.get<Film>('/film/' + route.params.id);
      console.log('Fetched Film:', film.value); // Debugging log
    }
  } catch (error) {
    console.log(error);
  }
}

const closeModal = () => {
  isModalVisible.value = false;
}

const enrichProgrammazioneWithDisponibilita = async () => {
  const enrichedProgrammazione = [];
  for (const prog of programmazione.value) {
    const enrichedProiezioni = [];
    for (const proiezione of prog.proiezioni) {
      const disponibilita = await fetchDisponibilita(proiezione.id);
      enrichedProiezioni.push({
        id: proiezione.id,
        dataOraInizio: proiezione.dataOraInizio,
        dataOraFine: proiezione.dataOraFine,
        prezzo: proiezione.prezzo,
        filmId: proiezione.filmId,
        salaId: proiezione.salaId,
        disponibilita: disponibilita
      });
    }
    enrichedProgrammazione.push({
      cinema: prog.cinema,
      proiezioni: enrichedProiezioni,
    });
  }
  programmazioneConDisp.value = enrichedProgrammazione;
}

onMounted(async () => {
  await fetchFilm();
  await fetchProgrammazione();
  await enrichProgrammazioneWithDisponibilita();
});
</script>

<template>
  <div class="programmazione-container">
    <div class="filmCard">
      <film-card v-if="film" :film="film" />
    </div>
    <div v-for="p in programmazioneConDisp" :key="p.cinema.id ?? 'unknown-cinema-id'" class="cinema">
      <Card class="cinema-card p-card">
        <template #title>
          <div class="cinema-header p-card-header">
            <router-link :to="{ name: 'ProgrammazioneCinema', params: { id: p.cinema.id } }">
              <h2>{{ p.cinema.nome }}</h2>
            </router-link>
            <Badge value="Cinema"></Badge>
          </div>
        </template>
        <template #content>
          <p>Indirizzo: {{ p.cinema.indirizzo }}</p>
          <p>Telefono: {{ p.cinema.telefono }}</p>
          <Divider></Divider>
          <h3>Proiezioni</h3>
          <div class="proiezioni">
            <Card v-for="proiezione in p.proiezioni" :key="proiezione.id ?? 'unknown-proiezione-id'" class="proiezione-card">
              <template #title>
                <Badge value="Proiezione" class="proiezione-badge"></Badge>
              </template>
              <template #content>
                <p><strong>Inizio:</strong> {{ proiezione.dataOraInizio }}</p>
                <p><strong>Fine:</strong> {{ proiezione.dataOraFine }}</p>
                <p><strong>Prezzo:</strong> {{ proiezione.prezzo }}€</p>
                <p><strong>Posti Disponibili:</strong> {{ proiezione.disponibilita }}</p>
              </template>
              <template #footer>
                <Button label="Prenota" @click="openModal(proiezione.id)" />
              </template>
            </Card>
          </div>
        </template>
      </Card>
    </div>
  </div>
  <PrenotazioneComponent
    :visible="isModalVisible"
    :proiezioneId="selectedProiezioneId"
    @update:visible="isModalVisible = $event"
  /></template>

<style scoped>
body {
  margin: 0;
  padding: 0;
  background-color: #f0f0f0;
  color: #333;
}
.programmazione-container {
  width: 90%;
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  box-sizing: border-box;
}
.cinema {
  width: 100%;
  margin-bottom: 20px;
}
.cinema-card {
  width: 100%;
  padding: 16px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  background-color: #7FFFD4;
}
.cinema-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}
.cinema-content {
  display: flex;
  flex-direction: column;
}
.proiezioni {
  display: flex;
  flex-direction: column;
  gap: 10px;
}
.proiezione-card {
  width: 100%;
  padding: 10px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  background-color: #a37a44;
}
.proiezione-header {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  margin-bottom: 5px;
}
.proiezione-content {
  display: flex;
  flex-direction: column;
}
.badge {
  padding: 5px 10px;
  background-color: #007bff;
  color: white;
  border-radius: 3px;
  font-size: 0.8em;
}
.filmCard {
  width: 20%;
  align-self: center;
}
</style>
