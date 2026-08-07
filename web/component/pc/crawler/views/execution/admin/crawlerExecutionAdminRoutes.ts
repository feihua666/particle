const crawlerExecutionAdminRoutes = [
    {
        path: '/admin/crawlerExecutionManagePage',
        component: () => import('./CrawlerExecutionManagePage.vue'),
        meta: {
            root: true,
            code:'adminCrawlerExecutionManagePage',
            name: '爬虫执行实例管理',
            keepAlive: true
        },
        children: [
        ]
    },
]
export default crawlerExecutionAdminRoutes
