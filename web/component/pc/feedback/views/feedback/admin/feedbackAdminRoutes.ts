const feedbackAdminRoutes = [
    {
        path: '/admin/feedbackManagePage',
        component: () => import('./FeedbackManagePage.vue'),
        meta: {
            root: true,
            code:'adminFeedbackManagePage',
            name: '意见反馈管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/feedbackManageAdd',
                component: () => import('./FeedbackManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminFeedbackManageAdd',
                    name: '意见反馈添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminFeedbackManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminFeedbackManageAdd'
                    }
                }
            },
            {
                path: '/admin/feedbackReplyManageAdd',
                component: () => import('../../reply/admin/FeedbackReplyManageAddPage.vue'),
                props: route => ({ feedbackId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminFeedbackReplyManageAdd',
                    name: '意见反馈回复',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminFeedbackReplyManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminFeedbackReplyManageAdd'
                    }
                }
            },
            {
                path: '/admin/feedbackManageManualHandle',
                component: () => import('./FeedbackManageManualHandlePage.vue'),
                props: route => ({ feedbackId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminFeedbackManageManualHandle',
                    name: '意见反馈手动处理',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminFeedbackManageManualHandle'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminFeedbackManageManualHandle'
                    }
                }
            },
            {
                path: '/admin/feedbackReplyManagePage',
                component: () => import('../../reply/admin/FeedbackReplyManagePage.vue'),
                props: route => ({ feedbackId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminFeedbackReplyManagePage',
                    name: '意见反馈回复列表',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminFeedbackReplyManagePage'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminFeedbackReplyManagePage'
                    }
                }
            },
        ]
    },
]
export default feedbackAdminRoutes