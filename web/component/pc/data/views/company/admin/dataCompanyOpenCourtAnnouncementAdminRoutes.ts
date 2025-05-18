const dataCompanyOpenCourtAnnouncementAdminRoutes = [
    {
        path: '/admin/dataCompanyOpenCourtAnnouncementManagePage',
        component: () => import('./DataCompanyOpenCourtAnnouncementManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyOpenCourtAnnouncementManagePage',
            name: '企业开庭公告管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyOpenCourtAnnouncementManageAdd',
                component: () => import('./DataCompanyOpenCourtAnnouncementManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyOpenCourtAnnouncementManageAdd',
                    name: '企业开庭公告添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyOpenCourtAnnouncementManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyOpenCourtAnnouncementManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyOpenCourtAnnouncementManageUpdate',
                component: () => import('./DataCompanyOpenCourtAnnouncementManageUpdatePage.vue'),
                props: route => ({ dataCompanyOpenCourtAnnouncementId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyOpenCourtAnnouncementManageUpdate',
                    name: '企业开庭公告修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyOpenCourtAnnouncementManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyOpenCourtAnnouncementManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyOpenCourtAnnouncementAdminRoutes