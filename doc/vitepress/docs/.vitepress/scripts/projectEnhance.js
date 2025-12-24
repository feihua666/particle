/**
 * 项目文档增强脚本
 */
import fs from 'fs'
import path from 'path'

const readme = 'README.md'
// 得到路径 /Users/yw/fh/git-source/particle
const projectPath = path.resolve(__dirname, '../../../../../')
console.log(projectPath)
// 得到路径 /Users/yw/fh/git-source/particle/doc/vuepress/docs
const docsPath = projectPath + '/doc/vitepress/docs'

/**
 * 获取文件第一行标题
 * @param relativePath 相对于项目根目录的路径
 * @return {*|string}
 */
function getFirstLineTitle(relativePath) {
    // 构建相对于项目根目录的完整路径
    const fullPath = path.resolve(projectPath, relativePath);
    try {
        const content = fs.readFileSync(fullPath, 'utf8');
        const lines = content.split('\n');
        // 获取第一行，通常是标题行
        const firstLine = lines[0].trim();
        // 移除markdown标题符号 #
        return firstLine.replace(/^#\s*/, '');
    } catch (error) {
        console.error(`Error reading file ${fullPath}:`, error.message);
        return 'Unknown Component';
    }
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
module.exports = {
    getFirstLineTitle
};
