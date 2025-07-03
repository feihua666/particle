const cmsSiteAdminRoutes = [
    {
        path: '/admin/cmsSiteManagePage',
        component: () => import('./CmsSiteManagePage.vue'),
        meta: {
            root: true,
            code:'adminCmsSiteManagePage',
            name: '站点管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/cmsSiteManageAdd',
                component: () => import('./CmsSiteManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminCmsSiteManageAdd',
                    name: '站点添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCmsSiteManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCmsSiteManageAdd'
                    }
                }
            },

            {
                path: '/admin/cmsSiteManageUpdate',
                component: () => import('./CmsSiteManageUpdatePage.vue'),
                props: route => ({ cmsSiteId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminCmsSiteManageUpdate',
                    name: '站点修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCmsSiteManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCmsSiteManageUpdate'
                    }
                }
            },
        ]
    },
]
export default cmsSiteAdminRoutes