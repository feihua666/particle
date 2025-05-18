/**
 * 由于有一些依赖是在项目外，导致找不到安装的包，这里先引入一下
 */
// 下面的不能去掉，否则前端报错，找不到 ace 变量
import ace from "ace-builds"
// 添加一个变量防止自动优化把上面的语句给优化掉
let aceReference = ace
