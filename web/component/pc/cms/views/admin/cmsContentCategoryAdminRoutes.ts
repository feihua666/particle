const cmsContentCategoryAdminRoutes = [
    {
        path: '/admin/cmsContentCategoryManagePage',
        component: () => import('./CmsContentCategoryManagePage.vue'),
        meta: {
            root: true,
            code:'adminCmsContentCategoryManagePage',
            name: '内容分类管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/cmsContentCategoryManageAdd',
                component: () => import('./CmsContentCategoryManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminCmsContentCategoryManageAdd',
                    name: '内容分类添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCmsContentCategoryManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCmsContentCategoryManageAdd'
                    }
                }
            },

            {
                path: '/admin/cmsContentCategoryManageUpdate',
                component: () => import('./CmsContentCategoryManageUpdatePage.vue'),
                props: route => ({ cmsContentCategoryId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminCmsContentCategoryManageUpdate',
                    name: '内容分类修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCmsContentCategoryManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCmsContentCategoryManageUpdate'
                    }
                }
            },
        ]
    },
]
export default cmsContentCategoryAdminRoutes