const cmsContentAdminRoutes = [
    {
        path: '/admin/cmsContentManagePage',
        component: () => import('./CmsContentManagePage.vue'),
        meta: {
            root: true,
            code:'adminCmsContentManagePage',
            name: '内容管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/cmsContentManageAdd',
                component: () => import('./CmsContentManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminCmsContentManageAdd',
                    name: '内容添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCmsContentManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCmsContentManageAdd'
                    }
                }
            },

            {
                path: '/admin/cmsContentManageUpdate',
                component: () => import('./CmsContentManageUpdatePage.vue'),
                props: route => ({ cmsContentId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminCmsContentManageUpdate',
                    name: '内容修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCmsContentManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCmsContentManageUpdate'
                    }
                }
            },
        ]
    },
]
export default cmsContentAdminRoutes