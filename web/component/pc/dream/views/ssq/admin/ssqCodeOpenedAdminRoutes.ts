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
        path: '/admin/SsqCodeOpenedSeqNoTrendPage',
        component: () => import('./SsqCodeOpenedSeqNoTrendPage.vue'),
        meta: {
            root: true,
            code:'SsqCodeOpenedSeqNoTrendPage',
            name: '双色球开奖序号趋势',
            keepAlive: true
        },
    },
    {
        path: '/admin/SsqCodeOpenedRegionTrendPage',
        component: () => import('./SsqCodeOpenedRegionTrendPage.vue'),
        meta: {
            root: true,
            code:'SsqCodeOpenedRegionTrendPage',
            name: '双色球开奖区域分布趋势',
            keepAlive: true
        },
    },
    {
        path: '/admin/SsqCodeOpenedParameterTuningPredictionManagePage',
        component: () => import('./SsqCodeOpenedParameterTuningPredictionManagePage.vue'),
        meta: {
            root: true,
            code:'SsqCodeOpenedParameterTuningPredictionManagePage',
            name: '双色球开奖预测调参',
            keepAlive: true
        },
    },
    {
        path: '/admin/SsqCodeOpenedSlidingWindowTrendPage',
        component: () => import('./SsqCodeOpenedSlidingWindowTrendPage.vue'),
        meta: {
            root: true,
            code:'SsqCodeOpenedSlidingWindowTrendPage',
            name: '双色球开奖滑动窗口趋势',
            keepAlive: true
        },
    }
]
export default ssqCodeOpenedAdminRoutes