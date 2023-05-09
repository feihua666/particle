import {page as opLogPageApi} from "../api/admin/opLogAdminApi";

export const remoteSelectOpLogProps = {
    // 加载数据初始化参数,路由传参
    opLogId: {
        type: String
    },
    // 加载数据初始化参数,路由传参
    opLogName: String
}
/**
 * 远程搜索操作日志表单配置项
 * 属性中必须有 opLogId 和 opLogName 两个属性
 * @param props
 */
export const useRemoteSelectOpLogCompItem = ({props,
                                                required = false,
                                                fieldName='opLogId',
                                                propOpLogIdFieldName='opLogId',
                                                propOpLogNicknameFieldName='opLogName',
                                                label='操作日志',
                                                valueChange = ()=>{}})=>{

  return   {
        field: {
            name: fieldName,
            value: props[propOpLogIdFieldName],
            valueChange: valueChange
        },
        element: {
            comp: 'PtSelect',
                formItemProps: {
                label: label,
                required: required
            },
            compProps: ()=> {
                let paramsExist = !!(props[propOpLogIdFieldName] && props[propOpLogNicknameFieldName])
                let r = {
                    placeholder: '输入操作名称',
                    disabled: paramsExist,
                    // 给定默认数据
                    dataMethod: ()=> {
                        if(paramsExist){
                            return {data: [{
                                    id: props[propOpLogIdFieldName],
                                    name: props[propOpLogNicknameFieldName],
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
                        return opLogPageApi({name: query})
                    },
                    // 下拉显示昵称
                    props: {label: 'name'}
                }// r

                return r
            }
        }
    }
}
