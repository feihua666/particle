export const remoteSelectCompanyVcInvestInstitutionProps = {
    // 加载数据初始化参数,路由传参
    companyVcInvestInstitutionId: {
        type: String
    },
    // 加载数据初始化参数,路由传参
    companyVcInvestInstitutionName: String
}

/**
 * 远程搜索企业投资机构表表单配置项
 * 属性中必须有 companyVcInvestInstitutionId 和 companyVcInvestInstitutionName 两个属性
 * @param props
 */
export const useRemoteSelectFuncCompItem = ({props,required})=>{
  return   {
        field: {
            name: 'companyVcInvestInstitutionId',
            value: props.companyVcInvestInstitutionId
        },
        element: {
            comp: 'PtSelect',
                formItemProps: {
                label: '企业投资机构表',
                required: required
            },
            compProps: ()=> {
                let paramsExist = !!(props.companyVcInvestInstitutionId && props.companyVcInvestInstitutionName)
                let r = {
                    placeholder: '输入企业投资机构表名称搜索',
                    disabled: paramsExist,
                    // 给定默认数据
                    dataMethod: ()=> {
                        if(paramsExist){
                            return {data: [{
                                    id: props.companyVcInvestInstitutionId,
                                    name: props.companyVcInvestInstitutionName,
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
