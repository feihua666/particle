const opLogErrorAdminRoutes = [
    {
        path: '/admin/opLogErrorManagePage',
        component: () => import('./OpLogErrorManagePage.vue'),
        meta: {
            root: true,
            code:'adminOpLogErrorManagePage',
            name: '操作异常日志管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/OpLogErrorContentViewPage',
                component: () => import('./OpLogErrorContentViewPage.vue'),
                props: route => ({ opLogErrorContentId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminOpLogErrorContentViewPage',
                    name: '操作异常日志内容修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminOpLogErrorContentViewPage'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminOpLogErrorContentViewPage'
                    }
                }
            },
        ]
    },
]
export default opLogErrorAdminRoutes