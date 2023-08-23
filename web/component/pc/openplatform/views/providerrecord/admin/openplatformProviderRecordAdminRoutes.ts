const openplatformProviderRecordAdminRoutes = [
    {
        path: '/admin/openplatformProviderRecordManagePage',
        props: route => ({ openplatformOpenapiRecordId: route.query.openplatformOpenapiRecordId }),
        component: () => import('./OpenplatformProviderRecordManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformProviderRecordManagePage',
            name: '开放接口供应商调用记录',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformProviderRecordManageParamView',
                component: () => import('./OpenplatformProviderRecordManageParamViewPage.vue'),
                props: route => ({ openplatformProviderRecordId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformProviderRecordManageParamView',
                    name: '开放接口供应商调用记录参数查看',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformProviderRecordManageParamView'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformProviderRecordManageParamView'
                    }
                }
            },
        ]
    },
]
export default openplatformProviderRecordAdminRoutes