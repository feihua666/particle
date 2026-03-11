const auditRecordSnapshotDataAdminRoutes = [
    {
        path: '/admin/auditRecordSnapshotDataManagePage',
        component: () => import('./AuditRecordSnapshotDataManagePage.vue'),
        meta: {
            root: true,
            code:'adminAuditRecordSnapshotDataManagePage',
            name: '审核记录数据快照管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/auditRecordSnapshotDataManageAdd',
                component: () => import('./AuditRecordSnapshotDataManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminAuditRecordSnapshotDataManageAdd',
                    name: '审核记录数据快照添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminAuditRecordSnapshotDataManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminAuditRecordSnapshotDataManageAdd'
                    }
                }
            },

            {
                path: '/admin/auditRecordSnapshotDataManageUpdate',
                component: () => import('./AuditRecordSnapshotDataManageUpdatePage.vue'),
                props: route => ({ auditRecordSnapshotDataId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminAuditRecordSnapshotDataManageUpdate',
                    name: '审核记录数据快照修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminAuditRecordSnapshotDataManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminAuditRecordSnapshotDataManageUpdate'
                    }
                }
            },
        ]
    },
]
export default auditRecordSnapshotDataAdminRoutes