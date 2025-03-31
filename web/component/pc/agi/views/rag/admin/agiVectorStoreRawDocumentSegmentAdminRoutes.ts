const agiVectorStoreRawDocumentSegmentAdminRoutes = [
    {
        path: '/admin/agiVectorStoreRawDocumentSegmentManagePage',
        component: () => import('./AgiVectorStoreRawDocumentSegmentManagePage.vue'),
        meta: {
            root: true,
            code:'adminAgiVectorStoreRawDocumentSegmentManagePage',
            name: '知识存储原始文档片段管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/agiVectorStoreRawDocumentSegmentManageAdd',
                component: () => import('./AgiVectorStoreRawDocumentSegmentManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminAgiVectorStoreRawDocumentSegmentManageAdd',
                    name: '知识存储原始文档片段添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminAgiVectorStoreRawDocumentSegmentManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminAgiVectorStoreRawDocumentSegmentManageAdd'
                    }
                }
            },

            {
                path: '/admin/agiVectorStoreRawDocumentSegmentManageUpdate',
                component: () => import('./AgiVectorStoreRawDocumentSegmentManageUpdatePage.vue'),
                props: route => ({ agiVectorStoreRawDocumentSegmentId: route.query.id,agiVectorStoreRawDocumentId: route.query.agiVectorStoreRawDocumentId,agiVectorStoreRawDocumentName: route.query.agiVectorStoreRawDocumentName }),
                meta: {
                    showInDrawer: true,
                    code:'adminAgiVectorStoreRawDocumentSegmentManageUpdate',
                    name: '知识存储原始文档片段修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminAgiVectorStoreRawDocumentSegmentManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminAgiVectorStoreRawDocumentSegmentManageUpdate'
                    }
                }
            },
        ]
    },
]
export default agiVectorStoreRawDocumentSegmentAdminRoutes
