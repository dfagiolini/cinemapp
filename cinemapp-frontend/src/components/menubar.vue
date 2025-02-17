<script setup lang="ts">
import Menubar from 'primevue/menubar';
import {ref} from "vue";

import { useRouter } from 'vue-router';

const router = useRouter();

const items = ref([
  {
    label: 'Home',
    icon: 'pi pi-home',
    command: () => { router.push('/') }
  }


]);
const adminItems = ref([
  {
    label: 'Admin Panel',
    items: [
      {
        label: 'Film',
        command: () => { router.push('/filmCRUD') }
      },
      {
        label: 'Cinema',
        command: () => { router.push('/cinema') }
      },
      {
        label: 'Proiezioni',
        command: () => { router.push('/proiezioni') }
      },
      {
        label: 'Prenotazioni',
        command: () => { router.push('/prenotazioni') }
      },
      {
        label: 'Sale',
        command: () => { router.push('/sale') }
      }
    ]
  }

]);





</script>

<template>

    <Menubar :model="items">

      <template #end>
        <Menubar :model="adminItems" />
      </template>
    </Menubar>




</template>

<style scoped>

</style>
