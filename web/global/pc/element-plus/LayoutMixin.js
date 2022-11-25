const layoutMixin = {
    props: {
        // 布局
        // number 表示每行几列
        // array 表示 每一行几列
        layout:{
            type: [Number,Array],
            default: 3
        }
    },
    computed:{
        $_layoutComputedLayout() {
            if(this.options){
                return this.$_layoutComputed(this.options.length)
            }
            return []
        }
    },
    methods: {
        /**
         *  返回一个数组
         * @param layout 支持数字和数组
         * @param flatLength 扁平化原始长度
         * @return {*}  返回一个二维数组[[8,8,8],[12,8],[24]],表示第一行1个第二行2个第三行1个,里面的数组表示span数
         */
        $_layoutComputed: function (flatLength) {
            let tempLayout = []
            let shouldTotal = flatLength
            if(typeof this.layout == 'number'){
                return this.$_layoutComputedByNumber(flatLength,this.layout)
            }
            // 数组
            tempLayout = tempLayout.concat(this.layout)
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
                tempLayout = tempLayout.concat(this.$_layoutComputedByNumber(shouldTotal - total,3))
            }

            return tempLayout
        },
        $_layoutComputedByNumber(flatLength,layout) {
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
        },
        $_layoutIndex(rowIndex,colIndex,layoutComputed){
            let r = 0;
            for(let i = 0;i < rowIndex; i++){
                r += layoutComputed[i].length
            }
            return r + colIndex
        },
    }
}
export default layoutMixin