const { defaultTheme } = require('@vuepress/theme-default')
const projectEnhance = require('./projectEnhance')
module.exports = {
    // 部署站点的基础路径,默认值： /
    base: '/',
    // 它将会在最终渲染出的 HTML 中作为 <html> 标签的 lang 属性。站点的语言,默认值： en-US
    lang: 'zh-CN',
    // 它将会作为所有页面标题的后缀，并且在默认主题的导航栏中显示。默认值： ''
    title: 'Particle 技术文档',
    // 它将会在最终渲染出的 HTML 中作为 <meta name="description" /> 标签的 content 属性。它会被每个页面的 Frontmatter 中的 description 字段覆盖。
    description: 'particle 意为粒子，粒子内高内聚、粒子间低耦合',
    port: 9090,
    head: [
        // favicon.ico
        ['link', { rel: 'icon', type: "image/png", href: `/favicon.png` }]
    ],
    // vuepress 2.0 插件比 1.x 弱了太多，目前没找到添加额外 md 文件的地方，所以只能先把文件通过脚本拷贝过来
    plugins:[
    ],
    // 主题配置
    theme: defaultTheme({

        // Logo 图片将会显示在导航栏的左端。设置为 null 可以禁用 Logo 。
        logo: '/images/logo/logo.png',
        // 它将被用作 仓库链接 的链接。仓库链接 将会显示为导航栏的最后一个元素。
        // 如果你按照 `organization/repository` 的格式设置它
        // 我们会将它作为一个 GitHub 仓库
        repo: 'feihua666/particle',
        // 是否启用 编辑此页 链接。默认值： true。你可以通过页面的 editLink frontmatter 来覆盖这个全局配置。
        editLink: false,
        // 导航栏配置。设置为 false 可以禁用导航栏。
        navbar: [
            // NavbarItem
            '/guide/',
            // NavbarGroup
            {
                text: '项目',
                children: [
                    projectEnhance.changelog,
                    projectEnhance.projectReadme,
                ],
            },
            {
                text: '后端框架',
                children: [
                    projectEnhance.backendGlobal,
                    projectEnhance.backendCommon,
                ],
            },
            {
                text: '后端业务',
                children: [
                    projectEnhance.backendComponent,
                ],
            },
            {
                text: '前端框架',
                children: [
                    projectEnhance.frontendWebCommon,
                    projectEnhance.frontendWebCommonPc,
                    projectEnhance.frontendWebCommonUniapp,
                ],
            },
            {
                text: '前端业务',
                children: [
                    projectEnhance.frontendWebPc,
                    projectEnhance.frontendWebUniapp,
                ],
            },
            projectEnhance.tools,
        ],
        // 侧边栏对象
        // 不同子路径下的页面会使用不同的侧边栏
        sidebar: {
            '/guide/': [
                {
                    text: '指南',
                    children: [
                        '/guide/profile',
                        '/guide/quick/getting-started.md',
                        '/guide/quick/runtime-env.md',
                    ],
                },
            ],
            '/project/': [
                {
                    text: '项目',
                    children: [
                        projectEnhance.changelog,
                        projectEnhance.projectReadme,
                    ],
                },
            ],
            '/project/global/': [
                projectEnhance.backendGlobal,
            ],
            '/project/common/': [
                projectEnhance.backendCommon,
            ],
            '/project/component/': [
                projectEnhance.backendComponent,
            ],
            '/project/web/common/': [
                projectEnhance.frontendWebCommon,
            ],
            '/project/tools/': [
                projectEnhance.tools,
            ],
        },
    }),
}