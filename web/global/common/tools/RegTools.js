/**
 * 获取匹配的结果数组
 * @param regStr 正则表达试字符串
 * @param content 匹配的内容
 * @returns [*]
 */
export function match(regStr,content,m) {
    let reg = new RegExp(regStr,m ? m: 'g')
    return content.match(reg)
}
