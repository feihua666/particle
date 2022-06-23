const { defaultTheme } = require('@vuepress/theme-default')
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
        ['link', { rel: 'shortcut icon', type: "image/png", href: `./favicon.png` }]
    ],
    // 主题配置
    theme: defaultTheme({
        // Logo 图片将会显示在导航栏的左端。设置为 null 可以禁用 Logo 。
        logo: '/images/logo/logo.png',
        // 它将被用作 仓库链接 的链接。仓库链接 将会显示为导航栏的最后一个元素。
        // 如果你按照 `organization/repository` 的格式设置它
        // 我们会将它作为一个 GitHub 仓库
        repo: 'https://gitlab.com/foo/bar',
    }),
}