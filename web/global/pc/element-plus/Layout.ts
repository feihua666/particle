/**
 * 布局,主要用于 form 表单项布局封装
 * 基于 el-row
 */
/**
 * 布局属性，封装组件时用
 */
export const layoutProps = {
    // 布局
    // number 表示每行几列
    // array 表示 每一行几列
    layout:{
        type: [Number,Array],
        default: 3
    }
}
/**
 * props.layout为数字时的计算
 * @param flatLength
 * @param layout props.layout为数字时
 * @returns {*[]}
 */
const  layoutComputeByNumber = (flatLength,layout) => {
    let tempLayout = []
    let shouldTotal = flatLength
    let rowNum = Math.floor((shouldTotal + layout - 1)/layout)
    let span = Math.floor((24)/layout)
    let total = 0
    for (let i = 0; i < rowNum; i++) {
        let row = []
        for (let j = 0; j < layout; j++) {
            if(total < shouldTotal){
                row.push(span)
            }
            total++
        }
        tempLayout.push(row)
    }
    return tempLayout
}
/**
 *  返回一个数组
 * @param layout 支持数字和数组
 * @param flatLength 扁平化原始长度
 * @return {*}  返回一个二维数组[[8,8,8],[12,8],[24]],表示第一行1个第二行2个第三行1个,里面的数组表示span数
 */
export const layoutCompute = ({props}) => {
    return (flatLength) => {
        let tempLayout = []
        let shouldTotal = flatLength
        if(typeof props.layout == 'number'){
            return layoutComputeByNumber(flatLength,props.layout)
        }
        // 数组
        tempLayout = tempLayout.concat(props.layout)
        // 如果总数不够，在后面补充
        let total = 0
        tempLayout.forEach((item,index) => {
            if (typeof item == 'number') {
                let span = Math.floor((24)/item)
                let r = []
                for (let i = 0; i < item; i++) {
                    r.push(span)
                }
                total += r.length
                tempLayout.splice(index,1,r)
            }else {
                total += item.length
            }
        })
        // 实际数大于应该数时，不管这种情况，属于传参错误
        if(total < shouldTotal){
            tempLayout = tempLayout.concat(layoutComputeByNumber(shouldTotal - total,3))
        }

        return tempLayout
    }
}
/**
 * 计算索引
 * @param rowIndex 行索引
 * @param colIndex 列索引
 * @param layoutComputed 通过 layoutCompute 计算的结果
 * @returns {*}
 */
export const layoutIndex = (rowIndex,colIndex,layoutComputed) => {
    let r = 0;
    for(let i = 0;i < rowIndex; i++){
        r += layoutComputed[i].length
    }
    return r + colIndex
}
