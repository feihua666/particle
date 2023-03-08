/**
 * 项目文档增强脚本
 * 主要是在 vuepress 2.0 中没有找到支持额外添加文档的功能
 * 特写些脚本进行增强，导出需要的目录结构
 */
const fs = require('fs')
const path = require('path')
const readme = 'README.md'
// 得到路径 /Users/yw/fh/git-source/particle
const projectPath = path.resolve(__dirname, '../../../../')
// 得到路径 /Users/yw/fh/git-source/particle/doc/vuepress/docs
const docsPath = projectPath + '/doc/vuepress/docs'
// 相对文档的路径
const docRootPath = docsPath + '/project'
const ignoreDirPattern = [
    // /Users/yw/fh/git-source/particle/doc/vuepress
    'vuepress',
    'src',
    'target',
    '.idea',
    '.mvn',
    'logs',

]

/**
 * 复制readme文件
 * @param sourceParentDirPath 源文件父目录，绝对路径
 * @param destParentDirPath 目标文件父目录，绝对路径
 * @param fileNamePattern 文件名，暂只支持文件名如：README.md
 * @param level 递归深度，默认0不递归，1递归一次
 * @returns {*[]} 路径信息，可直接用于 vuepress config.js导航或侧边栏中
 */
const copyFile = (sourceParentDirPath, destParentDirPath, fileNamePattern,level = 0) => {
    let r = []
    let files = fs.readdirSync(sourceParentDirPath)
    files.forEach(file=>{
        let child = sourceParentDirPath + '/' + file
        let stat = fs.statSync(child)
        if (stat.isFile()) {
            if (file == fileNamePattern) {
                let destFile = destParentDirPath + "/" + fileNamePattern
                mkdir(destParentDirPath)
                fs.copyFileSync(child,destFile)
                r.push(destFile.substr(docsPath.length))
            }
        }else {
            if (level > 0 && !ignoreDirPattern.some(item => item == file)) {
                let children = copyFile(child,destParentDirPath + "/" + file,fileNamePattern,level-1)
                r = r.concat(children)
            }
        }
    })
    return r
}
/**
 * 创建目录
 * @param dirname
 * @returns {boolean}
 */
const mkdir = (dirname) => {
    if (fs.existsSync(dirname)) {
        return true
    } else {
        if (mkdir(path.dirname(dirname))) {
            fs.mkdirSync(dirname)
            return true
        }
    }
}
// 项目整体版本说明文件
const changelog = copyFile(projectPath,docRootPath,'changelog.md')
// 项目整体readme文件
const projectReadme = copyFile(projectPath,docRootPath,readme)
// 全局后端框架模块
const backendGlobal = copyFile(projectPath+ '/global',docRootPath+ '/global',readme,1)
// 基础后端框架模块
const backendCommon = copyFile(projectPath+ '/common',docRootPath+ '/common',readme,1)
// 后端业务组件
const backendComponent = copyFile(projectPath+ '/component',docRootPath+ '/component',readme,3)
// 前端全局基础
const frontendWebCommon = copyFile(projectPath+ '/web/common',docRootPath+ '/web/common',readme)
// 前端pc基础
const frontendWebCommonPc = copyFile(projectPath+ '/web/common/pc',docRootPath+ '/web/common/pc',readme,3)
// 前端uniapp基础
const frontendWebCommonUniapp = copyFile(projectPath+ '/web/common/uniapp',docRootPath+ '/web/common/uniapp',readme,3)
// 前端pc业务
const frontendWebPc = copyFile(projectPath+ '/web/component/pc',docRootPath+ '/web/component/pc',readme,3)
// 前端uniapp移动业务
const frontendWebUniapp = copyFile(projectPath+ '/web/component/uniapp',docRootPath+ '/web/component/uniapp',readme,3)


// 工具相关
const tools = copyFile(projectPath+ '/tools',docRootPath+ '/tools',readme)

module.exports = {
    changelog: changelog[0],
    projectReadme: projectReadme[0],

    backendGlobal: {
        text: '全局组件',
        children: backendGlobal
    },
    backendCommon: {
        text: '领域基础组件',
        children: backendCommon
    },

    backendComponent: {
        text: '后端业务基础组件',
        children: backendComponent
    },
    frontendWebCommon: {
        text: '前端全局基础',
        children: frontendWebCommon
    },
    frontendWebCommonPc: {
        text: '前端pc基础',
        children: frontendWebCommonPc
    },
    frontendWebCommonUniapp: {
        text: '前端uniapp基础',
        children: frontendWebCommonUniapp
    },
    frontendWebPc: {
        text: 'pc 业务',
        children: frontendWebPc
    },
    frontendWebUniapp: {
        text: 'uniapp移动业务',
        children: frontendWebUniapp
    },
    tools: tools[0],

}