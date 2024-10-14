const openplatformOpenapiRecordCustomerMonthBillAdminRoutes = [
    {
        path: '/admin/openplatformOpenapiRecordCustomerMonthBillManagePage',
        component: () => import('./OpenplatformOpenapiRecordCustomerMonthBillManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformOpenapiRecordCustomerMonthBillManagePage',
            name: '开放平台客户月账单管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformOpenapiRecordCustomerMonthBillManageUpdate',
                component: () => import('./OpenplatformOpenapiRecordCustomerMonthBillManageUpdatePage.vue'),
                props: route => ({ openplatformOpenapiRecordCustomerMonthBillId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformOpenapiRecordCustomerMonthBillManageUpdate',
                    name: '开放平台客户月账单修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformOpenapiRecordCustomerMonthBillManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformOpenapiRecordCustomerMonthBillManageUpdate'
                    }
                }
            },
            {
                path: '/admin/openplatformOpenapiRecordCustomerMonthBillLastMonthStatistic',
                component: () => import('./OpenplatformOpenapiRecordCustomerMonthBillLastMonthStatisticPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformOpenapiRecordCustomerMonthBillLastMonthStatistic',
                    name: '开放平台客户上月账单统计',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformOpenapiRecordCustomerMonthBillLastMonthStatistic'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformOpenapiRecordCustomerMonthBillLastMonthStatistic'
                    }
                }
            },
            {
                path: '/admin/openplatformOpenapiRecordCustomerMonthBillThisMonthStatistic',
                component: () => import('./OpenplatformOpenapiRecordCustomerMonthBillThisMonthStatisticPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformOpenapiRecordCustomerMonthBillThisMonthStatistic',
                    name: '开放平台客户本月账单统计',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformOpenapiRecordCustomerMonthBillThisMonthStatistic'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformOpenapiRecordCustomerMonthBillThisMonthStatistic'
                    }
                }
            },

        ]
    },
]
export default openplatformOpenapiRecordCustomerMonthBillAdminRoutes