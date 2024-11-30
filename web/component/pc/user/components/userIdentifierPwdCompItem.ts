export const userIdentifierPwdColumns = [
    {
        prop: 'userNickname',
        label: '用户昵称',
        showOverflowTooltip: true
    },
    {
        prop: 'userIdentifier',
        label: '登录标识',
        showOverflowTooltip: true
    },
    {
        prop: 'pwdEncryptFlag',
        label: '加密方式',
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
        prop: 'isNeedUpdate',
        label: '提示修改密码',
        width: 110,
        formatter: (row, column, cellValue, index) => {
            let r = cellValue ? '提示' : '不提示'
            if(cellValue && row.needUpdateMessage){
                r = r + `(${row.needUpdateMessage})`
            }
            return r
        },
        showOverflowTooltip: true
    },
    {
        prop: 'groupFlag',
        label: '分组标识',
        showOverflowTooltip: true
    },
    {
        prop: 'pwdModifiedAt',
        label: '密码修改时间',
        showOverflowTooltip: true
    },
    {
        prop: 'complexity',
        label: '密码复杂度'
    },
]

export const userIdentifierPwdForLoginColumns = [
    {
        prop: 'userNickname',
        label: '用户昵称',
    },
    {
        prop: 'userIdentifier',
        label: '登录标识',
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
        prop: 'pwdModifiedAt',
        label: '密码修改时间'
    },
    {
        prop: 'complexity',
        label: '密码复杂度'
    },
]
