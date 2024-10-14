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
                path: '/admin/openplatformOpenapiRecordAppOpenapiMonthSummaryLastMonthStatistic',
                component: () => import('./OpenplatformOpenapiRecordAppOpenapiMonthSummaryLastMonthStatisticPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformOpenapiRecordAppOpenapiMonthSummaryLastMonthStatistic',
                    name: '开放平台应用开放接口上月汇总统计',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformOpenapiRecordAppOpenapiMonthSummaryLastMonthStatistic'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformOpenapiRecordAppOpenapiMonthSummaryLastMonthStatistic'
                    }
                }
            },
            {
                path: '/admin/openplatformOpenapiRecordAppOpenapiMonthSummaryThisMonthStatistic',
                component: () => import('./OpenplatformOpenapiRecordAppOpenapiMonthSummaryThisMonthStatisticPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformOpenapiRecordAppOpenapiMonthSummaryThisMonthStatistic',
                    name: '开放平台应用开放接口本月汇总统计',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformOpenapiRecordAppOpenapiMonthSummaryThisMonthStatistic'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformOpenapiRecordAppOpenapiMonthSummaryThisMonthStatistic'
                    }
                }
            },

        ]
    },
]
export default openplatformOpenapiRecordAppOpenapiMonthSummaryAdminRoutes