/**
 * 树查询通用表单
 */
export const treeQueryComps = [
    {
        field: {
            name: 'isIncludeParent',
            value: true
        },
        element: {
            comp: 'el-checkbox',
            formItemProps: {
                label: '包括父节点'
            },
            compProps: {
            }
        }
    },
    {
        field: {
            name: 'isIncludeAllChildren',
            value: false
        },
        element: {
            comp: 'el-checkbox',
            formItemProps: {
                label: '包括孙节点'
            },
            compProps: {
            }
        }
    },
]