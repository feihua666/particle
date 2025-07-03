const cmsContentMultimediaAdminRoutes = [
    {
        path: '/admin/cmsContentMultimediaManagePage',
        component: () => import('./CmsContentMultimediaManagePage.vue'),
        meta: {
            root: true,
            code:'adminCmsContentMultimediaManagePage',
            name: '内容多媒体管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/cmsContentMultimediaManageAdd',
                component: () => import('./CmsContentMultimediaManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminCmsContentMultimediaManageAdd',
                    name: '内容多媒体添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCmsContentMultimediaManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCmsContentMultimediaManageAdd'
                    }
                }
            },

            {
                path: '/admin/cmsContentMultimediaManageUpdate',
                component: () => import('./CmsContentMultimediaManageUpdatePage.vue'),
                props: route => ({ cmsContentMultimediaId: route.query.id ,cmsContentId: route.query.cmsContentId,cmsContentTitle: route.query.cmsContentTitle}),
                meta: {
                    showInDrawer: true,
                    code:'adminCmsContentMultimediaManageUpdate',
                    name: '内容多媒体修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCmsContentMultimediaManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCmsContentMultimediaManageUpdate'
                    }
                }
            },
        ]
    },
]
export default cmsContentMultimediaAdminRoutes
