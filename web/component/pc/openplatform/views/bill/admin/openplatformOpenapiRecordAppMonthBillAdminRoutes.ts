const openplatformOpenapiRecordAppMonthBillAdminRoutes = [
    {
        path: '/admin/openplatformOpenapiRecordAppMonthBillManagePage',
        component: () => import('./OpenplatformOpenapiRecordAppMonthBillManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformOpenapiRecordAppMonthBillManagePage',
            name: '开放平台应用月账单管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformOpenapiRecordAppMonthBillManageUpdate',
                component: () => import('./OpenplatformOpenapiRecordAppMonthBillManageUpdatePage.vue'),
                props: route => ({ openplatformOpenapiRecordAppMonthBillId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformOpenapiRecordAppMonthBillManageUpdate',
                    name: '开放平台应用月账单修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformOpenapiRecordAppMonthBillManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformOpenapiRecordAppMonthBillManageUpdate'
                    }
                }
            },
            {
                path: '/admin/openplatformOpenapiRecordAppMonthBillLastMonthStatistic',
                component: () => import('./OpenplatformOpenapiRecordAppMonthBillLastMonthStatisticPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformOpenapiRecordAppMonthBillLastMonthStatistic',
                    name: '开放平台应用上月账单统计',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformOpenapiRecordAppMonthBillLastMonthStatistic'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformOpenapiRecordAppMonthBillLastMonthStatistic'
                    }
                }
            },
            {
                path: '/admin/openplatformOpenapiRecordAppMonthBillThisMonthStatistic',
                component: () => import('./OpenplatformOpenapiRecordAppMonthBillThisMonthStatisticPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformOpenapiRecordAppMonthBillThisMonthStatistic',
                    name: '开放平台应用本月账单统计',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformOpenapiRecordAppMonthBillThisMonthStatistic'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformOpenapiRecordAppMonthBillThisMonthStatistic'
                    }
                }
            },
        ]
    },
]
export default openplatformOpenapiRecordAppMonthBillAdminRoutes