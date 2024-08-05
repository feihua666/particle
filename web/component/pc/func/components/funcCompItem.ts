import {list as funcListApi, page as funcPageApi} from "../api/admin/funcAdminApi";
import {list as funcGroupListApi} from "../api/admin/funcGroupAdminApi";

export const remoteSelectFuncProps = {
    // 加载数据初始化参数,路由传参
    funcId: {
        type: String
    },
    // 加载数据初始化参数,路由传参
    funcName: String
}
/**
 * 远程搜索功能菜单表单配置项
 * 属性中必须有 funcId 和 funcName 两个属性
 * @param props
 */
export const useRemoteSelectFuncCompItem = ({props,required})=>{
  return   {
        field: {
            name: 'funcId',
            value: props.funcId
        },
        element: {
            comp: 'PtSelect',
                formItemProps: {
                label: '功能菜单',
                required: required
            },
            compProps: ()=> {
                let paramsExist = !!(props.funcId && props.funcName)
                let r = {
                    placeholder: '输入功能菜单名称搜索',
                    disabled: paramsExist,
                    // 给定默认数据
                    dataMethod: ()=> {
                        if(paramsExist){
                            return {data: [{
                                    id: props.funcId,
                                    name: props.funcName,
                                }]}
                        }
                        return {data: []}
                    },
                    // 路由中没有数据，开启远程搜索
                    remote: !paramsExist,
                    remoteMethod: (query: string) => {
                        if(!query){
                            return {data: []}
                        }
                        return funcPageApi({name: query})
                    },
                    // 下拉显示昵称
                    props: {label: 'name'}
                }// r

                return r
            }
        }
    }
}

export const useCascaderFuncCompItem = ({fieldName= 'parentId',required=false,label= '父级',valueChange = ()=>{}})=>{
    return  {
        field: {
            name: fieldName,
            valueChange: valueChange
        },
        element: {
            comp: 'PtCascader',
            formItemProps: {
                label: label,
                required: required
            },
            compProps: {
                // 加载数据
                dataMethod: () => { return funcListApi({})},
                dataMethodResultHandleConvertToTree: true,
            }
        }
    }
}

export const useSelectFuncGroupCompItem = ({required= false,tips = ''})=>{
    return     {
        field: {
            name: 'funcGroupId'
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: '功能分组',
                required: required,
                tips: tips || undefined
            },
            compProps: {
                dataMethod: funcGroupListApi
            }
        }
    }
}

export const funcColumns = [
    {
        prop: 'name',
        label: '名称',
        width: 150,
        showOverflowTooltip: true
    },
    {
        prop: 'code',
        label: '编码',
        showOverflowTooltip: true
    },
    {
        prop: 'parentName',
        label: '父级',
        showOverflowTooltip: true
    },
    {
        prop: 'funcGroupName',
        label: '功能分组',
        showOverflowTooltip: true
    },
    {
        prop: 'icon',
        label: '图标',
        // elementPlus 图标
        columnView: 'elIcon',
        width: 50,
    },
    {
        prop: 'isDisabled',
        label: '是否禁用',
        width: 70,
        formatter: (row, column, cellValue, index) => {
            return cellValue ? '禁用' : '启用'
        }
    },
    {
        prop: 'isShow',
        label: '是否展示',
        width: 70,
        formatter: (row, column, cellValue, index) => {
            return cellValue ? '展示' : '隐藏'
        }
    },
    {
        prop: 'url',
        label: '路由',
        showOverflowTooltip: true
    },
    {
        prop: 'typeDictName',
        label: '类型',
        width: 50,
    },
    {
        prop: 'permissions',
        label: '权限码',
        showOverflowTooltip: true
    },
    {
        prop: 'seq',
        label: '排序',
        width: 50,
    },
    {
        prop: 'componentOf',
        label: '归属组件'
    },
    {
        prop: 'remark',
        label: '描述'
    }
]