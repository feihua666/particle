/**
 * 获取匹配的结果数组
 * @param regStr 正则表达试字符串
 * @param content 匹配的内容
 * @returns [*]
 */
export function match(regStr: string,content: string,flags?: string): RegExpMatchArray | null {
    let reg = new RegExp(regStr,flags ? flags: 'g')
    return content.match(reg)
}
