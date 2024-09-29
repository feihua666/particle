const openplatformOpenapiAdminRoutes = [
    {
        path: '/admin/openplatformOpenapiManagePage',
        component: () => import('./OpenplatformOpenapiManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformOpenapiManagePage',
            name: '开放平台开放接口管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformOpenapiManageAdd',
                component: () => import('./OpenplatformOpenapiManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformOpenapiManageAdd',
                    name: '开放平台开放接口添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformOpenapiManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformOpenapiManageAdd'
                    }
                }
            },

            {
                path: '/admin/openplatformOpenapiManageUpdate',
                component: () => import('./OpenplatformOpenapiManageUpdatePage.vue'),
                props: route => ({ openplatformOpenapiId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformOpenapiManageUpdate',
                    name: '开放平台开放接口修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformOpenapiManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformOpenapiManageUpdate'
                    }
                }
            },

        ],
    },
    {
        path: '/admin/OpenplatformOpenapiDataQueryPage',
        component: () => import('./OpenplatformOpenapiDataQueryPage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformOpenapiDataQueryPage',
            name: '开放平台数据查询',
            keepAlive: true
        }
    },
    {
        path: '/admin/OpenplatformOpenapiBatchDataQueryPage',
        component: () => import('./OpenplatformOpenapiBatchDataQueryPage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformOpenapiBatchDataQueryPage',
            name: '开放平台批量数据查询',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformOpenapiBatchQueryRecordDetail1',
                component: () => import('./OpenplatformOpenapiBatchQueryRecordDetailManagePage.vue'),
                props: route => ({ openplatformOpenapiBatchQueryRecordId: route.query.openplatformOpenapiBatchQueryRecordId }),
                meta: {
                    showInDrawer: true,
                    code: 'adminOpenplatformOpenapiBatchQueryRecordDetail1',
                    name: '开放接口批量查询记录明细管理',
                },
            }
        ]
    },
]
export default openplatformOpenapiAdminRoutes