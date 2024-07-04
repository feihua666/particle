const dataObjectAdminRoutes = [
    {
        path: '/admin/dataObjectManagePage',
        component: () => import('./DataObjectManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataObjectManagePage',
            name: '数据对象管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataObjectManageAdd',
                component: () => import('./DataObjectManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataObjectManageAdd',
                    name: '数据对象添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataObjectManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataObjectManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataObjectManageUpdate',
                component: () => import('./DataObjectManageUpdatePage.vue'),
                props: route => ({ dataObjectId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataObjectManageUpdate',
                    name: '数据对象修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataObjectManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataObjectManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataObjectAdminRoutes