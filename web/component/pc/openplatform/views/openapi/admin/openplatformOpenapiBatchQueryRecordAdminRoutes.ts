const openplatformOpenapiBatchQueryRecordAdminRoutes = [
    {
        path: '/admin/openplatformOpenapiBatchQueryRecordManagePage',
        component: () => import('./OpenplatformOpenapiBatchQueryRecordManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformOpenapiBatchQueryRecordManagePage',
            name: '开放接口批量查询记录管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformOpenapiBatchQueryRecordDetail',
                component: () => import('./OpenplatformOpenapiBatchQueryRecordDetailManagePage.vue'),
                props: route => ({ openplatformOpenapiBatchQueryRecordId: route.query.openplatformOpenapiBatchQueryRecordId }),
                meta: {
                    showInDrawer: true,
                    code: 'adminOpenplatformOpenapiBatchQueryRecordDetail',
                    name: '开放接口批量查询记录明细管理',
                },
            }
        ]
    },
]
export default openplatformOpenapiBatchQueryRecordAdminRoutes