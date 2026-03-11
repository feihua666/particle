const auditRecordSnapshotAttachmentAdminRoutes = [
    {
        path: '/admin/auditRecordSnapshotAttachmentManagePage',
        component: () => import('./AuditRecordSnapshotAttachmentManagePage.vue'),
        meta: {
            root: true,
            code:'adminAuditRecordSnapshotAttachmentManagePage',
            name: '审核记录附件快照管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/auditRecordSnapshotAttachmentManageAdd',
                component: () => import('./AuditRecordSnapshotAttachmentManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminAuditRecordSnapshotAttachmentManageAdd',
                    name: '审核记录附件快照添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminAuditRecordSnapshotAttachmentManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminAuditRecordSnapshotAttachmentManageAdd'
                    }
                }
            },

            {
                path: '/admin/auditRecordSnapshotAttachmentManageUpdate',
                component: () => import('./AuditRecordSnapshotAttachmentManageUpdatePage.vue'),
                props: route => ({ auditRecordSnapshotAttachmentId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminAuditRecordSnapshotAttachmentManageUpdate',
                    name: '审核记录附件快照修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminAuditRecordSnapshotAttachmentManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminAuditRecordSnapshotAttachmentManageUpdate'
                    }
                }
            },
        ]
    },
]
export default auditRecordSnapshotAttachmentAdminRoutes