import { defineConfig } from 'vitepress'
import { getFirstLineTitle } from './scripts/projectEnhance.js'
const backendComponentsSidebar = getBackendComponentsSidebar()
const frontendComponentsSidebar = getFrontendComponentsSidebar()
export default defineConfig({
    base: '/',
    lang: 'zh-CN',
    title: "Particle",
    description: "一个基于SpringBoot和Vue的快速开发脚手架",
    head: [
        // favicon.ico
        ['link', { rel: 'icon', type: "image/png", href: `/favicon.png` }]
    ],
    rewrites: {
        'components/fronted/overview.md': 'web/components/fronted/overview.md'
    },
    themeConfig: {
        // 如果站点有图标，则可以通过传递图片路径来显示它。应该将图标直接放在 public 中，并赋值该绝对路径。
        logo: '/images/logo/logo.png',
        // 默认情况下，nav 显示 config.title 作为站点的标题。如果想更改导航栏上显示的内容，可以在 themeConfig.siteTitle 选项中定义自定义文本。
        // 添加图标时，它会与站点标题一起显示。如果只需要图标并且想要隐藏站点标题文本，请将 siteTitle 选项设置为 false
        // siteTitle: false,
        nav: [
            { text: '首页', link: '/' },
            { text: '指南', link: '/guide/what-is-particle', activeMatch: '/guide/' },
            { text: '后端组件', link: '/components/backend/overview', activeMatch: '^/(components/backend|global|component)/' },
            { text: '前端组件', link: '/components/frontend/overview', activeMatch: '^/(components/frontend|web/global|web/component)/' },
            {text: '更新日志', link: '/guide/changelog'}
        ],
        sidebar: {
            '/guide/': [
                {
                    text: '简介',
                    collapsed: false,
                    items: [
                        { text: '什么是 Particle？', link: '/guide/what-is-particle' },
                        { text: '快速开始', link: '/guide/getting-started' },
                        { text: '技术选型', link: '/guide/technology-selection' },
                        { text: '项目结构', link: '/guide/project-structure' },
                        { text: '配置指南', link: '/guide/configuration' },
                        { text: '部署指南', link: '/guide/deployment' },
                        { text: '最佳实践', link: '/guide/best-practices' },
                        { text: '应用场景', link: '/guide/use-cases' },
                        { text: '示例应用', link: '/guide/example-application' },
                    ]
                },
                {
                    text: '社区',
                    collapsed: false,
                    items: [
                        { text: '社区和支持', link: '/guide/community-support' },
                        { text: '更新日志', link: '/guide/changelog' },
                    ]
                }
            ],
            '/components/backend/': backendComponentsSidebar,
            '/global/': backendComponentsSidebar,
            '/component/': backendComponentsSidebar,

            '/components/frontend/': frontendComponentsSidebar,
            '/web/global/': frontendComponentsSidebar,
            '/web/component/': frontendComponentsSidebar,
        },
        // 可以定义此选项以在导航栏中展示带有图标的社交帐户链接。
        socialLinks: [
            { icon: 'github', link: 'https://github.com/feihua666/particle' }
        ],
        // 页脚配置。可以添加 message 和 copyright。由于设计原因，仅当页面不包含侧边栏时才会显示页脚。
        footer: {
            message: 'Released under the MIT License.',
            copyright: 'Made with ❤️ by Particle'
        },
        // 编辑链接可让显示链接以编辑 Git 管理服务 (例如 GitHub 或 GitLab) 上的页面。
        editLink: {
            // pattern: 'https://github.com/feihua666/particle/edit/master/doc/vitepress/docs/:path',
            // filePath 通过打印日志时，输出类似：global/index.md、global/global-actuator-boot-starter.md
            pattern: editLinkPattern,
            text: '在 GitHub 上编辑此页面'
        },
        // 将此值设置为 false 可禁止渲染大纲容器。
        outline: {
            level: [2, 3],
            label: '页面导航'
        },
        // 可用于自定义出现在上一页和下一页链接上方的文本。
        docFooter: {
            prev: '上一页',
            next: '下一页'
        },
        // 用于自定义侧边栏菜单标签，该标签仅在移动端视图中显示。
        sidebarMenuLabel: '菜单',
        // 用于自定义返回顶部按钮的标签，该标签仅在移动端视图中显示。
        returnToTopLabel: '回到顶部',
        // 允许自定义上次更新的文本和日期格式。
        lastUpdated: {
            text: '最后更新于'
        },
        // 用于自定义深色模式开关标签，该标签仅在移动端视图中显示。
        darkModeSwitchLabel: '主题',
        // 用于自定义悬停时显示的浅色模式开关标题。
        lightModeSwitchTitle: '切换到浅色模式',
        // 用于自定义悬停时显示的深色模式开关标题。
        darkModeSwitchTitle: '切换到深色模式'
    }
})

