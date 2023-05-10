const trackingPageAdminRoutes = [
    {
        path: '/admin/trackingPageManagePage',
        component: () => import('./TrackingPageManagePage.vue'),
        meta: {
            root: true,
            code:'adminTrackingPageManagePage',
            name: '埋点页面管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/trackingPageManageAdd',
                component: () => import('./TrackingPageManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminTrackingPageManageAdd',
                    name: '埋点页面添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminTrackingPageManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminTrackingPageManageAdd'
                    }
                }
            },

            {
                path: '/admin/trackingPageManageUpdate',
                component: () => import('./TrackingPageManageUpdatePage.vue'),
                props: route => ({ trackingPageId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminTrackingPageManageUpdate',
                    name: '埋点页面修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminTrackingPageManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminTrackingPageManageUpdate'
                    }
                }
            },
            {
                path: '/admin/trackingPageRecordPopoverManagePage',
                component: () => import('./TrackingPageRecordManagePage.vue'),
                props: route => ({ trackingPageCode: route.query.code }),
                meta: {
                    showInDrawer: true,
                    code:'adminTrackingPageRecordManagePage',
                    name: '页面埋点记录管理',
                },
                children: [
                ]
            },
        ]
    },
]
export default trackingPageAdminRoutes