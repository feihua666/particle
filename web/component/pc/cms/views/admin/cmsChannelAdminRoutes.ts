const cmsChannelAdminRoutes = [
    {
        path: '/admin/cmsChannelManagePage',
        component: () => import('./CmsChannelManagePage.vue'),
        meta: {
            root: true,
            code:'adminCmsChannelManagePage',
            name: '栏目管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/cmsChannelManageAdd',
                component: () => import('./CmsChannelManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminCmsChannelManageAdd',
                    name: '栏目添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCmsChannelManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCmsChannelManageAdd'
                    }
                }
            },

            {
                path: '/admin/cmsChannelManageUpdate',
                component: () => import('./CmsChannelManageUpdatePage.vue'),
                props: route => ({ cmsChannelId: route.query.id,
                    relatedCmsContentId: route.query.relatedCmsContentId,
                    relatedCmsContentTitle: route.query.relatedCmsContentTitle }),
                meta: {
                    showInDrawer: true,
                    code:'adminCmsChannelManageUpdate',
                    name: '栏目修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCmsChannelManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCmsChannelManageUpdate'
                    }
                }
            },
            {
                path: '/admin/cmsChannelManageUrlPage',
                component: () => import('./CmsChannelManageUrlPage.vue'),
                props: route => ({ cmsChannelId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminCmsChannelManageUrl',
                    name: '栏目地址',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCmsChannelManageUrl'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCmsChannelManageUrl'
                    }
                }
            },
        ]
    },
]
export default cmsChannelAdminRoutes
