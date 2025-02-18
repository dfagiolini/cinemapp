import { defineStore } from 'pinia';
import axios from 'axios';
import { jwtDecode } from 'jwt-decode';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const API_URL = 'http://localhost:8080';

interface JwtPayload {
  sub: string;
  role: number;
}

export const useAuthStore = defineStore('auth', () => {
  const token = ref<string | null>(localStorage.getItem('token'));
  const role = ref<number | null>(null);
  const router = useRouter();

  function decodeToken(token: string): void {
    const decoded = jwtDecode<JwtPayload>(token);
    role.value = decoded.role;
  }

  async function login(username: string, password: string) {
    try {
      const response = await axios.post(`${API_URL}/cinemapp/login`, { username, password });
      token.value = response.data;
      if(token.value) {
        localStorage.setItem('token', token.value);

        decodeToken(token.value);
      }

      router.push('/');
    } catch (error) {
      console.error('Login failed:', error);
      alert('Login failed. Please check your credentials.');
    }
  }

  function logout() {
    token.value = null;
    role.value = null;
    localStorage.removeItem('token');
    router.push('/login');
  }

  if (token.value) {
    decodeToken(token.value);
  }

  return {
    token,
    role,
    login,
    logout,
  };
});
