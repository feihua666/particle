// 如果使用element plus已经依赖安装，无需再安装
import dayjs from 'dayjs'

const dateReg = new RegExp("-","gm")
/**
 * 获取日期对象
 * @param str 如：2021-10-27 16:25:19 格式
 */
export function getDateObj(str: string): Date{
    return new Date(str.replace(dateReg,"/"))
}

/**
 * 获取当前毫秒时间戳
 */
export function getCurrentTimestamps():number{
    return new Date().getTime()
}

/**
 * 获取当前的日期时间
 */
export function getCurrentDataTime() {
    return dayjs().format('YYYY-MM-DD HH:mm:ss')
}
