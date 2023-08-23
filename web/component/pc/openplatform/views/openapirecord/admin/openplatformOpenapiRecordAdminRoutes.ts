const openplatformOpenapiRecordAdminRoutes = [
    {
        path: '/admin/openplatformOpenapiRecordManagePage',
        component: () => import('./OpenplatformOpenapiRecordManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformOpenapiRecordManagePage',
            name: '开放接口调用记录',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformOpenapiRecordManageParamView',
                component: () => import('./OpenplatformOpenapiRecordManageParamViewPage.vue'),
                props: route => ({ openplatformOpenapiRecordId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformOpenapiRecordManageParamView',
                    name: '开放接口调用记录参数查看',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformOpenapiRecordManageParamView'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformOpenapiRecordManageParamView'
                    }
                }
            },
        ]
    },
]
export default openplatformOpenapiRecordAdminRoutes