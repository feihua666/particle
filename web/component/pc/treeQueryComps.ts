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
                label: '包括父节点',
                labelTips: '仅选择父级有效'
            },
            compProps: {
            }
        }
    },
    {
        field: {
            name: 'isIncludeAllChildren',
            value: true
        },
        element: {
            comp: 'el-checkbox',
            formItemProps: {
                label: '包括孙节点',
                labelTips: '仅选择父级有效'
            },
            compProps: {
            }
        }
    },
]