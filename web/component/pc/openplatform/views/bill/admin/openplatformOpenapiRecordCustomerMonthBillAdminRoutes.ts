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
                path: '/admin/openplatformOpenapiRecordCustomerMonthBillManageAdd',
                component: () => import('./OpenplatformOpenapiRecordCustomerMonthBillManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformOpenapiRecordCustomerMonthBillManageAdd',
                    name: '开放平台客户月账单添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformOpenapiRecordCustomerMonthBillManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformOpenapiRecordCustomerMonthBillManageAdd'
                    }
                }
            },

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
        ]
    },
]
export default openplatformOpenapiRecordCustomerMonthBillAdminRoutes