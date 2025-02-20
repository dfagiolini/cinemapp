<script setup lang="ts">
import Menubar from 'primevue/menubar';
import {ref, watch} from "vue";
import {onMounted} from "vue";
import { useRouter } from 'vue-router';
import {useAuthStore} from "@/stores/auth.ts";
import {verifyLoggedIn} from "@/utils/VerifyLoggedIn.ts";
import {verifyAdmin} from "@/utils/VerifyAdmin.ts";

const router = useRouter();
const authStore = useAuthStore();
const items = ref([
  {
    label: 'Home',
    icon: 'pi pi-home',
    command: () => { router.push('/') }
  }


]);
const userDetails = authStore.getUserDetailsFromLocalStorage();
const username = ref(userDetails ? userDetails.nome : '');
const isLoggedIn = ref(false);
const isAdmin = ref(false);
const checkLoginStatus = () => {
  isLoggedIn.value = verifyLoggedIn();

};
const checkAdminStatus = () => {
  isAdmin.value = verifyAdmin();
}

onMounted(() => {
  checkLoginStatus();
  checkAdminStatus();
});


watch(
  () => router.currentRoute.value,
  () => {
    checkLoginStatus();
  }
);
console.log(isLoggedIn.value);
console.log(verifyLoggedIn());
const endItems = ref([
  {
    label: 'Admin Panel',
    items: [
      {
        label: 'Film',
        command: () => { router.push('/filmCRUD') }
      },
      {
        label: 'Cinema',
        command: () => { router.push('/cinemaCRUD') }
      },
      {
        label: 'Proiezioni',
        command: () => { router.push('/proiezioniCRUD') }
      },
      {
        label: 'Prenotazioni',
        command: () => { router.push('/prenotazioniCRUD') }
      },
      {
        label: 'Sale',
        command: () => { router.push('/saleCRUD') }
      }
    ],
    visible: verifyAdmin()
  }, {
    label: username.value,
    visible : verifyLoggedIn(),
    items: [
      {
        label: 'Profilo',
        command: () => { router.push('/profilo') }
      }, {
      label: 'Esci',
        command: () => { authStore.logout() }
      }
    ],

  }, {
  label: 'Login',
    command: () => { router.push('/login') },
    visible : !verifyLoggedIn(),
  }

]);



</script>

<template>

    <Menubar :model="items">

      <template #end>

        <Menubar :model="endItems" />
      </template>

    </Menubar>




</template>

<style scoped>

</style>
