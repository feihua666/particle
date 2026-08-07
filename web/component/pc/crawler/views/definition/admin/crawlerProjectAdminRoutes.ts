const crawlerProjectAdminRoutes = [
    {
        path: '/admin/crawlerProjectManagePage',
        component: () => import('./CrawlerProjectManagePage.vue'),
        meta: {
            root: true,
            code:'adminCrawlerProjectManagePage',
            name: '爬虫项目管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/crawlerProjectManageAdd',
                component: () => import('./CrawlerProjectManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminCrawlerProjectManageAdd',
                    name: '爬虫项目添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrawlerProjectManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrawlerProjectManageAdd'
                    }
                }
            },

            {
                path: '/admin/crawlerProjectManageUpdate',
                component: () => import('./CrawlerProjectManageUpdatePage.vue'),
                props: route => ({ crawlerProjectId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminCrawlerProjectManageUpdate',
                    name: '爬虫项目修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrawlerProjectManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrawlerProjectManageUpdate'
                    }
                }
            },
        ]
    },
]
export default crawlerProjectAdminRoutes