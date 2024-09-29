const openplatformOpenapiRecordAppOpenapiDaySummaryAdminRoutes = [
    {
        path: '/admin/openplatformOpenapiRecordAppOpenapiDaySummaryManagePage',
        component: () => import('./OpenplatformOpenapiRecordAppOpenapiDaySummaryManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformOpenapiRecordAppOpenapiDaySummaryManagePage',
            name: '开放平台应用开放接口日汇总管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformOpenapiRecordAppOpenapiDaySummaryManageAdd',
                component: () => import('./OpenplatformOpenapiRecordAppOpenapiDaySummaryManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformOpenapiRecordAppOpenapiDaySummaryManageAdd',
                    name: '开放平台应用开放接口日汇总添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformOpenapiRecordAppOpenapiDaySummaryManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformOpenapiRecordAppOpenapiDaySummaryManageAdd'
                    }
                }
            },

            {
                path: '/admin/openplatformOpenapiRecordAppOpenapiDaySummaryManageUpdate',
                component: () => import('./OpenplatformOpenapiRecordAppOpenapiDaySummaryManageUpdatePage.vue'),
                props: route => ({ openplatformOpenapiRecordAppOpenapiDaySummaryId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformOpenapiRecordAppOpenapiDaySummaryManageUpdate',
                    name: '开放平台应用开放接口日汇总修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformOpenapiRecordAppOpenapiDaySummaryManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformOpenapiRecordAppOpenapiDaySummaryManageUpdate'
                    }
                }
            },
        ]
    },
]
export default openplatformOpenapiRecordAppOpenapiDaySummaryAdminRoutes