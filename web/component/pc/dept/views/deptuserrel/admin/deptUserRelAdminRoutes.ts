const deptUserRelAdminRoutes = [
    {
        path: '/admin/deptUserRelManagePage',
        component: () => import('./DeptUserRelManagePage.vue'),
        meta: {
            root: true,
            code:'adminDeptUserRelManagePage',
            name: '部门用户关系管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/deptUserRelManageAdd',
                component: () => import('./DeptUserRelManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDeptUserRelManageAdd',
                    name: '部门用户关系添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDeptUserRelManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDeptUserRelManageAdd'
                    }
                }
            },

            {
                path: '/admin/deptUserRelManageUpdate',
                component: () => import('./DeptUserRelManageUpdatePage.vue'),
                props: route => ({ deptUserRelId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDeptUserRelManageUpdate',
                    name: '部门用户关系修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDeptUserRelManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDeptUserRelManageUpdate'
                    }
                }
            },
        ]
    },
]
export default deptUserRelAdminRoutes