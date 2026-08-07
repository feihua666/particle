const crawlerRawStoreContentAdminRoutes = [
    {
        path: '/admin/crawlerRawStoreContentManagePage',
        component: () => import('./CrawlerRawStoreContentManagePage.vue'),
        meta: {
            root: true,
            code:'adminCrawlerRawStoreContentManagePage',
            name: '爬虫原始数据存储内容管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/crawlerRawStoreContentManageAdd',
                component: () => import('./CrawlerRawStoreContentManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminCrawlerRawStoreContentManageAdd',
                    name: '爬虫原始数据存储内容添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrawlerRawStoreContentManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrawlerRawStoreContentManageAdd'
                    }
                }
            },

            {
                path: '/admin/crawlerRawStoreContentManageUpdate',
                component: () => import('./CrawlerRawStoreContentManageUpdatePage.vue'),
                props: route => ({ crawlerRawStoreContentId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminCrawlerRawStoreContentManageUpdate',
                    name: '爬虫原始数据存储内容修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrawlerRawStoreContentManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrawlerRawStoreContentManageUpdate'
                    }
                }
            },
        ]
    },
]
export default crawlerRawStoreContentAdminRoutes