

import axios, { type AxiosInstance, type AxiosResponse } from 'axios';
import {useAuthStore} from "@/stores/auth.ts";

class Service {
  private apiClient: AxiosInstance;

  constructor() {
    this.apiClient = axios.create({
      baseURL: 'http://localhost:8080',
      headers: {
        'Content-Type': 'application/json',
      },
    });

    this.apiClient.interceptors.request.use(
      (config) => {
        const authStore = useAuthStore();
        const token = authStore.token;
        if (token) {
          config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
      },
      (error) => {
        return Promise.reject(error);
      }
    );
  }


public async get<T>(endpoint: string): Promise<T> {
    try {
      const response: AxiosResponse<T> = await this.apiClient.get(endpoint);
      return response.data;
    } catch (error) {
      console.error('API GET request error:', error);
      throw error;
    }
  }

  public async post<T>(endpoint: string, data: any): Promise<AxiosResponse<T>> {
    try {
      const response: AxiosResponse<T> = await this.apiClient.post(endpoint, data);
      return response;
    } catch (error) {
      console.error('API POST request error:', error);
      throw error;
    }
  }

  public async put<T>(endpoint: string, data: any): Promise<AxiosResponse<T>> {
    try {
      const response: AxiosResponse<T> = await this.apiClient.put(endpoint, data);
      return response;
    } catch (error) {
      console.error('API POST request error:', error);
      throw error;
    }
  }

  public async delete<T>(endpoint: string): Promise<AxiosResponse<T>> {
    try {
      const response: AxiosResponse<T> = await this.apiClient.delete(endpoint);
      return response;
    } catch (error) {
      console.error('API GET request error:', error);
      throw error;
    }
  }

}

export default new Service();
