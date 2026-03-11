const cmsTemplateAdminRoutes = [
    {
        path: '/admin/cmsTemplateManagePage',
        component: () => import('./CmsTemplateManagePage.vue'),
        meta: {
            root: true,
            code:'adminCmsTemplateManagePage',
            name: '模板管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/cmsTemplateManageAdd',
                component: () => import('./CmsTemplateManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminCmsTemplateManageAdd',
                    name: '模板添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCmsTemplateManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCmsTemplateManageAdd'
                    }
                }
            },

            {
                path: '/admin/cmsTemplateManageUpdate',
                component: () => import('./CmsTemplateManageUpdatePage.vue'),
                props: route => ({ cmsTemplateId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminCmsTemplateManageUpdate',
                    name: '模板修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCmsTemplateManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCmsTemplateManageUpdate'
                    }
                }
            },
        ]
    },
]
export default cmsTemplateAdminRoutes