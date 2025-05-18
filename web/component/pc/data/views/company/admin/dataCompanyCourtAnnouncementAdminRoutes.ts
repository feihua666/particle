const dataCompanyCourtAnnouncementAdminRoutes = [
    {
        path: '/admin/dataCompanyCourtAnnouncementManagePage',
        component: () => import('./DataCompanyCourtAnnouncementManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyCourtAnnouncementManagePage',
            name: '企业法院公告管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyCourtAnnouncementManageAdd',
                component: () => import('./DataCompanyCourtAnnouncementManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyCourtAnnouncementManageAdd',
                    name: '企业法院公告添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyCourtAnnouncementManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyCourtAnnouncementManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyCourtAnnouncementManageUpdate',
                component: () => import('./DataCompanyCourtAnnouncementManageUpdatePage.vue'),
                props: route => ({ dataCompanyCourtAnnouncementId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyCourtAnnouncementManageUpdate',
                    name: '企业法院公告修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyCourtAnnouncementManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyCourtAnnouncementManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyCourtAnnouncementAdminRoutes