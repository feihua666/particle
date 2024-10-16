import {useRemoteSelectUserCompItem} from "../../../../user/components/userCompItem";
import {useOauth2SelectClientCompItem} from "../../../../oauth2authorization/components/oauth2authorizationRegisteredClientCompItem.ts";
import {useSelectOpenapiFeeCompItem} from "../../openplatformOpenapiFeeCompItem";
import {useSelectCrmCustomerCompItem} from "../../../../crm/components/crmCompItem";
import {useSelectOpenapiLimitRuleCompItem} from "../../openplatformOpenapiLimitRuleCompItem";

export const pageFormItems = [
    {
        field: {
            name: 'name',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '名称',

            },
            compProps: {
                clearable: true,
                placeholder: '左前缀匹配',
            }
        }
    },
    useOauth2SelectClientCompItem({
        fieldName: 'appId',
        label: 'appId'
    }),

    useRemoteSelectUserCompItem({
        props: {},
        fieldName: 'ownerUserId',
        propUserIdFieldName: 'ownerUserId',
        propUserNicknameFieldName: 'ownerUserNickname',
        label: '归属用户'
    }),
    useSelectCrmCustomerCompItem({fieldName: 'ownerCustomerId',label: '归属客户'}),
    useSelectOpenapiFeeCompItem({}),

]
export const useAddPageFormItems = ({props,appAlgorithmSecretConfigsRef}) => {
    return [

        {
            field: {
                name: 'name',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '名称',
                    required: true,
                  tips: '建议填写三方申请公司/姓名'
                },
                compProps: {
                    clearable: true,
                }
            }
        },

      useOauth2SelectClientCompItem({
        fieldName: 'appId',
        label: 'appId',
        tips: '需要前往 oauth2客户端管理 创建',
        required: true
      }),

        useRemoteSelectUserCompItem({
            props,
            fieldName: 'ownerUserId',
            propUserIdFieldName: 'ownerUserId',
            propUserNicknameFieldName: 'ownerUserNickname',
            label: '归属用户',
          tips: '这有利于用户自动操作时绑定关系'
        }),
        useSelectCrmCustomerCompItem({fieldName: 'ownerCustomerId',label: '归属客户'}),
        {
            field: {
                name: 'requestAlgorithmSecretJson',
            },
            element: {
                comp: 'PtButton',
                formItemProps: {
                    label: '请求配置',
                    required: true,
                    tips: '请求算法与密钥等相关配置'
                },
                compProps: ({form,formData})=>{
                    return {
                        text: true,
                        type: form.requestAlgorithmSecretJson ? 'primary' : 'default',
                        buttonText: '点击配置',
                        method: ()=>{
                            if(appAlgorithmSecretConfigsRef.value){
                                appAlgorithmSecretConfigsRef.value.reactiveData.openapiRequestAlgorithmSecretConfigJson.dialogVisible = true
                            }
                        }
                    }
                }
            }
        },
        {
            field: {
                name: 'responseAlgorithmSecretJson',
            },
            element: {
                comp: 'PtButton',
                formItemProps: {
                  label: '响应配置',
                  tips: '响应算法与密钥等相关配置',
                },
                compProps: ({form,formData})=>{
                    return {
                        text: true,
                        type: form.responseAlgorithmSecretJson ? 'primary' : 'default',
                        buttonText: '点击配置',
                        method: ()=>{
                            if(appAlgorithmSecretConfigsRef.value){
                                appAlgorithmSecretConfigsRef.value.reactiveData.openapiResponseAlgorithmSecretConfigJson.dialogVisible = true
                            }
                        }
                    }
                }
            }
        },


        {
            field: {
                name: 'scopes',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '访问范围配置',
                  tips: '额外配置的访问权限,多个以逗号分隔'

                },
                compProps: {
                  placeholder: '如：admin:user:create',
                    clearable: true,
                }
            }
        },
      useSelectOpenapiFeeCompItem({
        tips: '默认的计费配置，可以在分配接口时单独指定计费规则，不配置，不进行计费'
      }),
        useSelectOpenapiLimitRuleCompItem({
            tips: '针对该应用进行限制配置，注意区分于应用接口配置的限制规则，该配置是应用级的'
        }),

        {
            field: {
                name: 'isDisabled',
                value: false
            },
            element: {
                comp: 'el-switch',
                formItemProps: {
                    label: '是否禁用',
                    required: true,
                },
                compProps: {
                }
            }
        },

        {
            field: {
                name: 'disabledReason'
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '禁用原因',
                    required: ({form}) => form.isDisabled == true

                },
                compProps: {
                    clearable: true,
                }
            }
        },
        {
            field: {
                name: 'isCheckSignature',
                value: true
            },
            element: {
                comp: 'el-switch',
                formItemProps: {
                    label: '是否检查签名',
                    required: true,
                },
                compProps: {
                }
            }
        },
    ]
}

// 更新和添加一致
export const useUpdatePageFormItems = useAddPageFormItems

