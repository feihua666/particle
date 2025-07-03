const cmsChannelViewRecordAdminRoutes = [
    {
        path: '/admin/cmsChannelViewRecordManagePage',
        component: () => import('./CmsChannelViewRecordManagePage.vue'),
        meta: {
            root: true,
            code:'adminCmsChannelViewRecordManagePage',
            name: '栏目访问记录管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/cmsChannelViewRecordManageAdd',
                component: () => import('./CmsChannelViewRecordManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminCmsChannelViewRecordManageAdd',
                    name: '栏目访问记录添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCmsChannelViewRecordManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCmsChannelViewRecordManageAdd'
                    }
                }
            },

            {
                path: '/admin/cmsChannelViewRecordManageUpdate',
                component: () => import('./CmsChannelViewRecordManageUpdatePage.vue'),
                props: route => ({ cmsChannelViewRecordId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminCmsChannelViewRecordManageUpdate',
                    name: '栏目访问记录修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCmsChannelViewRecordManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCmsChannelViewRecordManageUpdate'
                    }
                }
            },
        ]
    },
]
export default cmsChannelViewRecordAdminRoutes