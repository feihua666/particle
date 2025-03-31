const agiVectorStoreRawDocumentAdminRoutes = [
    {
        path: '/admin/agiVectorStoreRawDocumentManagePage',
        component: () => import('./AgiVectorStoreRawDocumentManagePage.vue'),
        meta: {
            root: true,
            code:'adminAgiVectorStoreRawDocumentManagePage',
            name: '知识存储原始文档管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/agiVectorStoreRawDocumentManageAdd',
                component: () => import('./AgiVectorStoreRawDocumentManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminAgiVectorStoreRawDocumentManageAdd',
                    name: '知识存储原始文档添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminAgiVectorStoreRawDocumentManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminAgiVectorStoreRawDocumentManageAdd'
                    }
                }
            },
        ]
    },
]
export default agiVectorStoreRawDocumentAdminRoutes
