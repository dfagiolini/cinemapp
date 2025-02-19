import { createRouter, createWebHistory } from 'vue-router'

import FilmCarousel from "@/components/FilmCarousel.vue";
import Home from "@/components/Home.vue";
import ProgrammazioneCinema from "@/components/ProgrammazioneCinema.vue";
import FilmCRUD from "@/components/FilmCRUD.vue";
import CinemaCRUD from "@/components/CinemaCRUD.vue";
import SalaCRUD from "@/components/SalaCRUD.vue";
import ProiezioneCRUD from "@/components/ProiezioneCRUD.vue";
import PrenotazioneCRUD from "@/components/PrenotazioneCRUD.vue";
import Login from "@/components/Login.vue";
import PrenotazioneComponent from "@/components/PrenotazioneComponent.vue";

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
    {
      path: '/ProgrammazioneCinema/:id',
      name: 'ProgrammazioneCinema',
      component: ProgrammazioneCinema,
    },
    {
      path: '/filmCRUD',
      name: 'FilmCRUD',
      component: FilmCRUD,
    },
    {
      path: '/cinemaCRUD',
      name: 'CinemaCRUD',
      component: CinemaCRUD,
    },
    {
      path: '/saleCRUD',
      name: 'SaleCRUD',
      component: SalaCRUD,
    },{
      path: '/proiezioniCRUD',
      name: 'ProiezioniCRUD',
      component: ProiezioneCRUD,
    },
    {
      path: '/prenotazioniCRUD',
      name: 'PrenotazioniCRUD',
      component: PrenotazioneCRUD,
    }, {
      path: '/login',
      name: 'login',
      component: Login,
    },{
      path: '/Prenota/:proiezioneId',
      name: 'prenota',
      component: PrenotazioneComponent,
    }

  ],
})

export default router
