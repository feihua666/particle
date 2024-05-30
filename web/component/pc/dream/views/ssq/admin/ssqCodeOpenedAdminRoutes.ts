const ssqCodeOpenedAdminRoutes = [
    {
        path: '/admin/ssqCodeOpenedManagePage',
        component: () => import('./SsqCodeOpenedManagePage.vue'),
        meta: {
            root: true,
            code:'adminSsqCodeOpenedManagePage',
            name: '双色球开奖管理',
            keepAlive: true
        },
        children: [
        ]
    },
    {
        path: '/admin/SsqCodeOpenedStatisticsPage',
        component: () => import('./SsqCodeOpenedStatisticsPage.vue'),
        meta: {
            root: true,
            code:'adminSsqCodeOpenedStatisticsPage',
            name: '双色球开奖统计',
            keepAlive: true
        },
    }
]
export default ssqCodeOpenedAdminRoutes