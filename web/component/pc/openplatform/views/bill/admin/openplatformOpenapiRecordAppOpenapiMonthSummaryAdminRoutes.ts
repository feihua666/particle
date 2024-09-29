const openplatformOpenapiRecordAppOpenapiMonthSummaryAdminRoutes = [
    {
        path: '/admin/openplatformOpenapiRecordAppOpenapiMonthSummaryManagePage',
        component: () => import('./OpenplatformOpenapiRecordAppOpenapiMonthSummaryManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformOpenapiRecordAppOpenapiMonthSummaryManagePage',
            name: '开放平台应用开放接口月汇总管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformOpenapiRecordAppOpenapiMonthSummaryManageAdd',
                component: () => import('./OpenplatformOpenapiRecordAppOpenapiMonthSummaryManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformOpenapiRecordAppOpenapiMonthSummaryManageAdd',
                    name: '开放平台应用开放接口月汇总添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformOpenapiRecordAppOpenapiMonthSummaryManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformOpenapiRecordAppOpenapiMonthSummaryManageAdd'
                    }
                }
            },

            {
                path: '/admin/openplatformOpenapiRecordAppOpenapiMonthSummaryManageUpdate',
                component: () => import('./OpenplatformOpenapiRecordAppOpenapiMonthSummaryManageUpdatePage.vue'),
                props: route => ({ openplatformOpenapiRecordAppOpenapiMonthSummaryId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformOpenapiRecordAppOpenapiMonthSummaryManageUpdate',
                    name: '开放平台应用开放接口月汇总修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformOpenapiRecordAppOpenapiMonthSummaryManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformOpenapiRecordAppOpenapiMonthSummaryManageUpdate'
                    }
                }
            },
        ]
    },
]
export default openplatformOpenapiRecordAppOpenapiMonthSummaryAdminRoutes