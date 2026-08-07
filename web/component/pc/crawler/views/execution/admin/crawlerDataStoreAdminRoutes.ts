const crawlerDataStoreAdminRoutes = [
    {
        path: '/admin/crawlerDataStoreManagePage',
        component: () => import('./CrawlerDataStoreManagePage.vue'),
        meta: {
            root: true,
            code:'adminCrawlerDataStoreManagePage',
            name: '爬虫结构数据存储管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/crawlerDataStoreManageAdd',
                component: () => import('./CrawlerDataStoreManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminCrawlerDataStoreManageAdd',
                    name: '爬虫结构数据存储添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrawlerDataStoreManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrawlerDataStoreManageAdd'
                    }
                }
            },

            {
                path: '/admin/crawlerDataStoreManageUpdate',
                component: () => import('./CrawlerDataStoreManageUpdatePage.vue'),
                props: route => ({ crawlerDataStoreId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminCrawlerDataStoreManageUpdate',
                    name: '爬虫结构数据存储修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrawlerDataStoreManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrawlerDataStoreManageUpdate'
                    }
                }
            },
        ]
    },
]
export default crawlerDataStoreAdminRoutes