/**
 * 是否为数组
 * @param array 数组
 */
export function isArray(array : any):boolean {
    if (typeof Array.isArray === "function") {
        return Array.isArray(array);
    }else{
        return Object.prototype.toString.call(array) === "[object Array]";
    }
}

/**
 * 判断数组中某个元素是否存在
 * @param item
 * @param array
 * @returns {boolean|*}
 */
export function exist(item: any,array: Array<any>):boolean {
    if (!array || !item) {
        return false
    }
    return array.indexOf(item) >= 0
}
/**
 * 数据转为树结构
 * @param list
 * @param parentId 从哪个parentId开始，不传入包括所有的
 * @return {*}
 */
export function listToTree(list:Array<any>, parentId?:number|string,idProp = 'id',parentIdProp = 'parentId'):Array<any> {
    let cacheObj = {}
    list.forEach(item => {
        cacheObj[item[idProp]] = item
    })
    return list.filter(item => {
        if (item[parentIdProp] != parentId && cacheObj[item[parentIdProp]]) {
            if(!cacheObj[item[parentIdProp]].children){
                cacheObj[item[parentIdProp]].children = []
            }
            cacheObj[item[parentIdProp]].children.push(item)
            return false
        }
        return true
    })
}

/**
 * 扁平化树
 * @param tree
 * @return {Array}
 */
export function treeToList(tree:Array<any>):Array<any> {
    let r = []
    let flat = function (tree) {
        tree.forEach(item => {
            r.push(item)
            item.children && item.children.length > 0 ? flat(item.children) : ''
        })
    }
    flat([].concat(tree))
    return r
}

/**
 * 获取树的叶子节点
 * @param tree
 */
export function getTreeLeafItem(tree:Array<any>):Array<any>{
    let r = []
    let flat = function (tree) {
        tree.forEach(item => {
            if(!item.children || item.children.length == 0 || item.isLeaf){
                r.push(item)
            }else {
                if(item.children && item.children.length > 0){
                    flat(item.children)
                }
            }
        })
    }
    flat([].concat(tree))
    return r
}

/**
 * 去重，注意如果数组中有 NaN ，则不会对其去重
 * @param arr
 */
export function removeDuplicate(arr: Array<any>):Array<any> {
    return arr.filter((item, index) => {
        return arr.indexOf(item) === index
    })
}

/**
 * 两个数组是否相同，兼容顺序不同
 * @param a
 * @param b
 */
export function isEqual(a: Array<any>,b: Array<any>): boolean{
    return a.length==0 && b.length==0 || a.length === b.length && a.some(t => b.includes(t))
}

/**
 * 将 index位置的元素前移
 * @param array
 * @param index
 */
export function frontMove(array: Array<any>,index){
    // 如果数组中只有一个元素不处理
    if(!array || array.length == 1){
        return
    }
    let temp = array[index]
    array.splice(index, 1)
    array.splice(index - 1, 0, temp)

}
/**
 * 将 index位置的元素前移
 * @param array
 * @param index
 */
export function backMove(array: Array<any>,index){
    // 如果数组中只有一个元素不处理
    if(!array || array.length == 1){
        return
    }
    let temp = array[index]
    array.splice(index, 1)
    array.splice(index + 1, 0, temp)
}

/**
 * 移除 index 位置的元素
 * @param array
 * @param index
 */
export function remove(array: Array<any>,index){
    if(!array){
        return
    }
    array.splice(index, 1)
}

/**
 * 清空数组
 * @param array
 */
export function removeAll(array: Array<any>){
    if (array.length == 0) {
        return
    }
    array.splice(0,array.length)
}
/**
 * 替换 index 位置的元素
 * @param array
 * @param index
 */
export function replace(array: Array<any>,index,item){
    if(!array){
        return
    }
    array.splice(index, 1,item)
}
/**
 * 从 array 中移除在 arrayRemoved 中已存在的元素
 * @param array
 * @param index
 */
export function removeItems(array: Array<any>,arrayRemoved: Array<any>){
    if(!arrayRemoved){
        return array
    }
    if(!array){
        return array
    }
    let result = array.filter(item => !arrayRemoved.includes(item));
    return result
}

/**
 * 反转数组
 * @param array
 */
export function reverse(array: Array<any>,createNew = true) {
    if (!array) {
        return array
    }
    if (createNew) {
        // Array.from(arr).reverse()
        return [...array].reverse()
    }
    return array.reverse()
}

/**
 * 获取数组中的最小值
 * @param array
 */
export function min(array: Array<any>):number {
    return Math.min(...array)
}
/**
 * 获取数组中的最大值
 * @param array
 */
export function max(array: Array<any>):number {
    return Math.max(...array)
}