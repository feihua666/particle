const adminComponentAdminRoutes = [
    {
        path: '/admin/adminComponentManagePage',
        component: () => import('./AdminComponentManagePage.vue'),
        meta: {
            root: true,
            code:'adminAdminComponentManagePage',
            name: '组件管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/adminComponentManageAdd',
                component: () => import('./AdminComponentManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminAdminComponentManageAdd',
                    name: '组件添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminAdminComponentManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminAdminComponentManageAdd'
                    }
                }
            },

            {
                path: '/admin/adminComponentManageUpdate',
                component: () => import('./AdminComponentManageUpdatePage.vue'),
                props: route => ({ adminComponentId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminAdminComponentManageUpdate',
                    name: '组件修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminAdminComponentManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminAdminComponentManageUpdate'
                    }
                }
            },
        ]
    },
]
export default adminComponentAdminRoutes