import {page as funcPageApi} from "../api/admin/funcAdminApi";

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
export const remoteSelectFuncCompItem = ({props,required})=>{
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
