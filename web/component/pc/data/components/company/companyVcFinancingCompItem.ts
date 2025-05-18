export const remoteSelectCompanyVcFinancingProps = {
    // 加载数据初始化参数,路由传参
    companyVcFinancingId: {
        type: String
    },
    // 加载数据初始化参数,路由传参
    companyVcFinancingName: String
}

/**
 * 远程搜索企业融资表ID表单配置项
 * 属性中必须有 companyVcFinancingId 和 companyVcFinancingName 两个属性
 * @param props
 */
export const useRemoteSelectFuncCompItem = ({props,required})=>{
  return   {
        field: {
            name: 'companyVcFinancingId',
            value: props.companyVcFinancingId
        },
        element: {
            comp: 'PtSelect',
                formItemProps: {
                label: '企业融资表ID',
                required: required
            },
            compProps: ()=> {
                let paramsExist = !!(props.companyVcFinancingId && props.companyVcFinancingName)
                let r = {
                    placeholder: '输入企业融资表ID名称搜索',
                    disabled: paramsExist,
                    // 给定默认数据
                    dataMethod: ()=> {
                        if(paramsExist){
                            return {data: [{
                                    id: props.companyVcFinancingId,
                                    name: props.companyVcFinancingName,
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
                        return xxxxxPageApi({name: query})
                    },
                    // 下拉显示属性
                    props: {label: 'name'}
                }// r

                return r
            }
        }
    }
}
