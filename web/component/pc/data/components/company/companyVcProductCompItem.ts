export const remoteSelectCompanyVcProductProps = {
    // 加载数据初始化参数,路由传参
    companyVcProductId: {
        type: String
    },
    // 加载数据初始化参数,路由传参
    companyVcProductName: String
}

/**
 * 远程搜索企业融资产品表ID表单配置项
 * 属性中必须有 companyVcProductId 和 companyVcProductName 两个属性
 * @param props
 */
export const useRemoteSelectFuncCompItem = ({props,required})=>{
  return   {
        field: {
            name: 'companyVcProductId',
            value: props.companyVcProductId
        },
        element: {
            comp: 'PtSelect',
                formItemProps: {
                label: '企业融资产品表ID',
                required: required
            },
            compProps: ()=> {
                let paramsExist = !!(props.companyVcProductId && props.companyVcProductName)
                let r = {
                    placeholder: '输入企业融资产品表ID名称搜索',
                    disabled: paramsExist,
                    // 给定默认数据
                    dataMethod: ()=> {
                        if(paramsExist){
                            return {data: [{
                                    id: props.companyVcProductId,
                                    name: props.companyVcProductName,
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
