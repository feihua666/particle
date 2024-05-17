const ssqCodeAdminRoutes = [
    {
        path: '/admin/ssqCodeManagePage',
        component: () => import('./SsqCodeManagePage.vue'),
        meta: {
            root: true,
            code:'adminSsqCodeManagePage',
            name: '双色球号码管理',
            keepAlive: true
        },
        children: [
        ]
    },
]
export default ssqCodeAdminRoutes