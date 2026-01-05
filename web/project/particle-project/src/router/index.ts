import {createRouter, createWebHashHistory} from 'vue-router'
import Entry from '../views/admin/Entry.vue'
import Index from '../views/admin/index/Index.vue'
import PageLogin from '../views/admin/login/PageLogin.vue'

import Test from '../views/test/Test.vue'

import routes from "./routes";
import TestRoutes from "@/views/test/TestRoutes.ts";

const options = {
    // history: createWebHistory(import.meta.env.BASE_URL),
    // 默认使用 hash 模式，如果是前端部署和后台分开问题不大，主要是解决前端部署在了后端项目中如使用springboot静态资源方式，刷新问题和地址和接口很难分清楚
    history: createWebHashHistory(import.meta.env.BASE_URL),
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
            component: PageLogin
        },
        {
            path: '/test',
            name: 'test',
            component: Test,
            children: TestRoutes
        },
    ]
}
const router = createRouter(options)

export default router
