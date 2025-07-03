const cmsSiteIndexViewRecordAdminRoutes = [
    {
        path: '/admin/cmsSiteIndexViewRecordManagePage',
        component: () => import('./CmsSiteIndexViewRecordManagePage.vue'),
        meta: {
            root: true,
            code:'adminCmsSiteIndexViewRecordManagePage',
            name: '站点首页访问记录管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/cmsSiteIndexViewRecordManageAdd',
                component: () => import('./CmsSiteIndexViewRecordManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminCmsSiteIndexViewRecordManageAdd',
                    name: '站点首页访问记录添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCmsSiteIndexViewRecordManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCmsSiteIndexViewRecordManageAdd'
                    }
                }
            },

            {
                path: '/admin/cmsSiteIndexViewRecordManageUpdate',
                component: () => import('./CmsSiteIndexViewRecordManageUpdatePage.vue'),
                props: route => ({ cmsSiteIndexViewRecordId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminCmsSiteIndexViewRecordManageUpdate',
                    name: '站点首页访问记录修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCmsSiteIndexViewRecordManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCmsSiteIndexViewRecordManageUpdate'
                    }
                }
            },
        ]
    },
]
export default cmsSiteIndexViewRecordAdminRoutes