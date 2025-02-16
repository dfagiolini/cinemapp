<script setup lang="ts">
import Menubar from 'primevue/menubar';
import {ref} from "vue";

const items = ref([
  {
    label: 'Home',
    icon: 'pi pi-home'
  } ]);







</script>

<template>
  <Menubar :model="items">
  </Menubar>
</template>

<style scoped>

</style>