/**
 * 后端组件侧边栏
 */
function getBackendComponentsSidebar() {
    return [
        {
            text: '组件总览',
            collapsed: false,
            items: [
                { text: '组件总览', link: '/components/backend/overview' },
            ]
        },
        {
            text: '全局组件<br/>global',
            collapsed: false,
            items: [
                { text: '概览', link: '/global/' },
                { text: getFirstLineTitle('global/global-actuator-boot-starter/README.md') + '<br/>global-actuator-boot-starter', link: '/global/global-actuator-boot-starter' },
                { text: getFirstLineTitle('global/global-ai-boot-starter/README.md') + '<br/>global-ai-boot-starter', link: '/global/global-ai-boot-starter' },
                { text: getFirstLineTitle('global/global-autoconfigure-boot-starter/README.md') + '<br/>global-autoconfigure-boot-starter', link: '/global/global-autoconfigure-boot-starter' },
                { text: getFirstLineTitle('global/global-big-datasource-boot-starter/README.md') + '<br/>global-big-datasource-boot-starter', link: '/global/global-big-datasource-boot-starter' },
                { text: getFirstLineTitle('global/global-bootstrap-boot-starter/README.md') + '<br/>global-bootstrap-boot-starter', link: '/global/global-bootstrap-boot-starter' },
                { text: getFirstLineTitle('global/global-cache-boot-starter/README.md') + '<br/>global-cache-boot-starter', link: '/global/global-cache-boot-starter' },
                { text: getFirstLineTitle('global/global-captcha-boot-starter/README.md') + '<br/>global-captcha-boot-starter', link: '/global/global-captcha-boot-starter' },
                { text: getFirstLineTitle('global/global-catchlog-boot-starter/README.md') + '<br/>global-catchlog-boot-starter', link: '/global/global-catchlog-boot-starter' },
                { text: getFirstLineTitle('global/global-common/README.md') + '<br/>global-common', link: '/global/global-common' },
                { text: getFirstLineTitle('global/global-concurrency-boot-starter/README.md') + '<br/>global-concurrency-boot-starter', link: '/global/global-concurrency-boot-starter' },
                { text: getFirstLineTitle('global/global-crawler-boot-starter/README.md') + '<br/>global-crawler-boot-starter', link: '/global/global-crawler-boot-starter' },
                { text: getFirstLineTitle('global/global-data-audit-boot-starter/README.md') + '<br/>global-data-audit-boot-starter', link: '/global/global-data-audit-boot-starter' },
                { text: getFirstLineTitle('global/global-data-permission-boot-starter/README.md') + '<br/>global-data-permission-boot-starter', link: '/global/global-data-permission-boot-starter' },
                { text: getFirstLineTitle('global/global-datasource-boot-starter/README.md') + '<br/>global-datasource-boot-starter', link: '/global/global-datasource-boot-starter' },
                { text: getFirstLineTitle('global/global-document-boot-starter/README.md') + '<br/>global-document-boot-starter', link: '/global/global-document-boot-starter' },
                { text: getFirstLineTitle('global/global-domain-boot-starter/README.md') + '<br/>global-domain-boot-starter', link: '/global/global-domain-boot-starter' },
                { text: getFirstLineTitle('global/global-dto/README.md') + '<br/>global-dto', link: '/global/global-dto' },
                { text: getFirstLineTitle('global/global-elasticsearch-boot-starter/README.md') + '<br/>global-elasticsearch-boot-starter', link: '/global/global-elasticsearch-boot-starter' },
                { text: getFirstLineTitle('global/global-exception-handle-boot-starter/README.md') + '<br/>global-exception-handle-boot-starter', link: '/global/global-exception-handle-boot-starter' },
                { text: getFirstLineTitle('global/global-exception/README.md') + '<br/>global-exception', link: '/global/global-exception' },
                { text: getFirstLineTitle('global/global-freemarker-boot-starter/README.md') + '<br/>global-freemarker-boot-starter', link: '/global/global-freemarker-boot-starter' },
                { text: getFirstLineTitle('global/global-logging-boot-starter/README.md') + '<br/>global-logging-boot-starter', link: '/global/global-logging-boot-starter' },
                { text: getFirstLineTitle('global/global-messaging-boot-starter/README.md') + '<br/>global-messaging-boot-starter', link: '/global/global-messaging-boot-starter' },
                { text: getFirstLineTitle('global/global-mybatis-plus-boot-starter/README.md') + '<br/>global-mybatis-plus-boot-starter', link: '/global/global-mybatis-plus-boot-starter' },
                { text: getFirstLineTitle('global/global-neo4j-boot-starter/README.md') + '<br/>global-neo4j-boot-starter', link: '/global/global-neo4j-boot-starter' },
                { text: getFirstLineTitle('global/global-notification-boot-starter/README.md') + '<br/>global-notification-boot-starter', link: '/global/global-notification-boot-starter' },
                { text: getFirstLineTitle('global/global-openapi-boot-starter/README.md') + '<br/>global-openapi-boot-starter', link: '/global/global-openapi-boot-starter' },
                { text: getFirstLineTitle('global/global-oss-boot-starter/README.md') + '<br/>global-oss-boot-starter', link: '/global/global-oss-boot-starter' },
                { text: getFirstLineTitle('global/global-project-info-boot-starter/README.md') + '<br/>global-project-info-boot-starter', link: '/global/global-project-info-boot-starter' },
                { text: getFirstLineTitle('global/global-ratelimit-boot-starter/README.md') + '<br/>global-ratelimit-boot-starter', link: '/global/global-ratelimit-boot-starter' },
                { text: getFirstLineTitle('global/global-redis-boot-starter/README.md') + '<br/>global-redis-boot-starter', link: '/global/global-redis-boot-starter' },
                { text: getFirstLineTitle('global/global-scheduler-boot-starter/README.md') + '<br/>global-scheduler-boot-starter', link: '/global/global-scheduler-boot-starter' },
                { text: getFirstLineTitle('global/global-security-boot-starter/README.md') + '<br/>global-security-boot-starter', link: '/global/global-security-boot-starter' },
                { text: getFirstLineTitle('global/global-session-boot-starter/README.md') + '<br/>global-session-boot-starter', link: '/global/global-session-boot-starter' },
                { text: getFirstLineTitle('global/global-swagger-boot-starter/README.md') + '<br/>global-swagger-boot-starter', link: '/global/global-swagger-boot-starter' },
                { text: getFirstLineTitle('global/global-test/README.md') + '<br/>global-test', link: '/global/global-test' },
                { text: getFirstLineTitle('global/global-tool/README.md') + '<br/>global-tool', link: '/global/global-tool' },
                { text: getFirstLineTitle('global/global-trans-boot-stater/README.md') + '<br/>global-trans-boot-stater', link: '/global/global-trans-boot-stater' },
                { text: getFirstLineTitle('global/global-validation-boot-starter/README.md') + '<br/>global-validation-boot-starter', link: '/global/global-validation-boot-starter' },
                { text: getFirstLineTitle('global/global-web-filter-boot-starter/README.md') + '<br/>global-web-filter-boot-starter', link: '/global/global-web-filter-boot-starter' },
                { text: getFirstLineTitle('global/global-web-mvc-boot-starter/README.md') + '<br/>global-web-mvc-boot-starter', link: '/global/global-web-mvc-boot-starter' },
                { text: getFirstLineTitle('global/global-wxjava-boot-starter/README.md') + '<br/>global-wxjava-boot-starter', link: '/global/global-wxjava-boot-starter' }
            ]
        },
        {
            text: '业务组件<br/>component',
            collapsed: false,
            items: [
                { text: '概览', link: '/component/' },
                { text: getFirstLineTitle('component/area/README.md') + '<br/>area', link: '/component/area' },
                { text: getFirstLineTitle('component/test/README.md') + '<br/>test', link: '/component/test' },
                { text: getFirstLineTitle('component/dict/README.md') + '<br/>dict', link: '/component/dict' },
                { text: getFirstLineTitle('component/func/README.md') + '<br/>func', link: '/component/func' },
                { text: getFirstLineTitle('component/user/README.md') + '<br/>user', link: '/component/user' },
                { text: getFirstLineTitle('component/component-common/README.md') + '<br/>component-common', link: '/component/component-common' },
                { text: getFirstLineTitle('component/component-autoconfigure-boot-starter/README.md') + '<br/>component-autoconfigure-boot-starter', link: '/component/component-autoconfigure-boot-starter' },
                { text: getFirstLineTitle('component/role/README.md') + '<br/>role', link: '/component/role' },
                { text: getFirstLineTitle('component/data-constraint/README.md') + '<br/>data-constraint', link: '/component/data-constraint' },
                { text: getFirstLineTitle('component/low-code/README.md') + '<br/>low-code', link: '/component/low-code' },
                { text: getFirstLineTitle('component/tools/README.md') + '<br/>tools', link: '/component/tools' },
                { text: getFirstLineTitle('component/data-query/README.md') + '<br/>data-query', link: '/component/data-query' },
                { text: getFirstLineTitle('component/tenant/README.md') + '<br/>tenant', link: '/component/tenant' },
                { text: getFirstLineTitle('component/dept/README.md') + '<br/>dept', link: '/component/dept' },
                { text: getFirstLineTitle('component/component-admin/README.md') + '<br/>component-admin', link: '/component/component-admin' },
                { text: getFirstLineTitle('component/op-log/README.md') + '<br/>op-log', link: '/component/op-log' },
                { text: getFirstLineTitle('component/tracking/README.md') + '<br/>tracking', link: '/component/tracking' },
                { text: getFirstLineTitle('component/message/README.md') + '<br/>message', link: '/component/message' },
                { text: getFirstLineTitle('component/oauth2authorization/README.md') + '<br/>oauth2authorization', link: '/component/oauth2authorization' },
                { text: getFirstLineTitle('component/open-platform/README.md') + '<br/>open-platform', link: '/component/open-platform' },
                { text: getFirstLineTitle('component/report/README.md') + '<br/>report', link: '/component/report' },
                { text: getFirstLineTitle('component/usage-count/README.md') + '<br/>usage-count', link: '/component/usage-count' },
                { text: getFirstLineTitle('component/feedback/README.md') + '<br/>feedback', link: '/component/feedback' },
                { text: getFirstLineTitle('component/crm/README.md') + '<br/>crm', link: '/component/crm' },
                { text: getFirstLineTitle('component/dream/README.md') + '<br/>dream', link: '/component/dream' },
                { text: getFirstLineTitle('component/config/README.md') + '<br/>config', link: '/component/config' },
                { text: getFirstLineTitle('component/data/README.md') + '<br/>data', link: '/component/data' },
                { text: getFirstLineTitle('component/scheduler/README.md') + '<br/>scheduler', link: '/component/scheduler' },
                { text: getFirstLineTitle('component/navigation/README.md') + '<br/>navigation', link: '/component/navigation' },
                { text: getFirstLineTitle('component/agi/README.md') + '<br/>agi', link: '/component/agi' },
                { text: getFirstLineTitle('component/cms/README.md') + '<br/>cms', link: '/component/cms' }
            ]
        }
    ]
}

