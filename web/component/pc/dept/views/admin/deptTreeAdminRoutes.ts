const deptTreeAdminRoutes = [
    {
        path: '/admin/deptTreeManagePage',
        component: () => import('./DeptTreeManagePage.vue'),
        meta: {
            root: true,
            code:'adminDeptTreeManagePage',
            name: '部门树管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/deptTreeManageAdd',
                component: () => import('./DeptTreeManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDeptTreeManageAdd',
                    name: '部门树添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDeptTreeManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDeptTreeManageAdd'
                    }
                }
            },

            {
                path: '/admin/deptTreeManageUpdate',
                component: () => import('./DeptTreeManageUpdatePage.vue'),
                props: route => ({ deptTreeId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDeptTreeManageUpdate',
                    name: '部门树修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDeptTreeManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDeptTreeManageUpdate'
                    }
                }
            },
        ]
    },
]
export default deptTreeAdminRoutes