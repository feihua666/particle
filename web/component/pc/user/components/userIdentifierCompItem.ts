export const userIdentifierColumns = [
    {
        prop: 'userNickname',
        label: '用户昵称',
        showOverflowTooltip: true
    },
    {
        prop: 'identifier',
        label: '登录标识',
        showOverflowTooltip: true
    },
    {
        prop: 'identityTypeDictName',
        label: '账号类型',
        showOverflowTooltip: true
    },
    {
        prop: 'unionId',
        label: 'unionId',
        showOverflowTooltip: true
    },

    {
        prop: 'isLock',
        label: '是否锁定',
        width: 80,
        formatter: (row, column, cellValue, index) => {
            let r = cellValue ? '锁定' : '正常'
            if(cellValue && row.lockReason){
                r = r + `(${row.lockReason})`
            }
            return r
        },
        showOverflowTooltip: true
    },
    {
        prop: 'isExpired',
        label: '是否过期',
        width: 80,
        formatter: (row, column, cellValue, index) => {
            let r = cellValue ? '已过期' : '正常'
            if(cellValue && row.expiredReason){
                r = r + `(${row.expiredReason})`
            }
            return r
        }
    },
    {
        prop: 'expireAt',
        label: '过期时间',
        width: 80,
    },

    {
        prop: 'groupFlag',
        label: '分组标识',
        showOverflowTooltip: true
    },
    {
        prop: 'lastLoginAt',
        label: '最后登录时间',
        showOverflowTooltip: true
    },
    {
        prop: 'lastLoginIp',
        label: '最后登录ip',
        showOverflowTooltip: true
    },
]
