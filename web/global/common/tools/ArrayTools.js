/**
 * 是否为数组
 * @param array 数组
 */
export function isArray(array) {
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
export function exist(item,array) {
    if (!array || !item) {
        return false
    }
    return array.includes(item)
}
/**
 * 数据转为树结构
 * @param list
 * @param parentId 从哪个parentId开始，不传入包括所有的
 * @return {*}
 */
export function listToTree(list, parentId) {
    let cacheObj = {}
    list.forEach(item => {
        cacheObj[item.id] = item
    })
    return list.filter(item => {
        if (item.parentId != parentId && cacheObj[item.parentId]) {
            if(!cacheObj[item.parentId].children){
                cacheObj[item.parentId].children = []
            }
            cacheObj[item.parentId].children.push(item)
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
export function treeToList(tree) {
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