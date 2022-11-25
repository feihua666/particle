import { createRouter, createWebHistory } from 'vue-router'
import Index from '../views/backend/index/Index.vue'
const options = {
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index
    },
  ]
}
const router = createRouter(options)

export default router
