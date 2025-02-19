<script setup lang="ts">
import {RouterLink, RouterView} from 'vue-router'

import menubar from "@/components/menubar.vue";
import {verifyAdmin} from "@/utils/VerifyAdmin.ts";


</script>

<template>
  <div id=app>
    <header class="menubar">
    <menubar />
    </header>
    <div class ="content">
    <RouterView />
    </div>
  </div>



</template>

<style scoped>
body, html, #app {
  margin: 0;
  padding: 0;
  height: 100%;
  display: flex;
  flex-direction: column;
}

.menubar {
  width: 100%;
}

.content {
  flex: 1;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: flex-start;
  overflow-y: auto;
}
</style>
