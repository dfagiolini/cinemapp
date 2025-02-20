<script setup lang="ts">
import { RouterLink, RouterView, useRoute, useRouter } from 'vue-router';

import menubar from "@/components/menubar.vue";
import {ref, watchEffect} from "vue";

const route = useRoute();
const menubarKey = ref(route.fullPath);

watchEffect(() => {
  menubarKey.value = route.fullPath;
});
</script>

<template>
  <div id=app>
    <header>
    <menubar :key="menubarKey" />
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
