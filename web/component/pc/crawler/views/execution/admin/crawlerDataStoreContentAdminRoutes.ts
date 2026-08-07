const crawlerDataStoreContentAdminRoutes = [
    {
        path: '/admin/crawlerDataStoreContentManagePage',
        component: () => import('./CrawlerDataStoreContentManagePage.vue'),
        meta: {
            root: true,
            code:'adminCrawlerDataStoreContentManagePage',
            name: '爬虫结构数据存储内容管理',
            keepAlive: true
        },
        children: [
            {
                path: '/admin/crawlerDataStoreContentManageAdd',
                component: () => import('./CrawlerDataStoreContentManageAddPage.vue'),
                meta: {
                    showInDrawer: true,
                    code:'adminCrawlerDataStoreContentManageAdd',
                    name: '爬虫结构数据存储内容添加',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrawlerDataStoreContentManageAdd'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrawlerDataStoreContentManageAdd'
                    }
                }
            },

            {
                path: '/admin/crawlerDataStoreContentManageUpdate',
                component: () => import('./CrawlerDataStoreContentManageUpdatePage.vue'),
                props: route => ({ crawlerDataStoreContentId: route.query.id }),
                meta: {
                    showInDrawer: true,
                    code:'adminCrawlerDataStoreContentManageUpdate',
                    name: '爬虫结构数据存储内容修改',
                    // 将表单按钮显示在 drawer footer中
                    drawerProps: {
                        footerBoxId: 'adminCrawlerDataStoreContentManageUpdate'
                    },
                    formButtonsTeleportProps: {
                        disabled: false,
                        to: '#adminCrawlerDataStoreContentManageUpdate'
                    }
                }
            },
        ]
    },
]
export default crawlerDataStoreContentAdminRoutes