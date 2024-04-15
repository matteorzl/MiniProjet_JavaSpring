import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '@/components/HomePage.vue';
import creerClient from "@/components/creerClient.vue";
import listeComptes from "@/components/listeComptes.vue";
import detailCompte from "@/components/detailCompte.vue";
import detailClient from "@/components/detailClient.vue";
import creerCompte from "@/components/creerCompte.vue";
import listeClients from "@/components/listeClients.vue";
import virementCompte from "@/components/virementCompte.vue";

const routes = [
  {
    path: '/',
    component: HomePage
  },
  {
    path: '/compte/lister',
    name: '',
    component: listeComptes
  },
  {
    path: '/compte/:id',
    name: '',
    component: detailCompte
  },
  {
    path: '/compte/creer',
    name: '',
    component: creerCompte
  },
  {
    path: '/compte/virement',
    name: '',
    component: virementCompte
  },
  {
    path: '/compte/editer',
    name: '',
    component: creerCompte
  },
  {
    path: '/client/lister',
    name: '',
    component: listeClients
  },
  {
    path: '/client/:id',
    name: '',
    component: detailClient
  },
  {
    path: '/client/creer',
    name: '',
    component: creerClient
  },
  {
    path: '/client/editer',
    name: '',
    component: creerClient
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
