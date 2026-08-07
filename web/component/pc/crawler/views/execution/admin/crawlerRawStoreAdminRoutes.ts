const crawlerRawStoreAdminRoutes = [
    {
        path: '/admin/crawlerRawStoreManagePage',
        component: () => import('./CrawlerRawStoreManagePage.vue'),
        meta: {
            root: true,
            code:'adminCrawlerRawStoreManagePage',
            name: '爬虫原始数据存储管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/crawlerRawStoreManageAdd',
                component: () => import('./CrawlerRawStoreManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminCrawlerRawStoreManageAdd',
                    name: '爬虫原始数据存储添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrawlerRawStoreManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrawlerRawStoreManageAdd'
                    }
                }
            },

            {
                path: '/admin/crawlerRawStoreManageUpdate',
                component: () => import('./CrawlerRawStoreManageUpdatePage.vue'),
                props: route => ({ crawlerRawStoreId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminCrawlerRawStoreManageUpdate',
                    name: '爬虫原始数据存储修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrawlerRawStoreManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrawlerRawStoreManageUpdate'
                    }
                }
            },
        ]
    },
]
export default crawlerRawStoreAdminRoutes