<script setup lang="ts">

import FilmCarousel from "@/components/FilmCarousel.vue";
import SearchTable from "@/components/SearchTable.vue";
</script>

<template>
  <div class="home">
  <film-carousel class="film-carousel"/>
  <SearchTable/>
  </div>
</template>

<style scoped>
.film-carousel {
  margin-top: 0;
  padding-top: 0;
  position: relative;
  top: 0;
  box-sizing: border-box;
  width: 100%;
}
.home {
  display: flex;
  flex-flow: column;
}

</style>
