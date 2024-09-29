const openplatformProviderRecordPrdMonthBillAdminRoutes = [
    {
        path: '/admin/openplatformProviderRecordPrdMonthBillManagePage',
        component: () => import('./OpenplatformProviderRecordPrdMonthBillManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformProviderRecordPrdMonthBillManagePage',
            name: '开放平台供应商月账单管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformProviderRecordPrdMonthBillManageAdd',
                component: () => import('./OpenplatformProviderRecordPrdMonthBillManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformProviderRecordPrdMonthBillManageAdd',
                    name: '开放平台供应商月账单添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformProviderRecordPrdMonthBillManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformProviderRecordPrdMonthBillManageAdd'
                    }
                }
            },

            {
                path: '/admin/openplatformProviderRecordPrdMonthBillManageUpdate',
                component: () => import('./OpenplatformProviderRecordPrdMonthBillManageUpdatePage.vue'),
                props: route => ({ openplatformProviderRecordPrdMonthBillId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformProviderRecordPrdMonthBillManageUpdate',
                    name: '开放平台供应商月账单修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformProviderRecordPrdMonthBillManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformProviderRecordPrdMonthBillManageUpdate'
                    }
                }
            },
        ]
    },
]
export default openplatformProviderRecordPrdMonthBillAdminRoutes