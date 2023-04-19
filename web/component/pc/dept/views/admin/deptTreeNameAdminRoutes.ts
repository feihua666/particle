const deptTreeNameAdminRoutes = [
    {
        path: '/admin/deptTreeNameManagePage',
        component: () => import('./DeptTreeNameManagePage.vue'),
        meta: {
            root: true,
            code:'adminDeptTreeNameManagePage',
            name: '部门树名称管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/deptTreeNameManageAdd',
                component: () => import('./DeptTreeNameManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDeptTreeNameManageAdd',
                    name: '部门树名称添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDeptTreeNameManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDeptTreeNameManageAdd'
                    }
                }
            },

            {
                path: '/admin/deptTreeNameManageUpdate',
                component: () => import('./DeptTreeNameManageUpdatePage.vue'),
                props: route => ({ deptTreeNameId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDeptTreeNameManageUpdate',
                    name: '部门树名称修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDeptTreeNameManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDeptTreeNameManageUpdate'
                    }
                }
            },
        ]
    },
]
export default deptTreeNameAdminRoutes