/**
 * 前端组件侧边栏
 */
function getFrontendComponentsSidebar() {
    return [
        {
            text: '组件总览',
            collapsed: false,
            items: [
                { text: '组件总览', link: '/components/frontend/overview' },
            ]
        },
        {
            text: '全局组件<br/>web/global',
            collapsed: false,
            items: [
                {
                    text: '通用组件<br/>web/global/common',
                    collapsed: false,
                    items: [
                        { text: '概览', link: '/web/global/common/' },
                    ]
                },
                {
                    text: 'pc或平板端组件<br/>web/global/pc',
                    collapsed: false,
                    items: [
                        { text: '概览', link: '/web/global/pc/' },
                    ]
                },
                {
                    text: '移动端组件<br/>web/global/mobile',
                    collapsed: false,
                    items: [
                        { text: '概览', link: '/web/global/mobile' },
                    ]
                }
            ]
        },
        {
            text: '业务组件<br/>web/component',
            collapsed: false,
            items: [
                { text: '概览', link: '/web/component/' },
            ]
        }
    ]
}

/**
 * 编辑链接处理，因文档中的有些文档在项目中写的 readme.md 文件
 * 将引用的文件，转换到实现的项目路径
 * @param {string} filePath 文件路径
 * @returns {string} 标题
 */
