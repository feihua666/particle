<script setup name="clientSettings" lang="ts">
/**
 * oauth2客户端设置
 */
import {reactive ,ref,onMounted} from 'vue'
import {algorithmList} from "../../../api/client/front/oauth2RegisteredClientFrontApi";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 初始化数据
  initJsonStr: {
    type: String
  },
  // 表单提交方法
  onSubmit:{
    type: Function,
    default: ()=>({})
  },
  // 表单中的
  authorizationGrantTypes: {
    type: Array,
    default: ()=> ([])
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},
})
const checkAuthorizationCodeExist = ({authorizationGrantTypes}) => {
  if(authorizationGrantTypes){
    return authorizationGrantTypes.some(item => item == 'authorization_code')
  }
  return false
}
// 表单项
const formComps = ref(
    [

      {
        field: {
          name: 'settings.client.require-proof-key',
          value: true
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: 'REQUIRE_PROOF_KEY',
            required: () => checkAuthorizationCodeExist(props.authorizationGrantTypes),
            // 参考文档：https://blog.csdn.net/mzl87/article/details/121497358?share_token=2A2B2103-0636-4F96-B556-6231F3B70991&tt_from=weixin&utm_source=weixin&utm_medium=toutiao_ios&utm_campaign=client_share&wxshare_count=1
            // 或者搜索 pkce，PKCE 全称是 Proof Key for Code Exchange
            tips: '是否要求客户端提供验证密钥，这是针对授权码模式(authorization_code)，客户端无能力保护自己的客户端密钥（client_secret）时，防止 code 被拦截的验证'
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'settings.client.require-authorization-consent',
          value: true
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: 'REQUIRE_AUTHORIZATION_CONSENT',
            required: () => checkAuthorizationCodeExist(props.authorizationGrantTypes),
            tips: '针对授权类型为 authorization_code 或 device_code 时，是否需要资源拥有者确认，建议选择需要确认，一般是这个流程'
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'settings.client.token-endpoint-authentication-signing-algorithm',
        },
        element: {
          comp: 'PtSelect',
          formItemProps: {
            label: 'TOKEN_ENDPOINT_AUTHENTICATION_SIGNING_ALGORITHM',
            required: false,
            tips: '授权方法为 private_key_jwt 或 client_secret_jwt 时必填,主要指定签名方式'
          },
          compProps: {
            dataMethod: algorithmList,
            props: {
              value: 'value',
              name: 'name'
            }
          }
        }
      },
      {
        field: {
          name: 'settings.client.jwk-set-url',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: 'JWK_SET_URL',
            required: false,
            tips: 'http(s)开头',
            displayBlock: true
          },
          compProps: {
          }
        }
      },
    ],
)
onMounted(()=>{
  // 挂载后初始化数据
  // 挂载后初始化数据
  if(props.initJsonStr){
    let form = JSON.parse(props.initJsonStr)
    for (let formKey in form) {
      reactiveData.form[formKey] = form[formKey]
    }
  }
})
// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认',
})
// 提交按钮
const submitMethod = () => {
  props.onSubmit()

}
// 暴露方法
defineExpose({
  form: reactiveData.form
})
</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="120"
          :method="submitMethod"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          inline
          :layout="[3,1]"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>