import { defineStore } from 'pinia';
import axios from 'axios';
import { jwtDecode } from 'jwt-decode';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

import Service from "@/services/service.ts";


const API_URL = 'http://localhost:8080';

interface JwtPayload {
  sub: string;
  role: number;
}
export interface UserDetails {
  nome: string | null,
  email: string | null;
}

export const useAuthStore = defineStore('auth', () => {
  const token = ref<string | null>(localStorage.getItem('token'));
  const role = ref<number | null>(null);
  const router = useRouter();
  const userdetails = ref<UserDetails>({nome: null, email: null});
  const isAdmin = ref<boolean>(false);
  const isLoggedIn = ref<boolean>(false);

  function decodeToken(token: string): void {
    const decoded = jwtDecode<JwtPayload>(token);
    role.value = decoded.role;
    isAdmin.value = role.value == 0;
  }

  async function login(username: string, password: string) {
    try {
      const response = await axios.post(`${API_URL}/cinemapp/login`, { username, password });
      token.value = response.data;
      if(token.value) {
        localStorage.setItem('token', token.value);
        decodeToken(token.value);

        try {
          userdetails.value = await Service.get<UserDetails>('/getMyDetails');
          localStorage.setItem('userDetails', JSON.stringify(userdetails.value));
        } catch (error) {
          console.log(error);
        }

      }
      isLoggedIn.value = true;
      router.push('/');
    } catch (error) {
      console.error('Login failed:', error);
      alert('Login failed. Please check your credentials.');
    }

  }
  function getUserDetailsFromLocalStorage() {
    const userDetailsString = localStorage.getItem('userDetails');
    if (userDetailsString) {
      try {
        return JSON.parse(userDetailsString);
      } catch (error) {
        console.error('Failed to parse user details:', error);
        return null;
      }
    }
    return null;
  }
  function logout() {
    token.value = null;
    role.value = null;
    localStorage.removeItem('token');
    userdetails.value = {nome: null, email: null};
    localStorage.removeItem('userDetails');
    isLoggedIn.value = false;
    isAdmin.value = false;

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
    getUserDetailsFromLocalStorage,
    isAdmin,
    isLoggedIn
  };
});
