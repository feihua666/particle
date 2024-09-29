const openplatformProviderRecordPrdApiDaySummaryAdminRoutes = [
    {
        path: '/admin/openplatformProviderRecordPrdApiDaySummaryManagePage',
        component: () => import('./OpenplatformProviderRecordPrdApiDaySummaryManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformProviderRecordPrdApiDaySummaryManagePage',
            name: '开放平台供应商接口日汇总管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformProviderRecordPrdApiDaySummaryManageAdd',
                component: () => import('./OpenplatformProviderRecordPrdApiDaySummaryManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformProviderRecordPrdApiDaySummaryManageAdd',
                    name: '开放平台供应商接口日汇总添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformProviderRecordPrdApiDaySummaryManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformProviderRecordPrdApiDaySummaryManageAdd'
                    }
                }
            },

            {
                path: '/admin/openplatformProviderRecordPrdApiDaySummaryManageUpdate',
                component: () => import('./OpenplatformProviderRecordPrdApiDaySummaryManageUpdatePage.vue'),
                props: route => ({ openplatformProviderRecordPrdApiDaySummaryId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformProviderRecordPrdApiDaySummaryManageUpdate',
                    name: '开放平台供应商接口日汇总修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformProviderRecordPrdApiDaySummaryManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformProviderRecordPrdApiDaySummaryManageUpdate'
                    }
                }
            },
        ]
    },
]
export default openplatformProviderRecordPrdApiDaySummaryAdminRoutes