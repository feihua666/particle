const dataCompanyOpenCourtAnnouncementContentAdminRoutes = [
    {
        path: '/admin/dataCompanyOpenCourtAnnouncementContentManagePage',
        component: () => import('./DataCompanyOpenCourtAnnouncementContentManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyOpenCourtAnnouncementContentManagePage',
            name: '企业开庭公告内容管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyOpenCourtAnnouncementContentManageAdd',
                component: () => import('./DataCompanyOpenCourtAnnouncementContentManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyOpenCourtAnnouncementContentManageAdd',
                    name: '企业开庭公告内容添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyOpenCourtAnnouncementContentManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyOpenCourtAnnouncementContentManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyOpenCourtAnnouncementContentManageUpdate',
                component: () => import('./DataCompanyOpenCourtAnnouncementContentManageUpdatePage.vue'),
                props: route => ({ dataCompanyOpenCourtAnnouncementContentId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyOpenCourtAnnouncementContentManageUpdate',
                    name: '企业开庭公告内容修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyOpenCourtAnnouncementContentManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyOpenCourtAnnouncementContentManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyOpenCourtAnnouncementContentAdminRoutes