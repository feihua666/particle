import {list as tenantListApi} from "../api/admin/tenantAdminApi";

export const selectTenantProps = {
    // 加载数据初始化参数,路由传参
    tenantId: {
        type: String
    },
}
export const useSelectTenantCompItem = ({props = {},fieldName = 'tenantId',required = false, label = '租户',tips = ''})=>{
    return     {
        field: {
            name: fieldName,
            // selectTenantProps 中的tenantId保持一致
            value: props.tenantId
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: label,
                required: required,
                tips: tips
            },
            compProps: ()=>{
                let paramsExist = !!(props.tenantId)
                return {
                    disabled: paramsExist,
                    dataMethod: tenantListApi
                }
            }
        }
    }
}

export const tenantFuncApplicationColumns = [
    {
        prop: 'name',
        label: '名称',
        showOverflowTooltip: true
    },
    {
        prop: 'isGroup',
        label: '应用组/应用',
        formatter: (row, column, cellValue, index) => {
            let r = cellValue ? '应用组' : '应用'
            return r
        }
    },
    {
        prop: 'parentFuncApplicationName',
        label: '父级',
    },
    {
        prop: 'applicationTheme',
        label: '应用主题',
    },
    {
        prop: 'applicationDefaultRoute',
        label: '默认路由',
        showOverflowTooltip: true
    },
    {
        prop: 'applicationLogoUrl',
        label: '应用logo',
        columnView: 'image'
    },
    {
        prop: 'applicationIconUrl',
        label: '应用icon',
        columnView: 'image'
    },
    {
        prop: 'configJson',
        label: '额外配置json',
        showOverflowTooltip: true
    },
    {
        prop: 'tenantName',
        label: '租户名称',
        showOverflowTooltip: true
    },
]
