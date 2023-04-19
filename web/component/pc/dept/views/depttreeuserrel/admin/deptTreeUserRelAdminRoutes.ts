const deptTreeUserRelAdminRoutes = [
    {
        path: '/admin/deptTreeUserRelManagePage',
        component: () => import('./DeptTreeUserRelManagePage.vue'),
        meta: {
            root: true,
            code:'adminDeptTreeUserRelManagePage',
            name: '部门树用户关系管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/deptTreeUserRelManageAdd',
                component: () => import('./DeptTreeUserRelManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDeptTreeUserRelManageAdd',
                    name: '部门树用户关系添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDeptTreeUserRelManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDeptTreeUserRelManageAdd'
                    }
                }
            },

            {
                path: '/admin/deptTreeUserRelManageUpdate',
                component: () => import('./DeptTreeUserRelManageUpdatePage.vue'),
                props: route => ({ deptTreeUserRelId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDeptTreeUserRelManageUpdate',
                    name: '部门树用户关系修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDeptTreeUserRelManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDeptTreeUserRelManageUpdate'
                    }
                }
            },
        ]
    },
]
export default deptTreeUserRelAdminRoutes