/**
 * 这是一个vuepress 2.0 的插件示例
 * 注意插件命名规则，替换 xxx 就可以
 */
const { path } = require('@vuepress/utils')
const changelogPlugin = (options) => {
    return (app) => {
        return {
            name: 'vuepress-plugin-xxx',
            extendsMarkdownOptions: (markdownOptions, app) => {
            },
            extendsMarkdown: (md, app) => {
            },
            extendsPageOptions: (pageOptions, app) => {
            },
            extendsPage: (page) => {
            },
            // 初始化之后，所有的页面已经加载完毕
            async onInitialized(app) {
            }
        }
    }
}
module.exports = changelogPlugin