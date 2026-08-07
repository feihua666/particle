const crawlerDefinitionAdminRoutes = [
    {
        path: '/admin/crawlerDefinitionManagePage',
        component: () => import('./CrawlerDefinitionManagePage.vue'),
        meta: {
            root: true,
            code:'adminCrawlerDefinitionManagePage',
            name: '爬虫定义管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/crawlerDefinitionManageAdd',
                component: () => import('./CrawlerDefinitionManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminCrawlerDefinitionManageAdd',
                    name: '爬虫定义添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrawlerDefinitionManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrawlerDefinitionManageAdd'
                    }
                }
            },

            {
                path: '/admin/crawlerDefinitionManageUpdate',
                component: () => import('./CrawlerDefinitionManageUpdatePage.vue'),
                props: route => ({ crawlerDefinitionId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminCrawlerDefinitionManageUpdate',
                    name: '爬虫定义修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrawlerDefinitionManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrawlerDefinitionManageUpdate'
                    }
                }
            },
            {
                path: '/admin/definition/crawlerDefinitionHistoryManageUpdate',
                component: () => import('./CrawlerDefinitionHistoryManageUpdatePage.vue'),
                props: route => ({ crawlerDefinitionHistoryId: route.query.crawlerDefinitionHistoryId,crawlerDefinitionName: route.query.crawlerDefinitionName }),
                meta: {
                    showInDrawer: true,
                    code:'adminDefinitionCrawlerDefinitionHistoryManageUpdate',
                    name: '爬虫定义历史修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDefinitionCrawlerDefinitionHistoryManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDefinitionCrawlerDefinitionHistoryManageUpdate'
                    }
                }
            },
            {
                path: '/admin/definition/crawlerExecutionExecutePage',
                component: () => import('../../execution/admin/CrawlerExecutionExecutePage.vue'),
                props: route => ({ crawlerDefinitionHistoryId: route.query.crawlerDefinitionHistoryId,
                    crawlerDefinitionName: route.query.crawlerDefinitionName }),
                meta: {
                    showInDrawer: true,
                    code:'adminDefinitionCrawlerExecutionExecutePage',
                    name: '爬虫定义执行',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminDefinitionCrawlerExecutionExecutePage'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminDefinitionCrawlerExecutionExecutePage'
                    }
                }
            },
        ]
    },
]
export default crawlerDefinitionAdminRoutes
