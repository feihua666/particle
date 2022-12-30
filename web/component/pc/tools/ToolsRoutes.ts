
const UserRoutes = [
    {
        path: '/front/stringReplacePage',
        component: () => import('./views/front/StringReplacePage.vue'),
        meta: {
            root: true,
            code:'stringReplacePage',
            name: '字符串替换',
            keepAlive: true
        },
    },
]
export default UserRoutes