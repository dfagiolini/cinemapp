import { createRouter, createWebHistory } from 'vue-router'

import FilmCarousel from "@/components/FilmCarousel.vue";
import Home from "@/components/Home.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
    },
    {
      path: '/ProgrammazioneFilm/:id',
      name: 'ProgrammazioneFilm',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../components/ProgrammazioneFilm.vue'),
    },
  ],
})

export default router
