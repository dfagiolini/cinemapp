<template>
  <div class="p-d-flex p-jc-center p-ai-center" style="height: 100vh;">
    <div class="p-card p-p-4 p-shadow-6" style="width: 300px;">
      <h2 class="p-text-center">Login</h2>
      <form @submit.prevent="handleLogin">
        <div class="p-field">
          <label for="username">Username</label>
          <InputText id="username" v-model="username" type="text" required />
        </div>
        <div class="p-field">
          <label for="password">Password</label>
          <Password id="password" v-model="password" toggleMask required feedback={false} />
        </div>
        <Button label="Login" icon="pi pi-sign-in" type="submit" class="p-mt-3" />
      </form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useAuthStore } from '@/stores/auth';
import InputText from 'primevue/inputtext';
import Password from 'primevue/password';
import Button from 'primevue/button';

const username = ref('');
const password = ref('');
const authStore = useAuthStore();
const router = useRouter();

const handleLogin = async () => {
  await authStore.login(username.value, password.value);
};
</script>
