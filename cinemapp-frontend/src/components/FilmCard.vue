
<template>
  <div class="film-card">
    <card>
      <template #header>
        <img :src="film.copertina" alt="Film Poster" class="film-poster" />
      </template>
      <template #title>{{ film.titolo }}</template>
      <template #subtitle>{{ film.regia }}</template>

      <template #content>
        <p>{{ film.descrizione }}</p>
      </template>
    </card>
  </div>
</template>

<script lang="ts">
import { defineComponent, type PropType } from 'vue';

import Card  from 'primevue/card';
import type {Film} from "@/model/film.ts";


export default defineComponent({
  name: 'FilmCard',
  components: {
    Card,
  },
  props: {
    film: {
      type: Object as PropType<Film>,
      required: true,
    },
  },
});
</script>

<style scoped>
.film-card {
  margin: 1em;
}
.film-poster {
  width: 100%;
  height: auto;
}
</style>
