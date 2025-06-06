import {isArray} from '../../common/tools/ArrayTools'

// 定义索引
const d = [
    {
        pattern:['查询','搜索','获取'],
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
        pattern:'清空',
        icon: 'DeleteFilled',
        type:'danger',
    },

    {
        pattern:['添加','新增'],
        icon: 'Plus',
        type:'primary',
    },
    {
        pattern:['修改','编辑','更新','保存','绑定'],
        icon: 'Edit',
        type:'primary',
    },
    {
        pattern:'刷新',
        icon: 'Refresh'
    },
    {
        pattern:['确认','提交'],
        icon: 'Check',
        type:'primary',
    },
    {
        pattern:['预览','查看','详情'],
        icon: 'View',
        type:'primary',
    },

    {
        pattern:['分配','配置','授权'],
        icon: 'Tools',
        type:'primary'
    },
    {
        pattern:['复制'],
        icon: 'DocumentCopy',
        type:'primary'
    },
    {
        pattern:['审计','审核','审批'],
        icon: 'Comment',
        type:'primary'
    },
    {
        pattern:['下载'],
        icon: 'Download',
        type:'primary'
    },
    {
        pattern:['上传'],
        icon: 'Upload',
        type:'primary'
    },
    {
        pattern:'地图',
        icon: 'MapLocation',
        type:'primary'
    },
    {
        pattern:['装载','加载'],
        icon: 'Cpu',
        type:'primary'
    },
    {
        pattern:['测试'],
        icon: 'SetUp',
        type:'primary'
    },
    {
        pattern:['设计'],
        icon: 'MagicStick',
        type:'primary'
    },

    {
        pattern:['切换'],
        icon: 'Switch',
        type:'primary'
    },
    {
        pattern:['处理'],
        icon: 'Operation',
        type:'primary'
    },
    {
        pattern:['回复'],
        icon: 'ChatLineRound',
        type:'primary'
    },
    {
        pattern:['联系方式'],
        icon: 'Tickets',
        type:'primary'
    },
    {
        pattern:['统计','图表'],
        icon: 'Histogram',
        type:'primary'
    },
    {
        pattern:['停止','暂停','挂起'],
        icon: 'VideoPause',
        type:'primary'
    },
    {
        pattern:['启动','恢复','执行'],
        icon: 'VideoPlay',
        type:'primary'
    },
    {
        pattern:['计算'],
        icon: 'Cpu',
        type:'primary'
    },
    {
        pattern:['管理','部署'],
        icon: 'Setting',
        type:'primary'
    },
    {
        pattern:['嵌入','向量化'],
        icon: 'Compass',
        type:'primary'
    },
    {
        pattern:['对话','聊天'],
        icon: 'ChatLineRound',
        type:'primary'
    },
    {
        pattern:['上移','下移','移动'],
        icon: 'Rank',
        type:'primary'
    },
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
