import { createRouter, createWebHistory } from 'vue-router'
import Entry from '../views/admin/Entry.vue'
import Index from '../views/admin/index/Index.vue'
import Login from '../views/admin/login/Login.vue'

import TestIndex from '../views/component/Index.vue'
import TestMap from '../views/test/TestMap.vue'
import TestDraggable from '../views/test/TestDraggable.vue'
import TestTimePicker from '../views/test/TestTimePicker.vue'
import TestAceEditor from '../views/test/TestAceEditor.vue'
import TestCompAdapter from '../views/test/TestCompAdapter.vue'
import TestTinymceEditor from '../views/test/TestTinymceEditor.vue'

import routes from "./routes";
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
      component: Index,
      children: routes
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/testIndex',
      name: 'testIndex',
      component: TestIndex
    },
    {
      path: '/testMap',
      name: 'testMap',
      component: TestMap
    },
    {
      path: '/testDraggable',
      name: 'testDraggable',
      component: TestDraggable
    },
    {
      path: '/testTimePicker',
      name: 'testTimePicker',
      component: TestTimePicker
    },
    {
      path: '/testAceEditor',
      name: 'testAceEditor',
      component: TestAceEditor
    },
    {
      path: '/testCompAdapter',
      name: 'testCompAdapter',
      component: TestCompAdapter
    },
    {
      path: '/testTinymceEditor',
      name: 'testTinymceEditor',
      component: TestTinymceEditor
    },
  ]
}
const router = createRouter(options)

export default router
