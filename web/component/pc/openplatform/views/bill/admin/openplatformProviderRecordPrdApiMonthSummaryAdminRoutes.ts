const openplatformProviderRecordPrdApiMonthSummaryAdminRoutes = [
    {
        path: '/admin/openplatformProviderRecordPrdApiMonthSummaryManagePage',
        component: () => import('./OpenplatformProviderRecordPrdApiMonthSummaryManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformProviderRecordPrdApiMonthSummaryManagePage',
            name: '开放平台供应商接口月汇总管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformProviderRecordPrdApiMonthSummaryManageAdd',
                component: () => import('./OpenplatformProviderRecordPrdApiMonthSummaryManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformProviderRecordPrdApiMonthSummaryManageAdd',
                    name: '开放平台供应商接口月汇总添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformProviderRecordPrdApiMonthSummaryManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformProviderRecordPrdApiMonthSummaryManageAdd'
                    }
                }
            },

            {
                path: '/admin/openplatformProviderRecordPrdApiMonthSummaryManageUpdate',
                component: () => import('./OpenplatformProviderRecordPrdApiMonthSummaryManageUpdatePage.vue'),
                props: route => ({ openplatformProviderRecordPrdApiMonthSummaryId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformProviderRecordPrdApiMonthSummaryManageUpdate',
                    name: '开放平台供应商接口月汇总修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformProviderRecordPrdApiMonthSummaryManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformProviderRecordPrdApiMonthSummaryManageUpdate'
                    }
                }
            },
        ]
    },
]
export default openplatformProviderRecordPrdApiMonthSummaryAdminRoutes