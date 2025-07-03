const cmsContentViewRecordAdminRoutes = [
    {
        path: '/admin/cmsContentViewRecordManagePage',
        component: () => import('./CmsContentViewRecordManagePage.vue'),
        meta: {
            root: true,
            code:'adminCmsContentViewRecordManagePage',
            name: '内容访问记录管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/cmsContentViewRecordManageAdd',
                component: () => import('./CmsContentViewRecordManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminCmsContentViewRecordManageAdd',
                    name: '内容访问记录添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCmsContentViewRecordManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCmsContentViewRecordManageAdd'
                    }
                }
            },

            {
                path: '/admin/cmsContentViewRecordManageUpdate',
                component: () => import('./CmsContentViewRecordManageUpdatePage.vue'),
                props: route => ({ cmsContentViewRecordId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminCmsContentViewRecordManageUpdate',
                    name: '内容访问记录修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCmsContentViewRecordManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCmsContentViewRecordManageUpdate'
                    }
                }
            },
        ]
    },
]
export default cmsContentViewRecordAdminRoutes