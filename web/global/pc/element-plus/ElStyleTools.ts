import {isArray} from '../../common/tools/ArrayTools'

// 定义索引
const d = [
    {
        pattern:['查询','搜索','获取','预览'],
        icon: 'Search',
        type:'primary',
    },
    {
        pattern:['重置'],
        icon: 'Refresh',
        type: 'default'
    },
    {
        pattern:'删除',
        icon: 'Delete',
        type:'danger',
    },
    {
        pattern:['添加','新增'],
        icon: 'Plus',
        type:'primary',
    },
    {
        pattern:['修改','编辑','更新','保存'],
        icon: 'Edit',
        type:'primary',
    },
    {
        pattern:'刷新',
        icon: 'Refresh'
    },
    {
        pattern:['确认'],
        icon: 'Check',
        type:'primary',
    },
    {
        pattern:'详情',
        icon: 'View'
    }
]

/**
 * 智能获取按钮样式
 * @param pattern
 * @return {*}
 */
export function aiButtonStyle(pattern) {
    let r = null
    if(pattern){
        for (let i = 0; i < d.length; i++) {
            let item = d[i]
            if(isArray(item.pattern)){
                for (let j = 0; j < item.pattern.length; j++) {
                    let jitem = item.pattern[j]
                    if(jitem.indexOf(pattern) >= 0 || pattern.indexOf(jitem) >= 0){
                        r = item
                        break
                    }
                }
            }else {
                if(item.pattern.indexOf(pattern) >= 0 || pattern.indexOf(item.pattern) >= 0){
                    r = item
                    break
                }
            }
        }
    }

    if(!r){
        r = {
            pattern:'空图标',
            // elementPlusCssOverride.css 中定义
            icon: ''
        }
    }
    return r
}