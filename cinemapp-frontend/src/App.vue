<script setup lang="ts">
import {RouterLink, RouterView} from 'vue-router'

import menubar from "@/components/menubar.vue";
</script>

<template>
  <div id=app>
    <header class="menubar">
    <menubar />
    </header>
    <RouterView />
  </div>



</template>

<style scoped>
body, html, #app {
  margin: 0;
  padding: 0;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  flex-flow: column;
}



.menubar {
  margin-top: 1%;
  padding-top: 1%;
  margin-bottom: 0;
  position: relative;
  top: 0;
  width: 100%;
}


</style>
