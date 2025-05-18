const dataCompanyIprPatentNoticeAdminRoutes = [
    {
        path: '/admin/dataCompanyIprPatentNoticeManagePage',
        component: () => import('./DataCompanyIprPatentNoticeManagePage.vue'),
        meta: {
            root: true,
            code:'adminDataCompanyIprPatentNoticeManagePage',
            name: '企业知识产权专利通知书信息管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/dataCompanyIprPatentNoticeManageAdd',
                component: () => import('./DataCompanyIprPatentNoticeManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentNoticeManageAdd',
                    name: '企业知识产权专利通知书信息添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentNoticeManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentNoticeManageAdd'
                    }
                }
            },

            {
                path: '/admin/dataCompanyIprPatentNoticeManageUpdate',
                component: () => import('./DataCompanyIprPatentNoticeManageUpdatePage.vue'),
                props: route => ({ dataCompanyIprPatentNoticeId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminDataCompanyIprPatentNoticeManageUpdate',
                    name: '企业知识产权专利通知书信息修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDataCompanyIprPatentNoticeManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDataCompanyIprPatentNoticeManageUpdate'
                    }
                }
            },
        ]
    },
]
export default dataCompanyIprPatentNoticeAdminRoutes