const reportReportApiAdminRoutes = [
    {
        path: '/admin/reportReportApiManagePage',
        component: () => import('./ReportReportApiManagePage.vue'),
        meta: {
            root: true,
            code:'adminReportReportApiManagePage',
            name: '报告接口管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/reportReportApiManageAdd',
                component: () => import('./ReportReportApiManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminReportReportApiManageAdd',
                    name: '报告接口添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminReportReportApiManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminReportReportApiManageAdd'
                    }
                }
            },

            {
                path: '/admin/reportReportApiManageUpdate',
                component: () => import('./ReportReportApiManageUpdatePage.vue'),
                props: route => ({ reportReportApiId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminReportReportApiManageUpdate',
                    name: '报告接口修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminReportReportApiManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminReportReportApiManageUpdate'
                    }
                }
            },
        ]
    },
]
export default reportReportApiAdminRoutes