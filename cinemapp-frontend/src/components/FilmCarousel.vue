
<template>
  <div class="film-carousel">
    <Carousel :value="films" :numVisible="3" :numScroll="1">
      <template #item="slotProps">
        <FilmCard :film="slotProps.data" />
      </template>
    </Carousel>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import Carousel from 'primevue/carousel';
import FilmCard from '@/components/FilmCard.vue';
import type { Film } from '@/model/film';
import Service from '@/services/service';

const films = ref<Film[]>([]);
const error = ref<Error | null>(null);

const fetchFilms = async () => {
  try {
    films.value = await Service.get<Film[]>('/film');
  } catch (err) {
    error.value = err as Error;
    console.error('Failed to fetch data:', err);
  }
};

onMounted(() => {
  fetchFilms();
});
</script>

<style scoped>
.film-carousel {
  margin: 2em;
position: relative;
}
</style>
