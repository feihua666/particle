export const userLoginRecordColumns = [
    {
        prop: 'userNickname',
        label: '用户昵称',
    },
    {
        prop: 'userIdentifier',
        label: '登录标识',
    },
    {
        prop: 'loginAt',
        label: '登录时间',
        showOverflowTooltip: true
    },
    {
        prop: 'loginIp',
        label: '登录ip'
    },

    {
        prop: 'deviceId',
        label: '设备id',
    },
    {
        prop: 'deviceName',
        label: '设备名称',
    },
    {
        prop: 'operatingSystem',
        label: '操作系统及版本'
    },
    {
        prop: 'isSuccess',
        label: '登录结果',
        formatter: (row, column, cellValue, index) => {
            let r = cellValue ? '登录成功' : '登录失败'
            if(!cellValue && row.failedReason){
                r = r + `(${row.failedReason})`
            }
            return r
        },
        showOverflowTooltip: true
    },
    {
        prop: 'traceId',
        label: '追踪id'
    },
    {
        prop: 'apiCount',
        label: 'api数量'
    },
    {
        prop: 'lastActiveAt',
        label: '最后活跃时间',
        showOverflowTooltip: true
    },
    {
        prop: 'logoutAt',
        label: '退出登录时间',
        showOverflowTooltip: true
    },
]