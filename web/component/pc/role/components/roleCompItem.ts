import {page as rolePageApi} from "../api/admin/roleAdminApi";

export const remoteSelectRoleProps = {
    // 加载数据初始化参数,路由传参
    roleId: {
        type: String
    },
    // 加载数据初始化参数,路由传参
    roleName: String
}
/**
 * 远程搜索角色表单配置项
 * 属性中必须有 roleId 和 roleName 两个属性
 * @param props
 */
export const remoteSelectRoleCompItem = ({props,required})=>{
  return   {
        field: {
            name: 'roleId',
            value: props.roleId
        },
        element: {
            comp: 'PtSelect',
                formItemProps: {
                label: '角色',
                required: required
            },
            compProps: ()=> {
                let paramsExist = !!(props.roleId && props.roleName)
                let r = {
                    clearable: true,
                    placeholder: '输入角色名称搜索',
                    disabled: paramsExist,
                    // 给定默认数据
                    dataMethod: ()=> {
                        if(paramsExist){
                            return {data: [{
                                    id: props.roleId,
                                    name: props.roleName,
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
                        return rolePageApi({name: query})
                    },
                    // 下拉显示昵称
                    props: {label: 'name'}
                }// r

                return r
            }
        }
    }
}
