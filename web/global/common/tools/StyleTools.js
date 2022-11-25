import {isArray} from './ArrayTools'

// 定义索引
const d = [
    {
        pattern:['post'],
        text: '添加'
    },
    {
        pattern:['get'],
        text: '查询'
    },
    {
        pattern:['put'],
        text: '修改'
    },
    {
        pattern:['delete'],
        text: '删除'
    },
]

/**
 * 智能获取按钮样式
 * @param pattern 如：post,get,put,delete
 * @return {*}
 */
export function aiStyle(pattern) {

    let r = {text: '操作'}
    if(pattern){
        for (let i = 0; i < d.length; i++) {
            let item = d[i]
            if(isArray(item.pattern)){
                for (let j = 0; j < item.pattern.length; j++) {
                    let jitem = item.pattern[j]
                    if(jitem.indexOf(pattern) >= 0 || pattern.indexOf(jitem) >= 0){
                        r = item
                        break
                    }
                }
            }else {
                if(item.pattern.indexOf(pattern) >= 0 || pattern.indexOf(item.pattern) >= 0){
                    r = item
                    break
                }
            }
        }
    }
    return r
}