function editLinkPattern({ filePath }) {
    // 配置，针对以这些开头的文件路径进行处理
    let config = {
        'global/global': {
            handle: (filePath)=> filePath.replace('.md', '/README.md'),
        },
        'global/index': {
            handle: (filePath)=> filePath.replace('/index.md', '/README.md'),
        },
        'component/index': {
            handle: (filePath)=> filePath.replace('/index.md', '/README.md'),
        },
        'component/': {
            handle: (filePath)=> filePath.replace('.md', '/README.md'),
        },
        'web/global/index': {
            handle: (filePath)=> filePath.replace('/index.md', '/README.md'),
        },
        'web/global/common/index': {
            handle: (filePath)=> filePath.replace('/index.md', '/README.md'),
        },
        'web/global/mobile/index': {
            handle: (filePath)=> filePath.replace('/index.md', '/README.md'),
        },
        'web/global/pc/index': {
            handle: (filePath)=> filePath.replace('/index.md', '/README.md'),
        },
        'web/component/index': {
            handle: (filePath)=> filePath.replace('/index.md', '/README.md'),
        },
    }
    // 定义临时文件路径变量，后面针对配置中匹配的文件路径进行处理
    let tempFilePath = filePath
    // 定义一个子路径，配置中匹配到时，该路径应该为空
    let subPath = ''
    // 是否匹配到配置
    let isMatchConfig = false
    for (let configKey in config) {
        if (filePath.startsWith(configKey)) {
            tempFilePath = config[configKey].handle(filePath)
            subPath = ''
            isMatchConfig = true
            break
        }
    }
    // 没有匹配到配置，则使用原来的文件路径
    if (!isMatchConfig) {
        tempFilePath = filePath
        subPath = 'doc/vitepress/docs/'
    }
    let pattern = `https://github.com/feihua666/particle/edit/master/${subPath}:path`
    // 该代码参考了 https://github.com/vuejs/vitepress/blob/main/src/client/theme-default/composables/edit-link.ts
    return pattern.replace(/:path/g, tempFilePath);
}
