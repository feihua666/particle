const openplatformAppQuotaAdminRoutes = [
    {
        path: '/admin/openplatformAppQuotaManagePage',
        component: () => import('./OpenplatformAppQuotaManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpenplatformAppQuotaManagePage',
            name: '开放平台应用额度管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/openplatformAppQuotaManageAdd',
                component: () => import('./OpenplatformAppQuotaManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformAppQuotaManageAdd',
                    name: '开放平台应用额度添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformAppQuotaManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformAppQuotaManageAdd'
                    }
                }
            },

            {
                path: '/admin/openplatformAppQuotaManageUpdate',
                component: () => import('./OpenplatformAppQuotaManageUpdatePage.vue'),
                props: route => ({ openplatformAppQuotaId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpenplatformAppQuotaManageUpdate',
                    name: '开放平台应用额度修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpenplatformAppQuotaManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpenplatformAppQuotaManageUpdate'
                    }
                }
            },
        ]
    },
]
export default openplatformAppQuotaAdminRoutes