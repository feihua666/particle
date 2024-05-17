const ssqCodeOpenedAdminRoutes = [
    {
        path: '/admin/ssqCodeOpenedManagePage',
        component: () => import('./SsqCodeOpenedManagePage.vue'),
        meta: {
            root: true,
            code:'adminSsqCodeOpenedManagePage',
            name: '双色球开奖管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/ssqCodeOpenedManageAdd',
                component: () => import('./SsqCodeOpenedManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminSsqCodeOpenedManageAdd',
                    name: '双色球开奖添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminSsqCodeOpenedManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminSsqCodeOpenedManageAdd'
                    }
                }
            },

            {
                path: '/admin/ssqCodeOpenedManageUpdate',
                component: () => import('./SsqCodeOpenedManageUpdatePage.vue'),
                props: route => ({ ssqCodeOpenedId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminSsqCodeOpenedManageUpdate',
                    name: '双色球开奖修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminSsqCodeOpenedManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminSsqCodeOpenedManageUpdate'
                    }
                }
            },
        ]
    },
]
export default ssqCodeOpenedAdminRoutes