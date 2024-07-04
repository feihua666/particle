import {anyObj, clone} from "../../../../../../global/common/tools/ObjectTools";

const clientAuthenticationMethodsFieldName = 'clientAuthenticationMethods'
const authorizationGrantTypesFieldName = 'authorizationGrantTypes'
const scopesFieldName = 'scopes'
const arrayFieldNames = [clientAuthenticationMethodsFieldName,authorizationGrantTypesFieldName,scopesFieldName]
/**
 * 表单中是数组的字段转为逗号分隔
 * @param form
 */
export const convertSubmitForm = (form: anyObj) : anyObj=>{
    let tempForm = clone(form)
    arrayFieldNames.forEach(item => {
        tempForm[item] = tempForm[item].join(',')
    })
    return tempForm
}
/**
 * 将加载的修改数据转为数组
 * @param form
 */
export const convertLoadedData = (data: anyObj) : anyObj=>{
    arrayFieldNames.forEach(item => {
        if(data[item]){
            data[item] = data[item].split(',')
        }
    })
    return data
}

export const pageFormItems = [
    {
        field: {
            name: 'clientName',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '客户端名称',

            },
            compProps: {
                clearable: true,
                placeholder: '左前缀匹配',
            }
        }
    },
    {
        field: {
            name: 'clientId',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '客户端ID',

            },
            compProps: {
                clearable: true,
            }
        }
    },

]
/**
 * @param oauth2RegisteredClientManageSettingConfigsRef
 * @param isForAdd 是否用于添加
 */
export const useAddPageFormItems = ({oauth2RegisteredClientManageSettingConfigsRef,isForAdd = true})=>{
    return [

        {
            field: {
                name: 'clientName',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '客户端名称',
                    required: true,
                    tips: '名称仅做为展示使用'
                },
                compProps: {
                    clearable: true,
                }
            }
        },


        {
            field: {
                name: 'clientSecretExpiresAt',
            },
            element: {
                comp: 'PtDatePicker',
                formItemProps: {
                    label: '过期时间',
                    tips: '不填写永不过期'
                },
                compProps: {
                    clearable: true,
                    type: "datetime"
                }
            }
        },

        {
            field: {
                name: clientAuthenticationMethodsFieldName,
                value: ['client_secret_basic']
            },
            element: {
                comp: 'PtDictFrontSelect',
                formItemProps: {
                    label: '身份验证方法',
                    required: true,
                    labelTips: '主要是在接口对接时如何传递client_id和client_secret</br>建议使用 basic 方式'
                },
                compProps: {
                    // 字典查询
                    dictParam: {groupCode: 'oauth2_authorization_client_authentication_method'},
                    // 选取value为选重值
                    props: {value: 'value'},
                    view: 'checkbox'
                }
            }
        },


        {
            field: {
                name: authorizationGrantTypesFieldName,
                value: []
            },
            element: {
                comp: 'PtDictFrontSelect',
                formItemProps: {
                    label: '授权类型',
                    required: true,
                    labelTips: '1. 三方登录应该使用 authorization_code refresh_token</br>2. 开放接口应该使用 client_credentials'
                },
                compProps: {
                    // 字典查询
                    dictParam: {groupCode: 'oauth2_authorization_authorization_grant_type'},
                    // 选取value为选重值
                    props: {value: 'value'},
                    view: 'checkbox'
                }
            }
        },


        {
            field: {
                name: 'redirectUris',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '重定向URI',
                    tips: 'http(s)开头',
                    displayBlock: true

                },
                compProps: {
                    clearable: true,
                }
            }
        },


        {
            field: {
                name: scopesFieldName,
                value: []
            },
            element: {
                comp: 'PtDictFrontSelect',
                formItemProps: {
                    label: '访问范围',
                    required: true,
                    labelTips: '1. 三方登录应该使用 openid profile</br>2. 其它隐私信息请酌情使用'
                },
                compProps: {
                    // 字典查询
                    dictParam: {groupCode: 'oauth2_authorization_scope'},
                    // 选取value为选重值
                    props: {value: 'value'},
                    view: 'checkbox'
                }
            }
        },


        {
            field: {
                name: 'clientSettings',
            },
            element: {
                comp: 'PtButton',
                formItemProps: {
                    label: '客户端设置',
                    required: !isForAdd,
                },
                compProps: ({form,formData})=>{
                    return {
                        text: true,
                        type: form.clientSettings ? 'primary' : 'default',
                        buttonText: '点击配置',
                        method: ()=>{
                            if(oauth2RegisteredClientManageSettingConfigsRef.value){
                                oauth2RegisteredClientManageSettingConfigsRef.value.reactiveData.clientSettingConfigJson.dialogVisible = true
                            }
                        }
                    }
                }
            }
        },


        {
            field: {
                name: 'tokenSettings',
            },
            element: {
                comp: 'PtButton',
                formItemProps: {
                    label: '令牌设置',
                    required: !isForAdd,
                },
                compProps: ({form,formData})=>{
                    return {
                        text: true,
                        type: form.tokenSettings ? 'primary' : 'default',
                        buttonText: '点击配置',
                        method: ()=>{
                            if(oauth2RegisteredClientManageSettingConfigsRef.value){
                                oauth2RegisteredClientManageSettingConfigsRef.value.reactiveData.tokenSettingConfigJson.dialogVisible = true
                            }
                        }
                    }
                }
            }
        },


    ]
}

// 更新和添加一致
export const useUpdatePageFormItems = useAddPageFormItems

