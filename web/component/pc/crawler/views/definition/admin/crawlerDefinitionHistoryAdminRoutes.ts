const crawlerDefinitionHistoryAdminRoutes = [
    {
        path: '/admin/crawlerDefinitionHistoryManagePage',
        component: () => import('./CrawlerDefinitionHistoryManagePage.vue'),
        meta: {
            root: true,
            code:'adminCrawlerDefinitionHistoryManagePage',
            name: '爬虫定义历史管理',
            keepAlive: true
        },
        children: [


            {
                path: '/admin/crawlerDefinitionHistoryManageUpdate',
                component: () => import('./CrawlerDefinitionHistoryManageUpdatePage.vue'),
                props: route => ({ crawlerDefinitionHistoryId: route.query.id,crawlerDefinitionName: route.query.crawlerDefinitionName }),
                meta: {
                    showInDrawer: true,
                    code:'adminCrawlerDefinitionHistoryManageUpdate',
                    name: '爬虫定义历史修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrawlerDefinitionHistoryManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrawlerDefinitionHistoryManageUpdate'
                    }
                }
            },
        ]
    },
]
export default crawlerDefinitionHistoryAdminRoutes
