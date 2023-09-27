const reportSegmentTemplateAdminRoutes = [
    {
        path: '/admin/reportSegmentTemplateManagePage',
        component: () => import('./ReportSegmentTemplateManagePage.vue'),
        meta: {
            root: true,
            code:'adminReportSegmentTemplateManagePage',
            name: '报告片段模板管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/reportSegmentTemplateManageAdd',
                component: () => import('./ReportSegmentTemplateManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminReportSegmentTemplateManageAdd',
                    name: '报告片段模板添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminReportSegmentTemplateManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminReportSegmentTemplateManageAdd'
                    }
                }
            },

            {
                path: '/admin/reportSegmentTemplateManageUpdate',
                component: () => import('./ReportSegmentTemplateManageUpdatePage.vue'),
                props: route => ({ reportSegmentTemplateId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminReportSegmentTemplateManageUpdate',
                    name: '报告片段模板修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminReportSegmentTemplateManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminReportSegmentTemplateManageUpdate'
                    }
                }
            },
            {
                path: '/admin/reportSegmentTemplateManageCopy',
                component: () => import('./ReportSegmentTemplateManageCopyPage.vue'),
                props: route => ({ reportSegmentTemplateId: route.query.id, parentReportSegmentTemplateId: route.query.parentId }),
                meta: {
                    showInDrawer: true,
                    code:'adminReportSegmentTemplateManageCopy',
                    name: '报告片段模板复制',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminReportSegmentTemplateManageCopy'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminReportSegmentTemplateManageCopy'
                    }
                }
            },
        ]
    },
]
export default reportSegmentTemplateAdminRoutes