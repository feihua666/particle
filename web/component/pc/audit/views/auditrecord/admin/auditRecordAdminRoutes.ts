const auditRecordAdminRoutes = [
    {
        path: '/admin/auditRecordManagePage',
        component: () => import('./AuditRecordManagePage.vue'),
        meta: {
            root: true,
            code:'adminAuditRecordManagePage',
            name: '审核记录管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/auditRecordManageAdd',
                component: () => import('./AuditRecordManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminAuditRecordManageAdd',
                    name: '审核记录添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminAuditRecordManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminAuditRecordManageAdd'
                    }
                }
            },

            {
                path: '/admin/auditRecordManageUpdate',
                component: () => import('./AuditRecordManageUpdatePage.vue'),
                props: route => ({ auditRecordId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminAuditRecordManageUpdate',
                    name: '审核记录修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminAuditRecordManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminAuditRecordManageUpdate'
                    }
                }
            },
        ]
    },
]
export default auditRecordAdminRoutes