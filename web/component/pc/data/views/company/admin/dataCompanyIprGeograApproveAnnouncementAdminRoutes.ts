const dataCompanyIprGeograApproveAnnouncementAdminRoutes = [
    {
        path: '/admin/dataCompanyIprGeograApproveAnnouncementManagePage',
        component: () => import('./DataCompanyIprGeograApproveAnnouncementManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprGeograApproveAnnouncementManagePage',
            name: '企业知识产权地理标识核准公告管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprGeograApproveAnnouncementManageAdd',
                component: () => import('./DataCompanyIprGeograApproveAnnouncementManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprGeograApproveAnnouncementManageAdd',
                    name: '企业知识产权地理标识核准公告添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprGeograApproveAnnouncementManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprGeograApproveAnnouncementManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprGeograApproveAnnouncementManageUpdate',
                component: () => import('./DataCompanyIprGeograApproveAnnouncementManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprGeograApproveAnnouncementId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprGeograApproveAnnouncementManageUpdate',
                    name: '企业知识产权地理标识核准公告修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprGeograApproveAnnouncementManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprGeograApproveAnnouncementManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprGeograApproveAnnouncementAdminRoutes