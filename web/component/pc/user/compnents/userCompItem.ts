import {page as userPageApi} from "../api/admin/userAdminApi";
import {page as userIdentifierPageApi} from "../api/admin/userIdentifierAdminApi";

export const remoteSelectUserProps = {
    // 加载数据初始化参数,路由传参
    userId: {
        type: String
    },
    // 加载数据初始化参数,路由传参
    userNickname: String
}
/**
 * 远程搜索用户表单配置项
 * 属性中必须有 userId 和 userNickname 两个属性
 * @param props
 */
export const remoteSelectUserCompItem = ({props,required})=>{
  return   {
        field: {
            name: 'userId',
            value: props.userId
        },
        element: {
            comp: 'PtSelect',
                formItemProps: {
                label: '用户',
                required: required
            },
            compProps: ()=> {
                let paramsExist = props.userId && props.userNickname
                let r = {
                    clearable: true,
                    placeholder: '输入用户昵称搜索',
                    disabled: paramsExist,
                    // 给定默认数据
                    dataMethod: ()=> {
                        if(paramsExist){
                            return {data: [{
                                    id: props.userId,
                                    nickname: props.userNickname,
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
                        return userPageApi({nickname: query})
                    },
                    // 下拉显示昵称
                    props: {label: 'nickname'}
                }// r

                return r
            }
        }
    }
}


export const remoteSelectUserIdentifierProps = {
    // 加载数据初始化参数,路由传参
    identifierId: {
        type: String
    },
    // 加载数据初始化参数,路由传参
    identifier: String
}
/**
 * 远程搜索用户登录标识表单配置项
 * 属性中必须有 identifierId 和 identifier 两个属性
 * @param props
 */
export const remoteSelectUserIdentifierCompItem = ({props,required})=>{
    return   {
        field: {
            name: 'identifierId',
            value: props.identifierId
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: '登录标识',
                required: required
            },
            compProps: ()=> {
                let paramsExist = props.identifierId && props.identifier
                let r = {
                    clearable: true,
                    placeholder: '输入登录标识搜索',
                    disabled: paramsExist,
                    // 给定默认数据
                    dataMethod: ()=> {
                        if(paramsExist){
                            return {data: [{
                                    id: props.identifierId,
                                    identifier: props.identifier,
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
                        return userIdentifierPageApi({identifier: query})
                    },
                    // 下拉显示昵称
                    props: {label: 'identifier'}
                }// r

                return r
            }
        }
    }
}
/**
 * 重置密码表单项
 */
export const resetPasswordCompItems = [
    {
        field: {
            name: 'password'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '密码',
                required: true
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'expireAt',
        },
        element: {
            comp: 'PtDatePicker',
            formItemProps: {
                label: '过期时间'
            },
            compProps:  {
                clearable: true,
                type: "datetime",
                placeholder: '不填写永不过期'
            }
        }
    },
    {
        field: {
            name: 'isNeedUpdate',
            value: false
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '提示修改',
                required: true
            },
            compProps: {
                clearable: true,
                activeText: '提示',
                inactiveText: '不提示',
            }
        }
    },
    {
        field: {
            name: 'needUpdateMessage'
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '提示修改消息内容',
                required: ({form})=> form.isNeedUpdate
            },
            compProps: {
                clearable: true,
            }
        }
    },
]