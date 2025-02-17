<script setup lang="ts">
import Menubar from 'primevue/menubar';
import {ref} from "vue";

const items = ref([
  {
    label: 'Home',
    icon: 'pi pi-home',
    route: '/'
  } ]);







</script>

<template>
  <Menubar :model="items">
    <template #item="{item}">
      <router-link v-if="item.route" :to="item.route">
        <span>{{ item.label }}</span>
      </router-link>

    </template>
  </Menubar>
</template>

<style scoped>

</style>
