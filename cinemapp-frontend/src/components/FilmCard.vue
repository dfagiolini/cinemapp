
<template>
  <router-link :to="{ name: 'ProgrammazioneFilm', params: { id: film.id } }" class = "no-decoration">
  <div class="film-card">
    <card >
      <template #header>
        <img :src="`/${film.copertina}`" alt="Film Poster" class="film-poster" />
      </template>
      <template #title>{{ film.titolo }}</template>
      <template #subtitle>{{ film.regia }}</template>

      <template #content>
        <p>{{ film.descrizione }}</p>
      </template>
    </card>
  </div>
  </router-link>
</template>

<<script setup lang="ts">
import { defineProps } from 'vue';
import { RouterLink } from 'vue-router';
import Card from 'primevue/card';
import type { Film } from '@/model/film.ts';

// Define props
const props = defineProps<{
  film: Film;
}>();
</script>



<style scoped>
.film-card {
  margin: 1em;
}
.film-poster {
  width: 100%;
  height: auto;
}
.no-decoration {
  text-decoration: none;
  color: inherit;
}
</style>
