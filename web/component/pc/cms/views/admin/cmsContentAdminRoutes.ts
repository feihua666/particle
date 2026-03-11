const cmsContentAdminRoutes = [
    {
        path: '/admin/cmsContentManagePage',
        component: () => import('./CmsContentManagePage.vue'),
        meta: {
            root: true,
            code: 'adminCmsContentManagePage',
            name: '内容管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/cmsContentManageAdd',
                component: () => import('./CmsContentManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code: 'adminCmsContentManageAdd',
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
                    code: 'adminCmsContentManageUpdate',
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
            {
                path: '/admin/cmsContentManageAudit',
                component: () => import('./CmsContentManageAuditPage.vue'),
                props: route => ({ cmsContentId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code: 'adminCmsContentManageAudit',
                    name: '内容审核',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCmsContentManageAudit'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCmsContentManageAudit'
                    }
                }
            },
            {
                path: '/admin/cmsContentManageUrlPage',
                component: () => import('./CmsContentManageUrlPage.vue'),
                props: route => ({ cmsContentId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminCmsContentManageUrl',
                    name: '内容地址',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCmsContentManageUrl'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCmsContentManageUrl'
                    }
                }
            },
        ]
    },
]
export default cmsContentAdminRoutes
