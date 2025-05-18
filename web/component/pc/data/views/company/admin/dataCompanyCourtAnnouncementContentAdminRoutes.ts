const dataCompanyCourtAnnouncementContentAdminRoutes = [
    {
        path: '/admin/dataCompanyCourtAnnouncementContentManagePage',
        component: () => import('./DataCompanyCourtAnnouncementContentManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyCourtAnnouncementContentManagePage',
            name: '企业法院公告内容管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyCourtAnnouncementContentManageAdd',
                component: () => import('./DataCompanyCourtAnnouncementContentManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyCourtAnnouncementContentManageAdd',
                    name: '企业法院公告内容添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyCourtAnnouncementContentManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyCourtAnnouncementContentManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyCourtAnnouncementContentManageUpdate',
                component: () => import('./DataCompanyCourtAnnouncementContentManageUpdatePage.vue'),
                props: route => ({ dataCompanyCourtAnnouncementContentId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyCourtAnnouncementContentManageUpdate',
                    name: '企业法院公告内容修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyCourtAnnouncementContentManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyCourtAnnouncementContentManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyCourtAnnouncementContentAdminRoutes