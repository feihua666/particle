const deptAdminRoutes = [
    {
        path: '/admin/deptManagePage',
        component: () => import('./DeptManagePage.vue'),
        meta: {
            root: true,
            code:'adminDeptManagePage',
            name: '部门管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/deptManageAdd',
                component: () => import('./DeptManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDeptManageAdd',
                    name: '部门添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDeptManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDeptManageAdd'
                    }
                }
            },

            {
                path: '/admin/deptManageUpdate',
                component: () => import('./DeptManageUpdatePage.vue'),
                props: route => ({ deptId: route.query.id,masterUserId: route.query.masterUserId,masterUserName: route.query.masterUserName}),
                meta: {
                    showInDrawer: true,
                    code:'adminDeptManageUpdate',
                    name: '部门修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDeptManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDeptManageUpdate'
                    }
                }
            },
        ]
    },
]
export default deptAdminRoutes