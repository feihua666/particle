import { createRouter, createWebHistory } from 'vue-router'
import Entry from '../views/admin/Entry.vue'
import Index from '../views/admin/index/Index.vue'
import Login from '../views/admin/login/Login.vue'
const options = {
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'entry',
      component: Entry
    },
    {
      path: '/index',
      name: 'index',
      component: Index
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
  ]
}
const router = createRouter(options)

export default router
