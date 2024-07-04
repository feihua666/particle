import {page as funcApplicationPageApi} from "../../api/application/admin/funcApplicationAdminApi.ts";

export const remoteSelectFuncApplicationProps = {
    // 加载数据初始化参数,路由传参
    funcApplicationId: {
        type: String
    },
    // 加载数据初始化参数,路由传参
    funcApplicationName: String
}
/**
 * 远程搜索功能应用表单配置项
 * 属性中必须有 funcApplicationId 和 funcApplicationName 两个属性
 * @param props
 */
export const remoteSelectFuncApplicationCompItem = ({props,required})=>{
  return   {
        field: {
            name: 'funcApplicationId',
            value: props.funcApplicationId
        },
        element: {
            comp: 'PtSelect',
                formItemProps: {
                label: '功能应用',
                required: required
            },
            compProps: ()=> {
                let paramsExist = !!(props.funcApplicationId && props.funcApplicationName)
                let r = {
                    placeholder: '输入功能应用名称搜索',
                    disabled: paramsExist,
                    // 给定默认数据
                    dataMethod: ()=> {
                        if(paramsExist){
                            return {data: [{
                                    id: props.funcApplicationId,
                                    name: props.funcApplicationName,
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
                        return funcApplicationPageApi({name: query})
                    },
                    // 下拉显示昵称
                    props: {label: 'name'}
                }// r

                return r
            }
        }
    }
}
