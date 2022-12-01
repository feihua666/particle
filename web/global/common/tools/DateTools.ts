const dateReg = new RegExp("-","gm")
/**
 * 获取日期对象
 * @param str 如：2021-10-27 16:25:19 格式
 */
export function getDateObj(str: string): Date{
    return new Date(str.replace(dateReg,"/"))
}