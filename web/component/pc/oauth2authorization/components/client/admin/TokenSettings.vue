<script setup name="clientSettings" lang="ts">
/**
 * oauth2 token 设置
 */
import {onMounted, reactive, ref} from 'vue'
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

// 后端存储是一个对象，参考两个静态属性对象：org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat
let self_contained = {value: 'self-contained'}
let self_contained_json_str = JSON.stringify(self_contained)
let reference = {value: 'reference'}
let reference_json_str = JSON.stringify(reference)

// 表单项
const formComps = ref(
    [

      {
        field: {
          name: 'settings.token.authorization-code-time-to-live',
          value: 'PT5M'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: 'AUTHORIZATION_CODE_TIME_TO_LIVE',
            required: true,
            tips: 'authorization_code 时长设置，建议设置为分钟为单位，例：PT5M 代表5分钟'
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'settings.token.access-token-time-to-live',
          value: 'PT5M'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: 'ACCESS_TOKEN_TIME_TO_LIVE',
            required: true,
            tips: 'access_token 时长设置，建议设置为分钟为单位，例：PT5M 代表5分钟'
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'settings.token.access-token-format',
          value: self_contained_json_str
        },
        element: {
          comp: 'PtSelect',
          formItemProps: {
            label: 'ACCESS_TOKEN_FORMAT',
            required: true,
            tips: 'access_token 格式设置'
          },
          compProps: {
            dataMethod: ()=>{
              return {
                data: [{
                  id: self_contained_json_str,
                  name: 'SELF_CONTAINED',
                },
                  {
                    id: reference_json_str,
                    name: 'REFERENCE',
                  }]
              }
            }
          }
        }
      },
      {
        field: {
          name: 'settings.token.reuse-refresh-tokens',
          value: true
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: 'REUSE_REFRESH_TOKENS',
            required: true,
            tips: 'refresh_token 是否重复使用，这是使用 refresh_token 刷新 token 时，如果设置为 true，将返回原来 refresh_token，否则生成一个新的 refresh_token'
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'settings.token.refresh-token-time-to-live',
          value: 'PT60M'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: 'REFRESH_TOKEN_TIME_TO_LIVE',
            required: true,
            tips: 'refresh_token 时长设置，建议设置为分钟为单位，例：PT60M 代表60分钟、PT1H 代表1小时'
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'settings.token.id-token-signature-algorithm',
          value: 'RS256'
        },
        element: {
          comp: 'PtSelect',
          formItemProps: {
            label: 'ID_TOKEN_SIGNATURE_ALGORITHM',
            required: true,
            tips: 'token 签名设置'
          },
          compProps: {
            dataMethod: () => {
              return algorithmList({type: 'signature'})
            },
            props: {
              value: 'value',
              label: 'name'
            }
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
      if(formKey == 'settings.token.access-token-format'){
        let value = form[formKey]
        if (value) {
          if (typeof value == 'string') {
            //  如果是字符串
            // 这里转换一下，因为有可能是万一手动修改了数据，导致字符串不相等，默认值选不中
            reactiveData.form[formKey] = JSON.stringify(JSON.parse(value))
          }else {
            // 如果是对象转为字符串
            // 这里转换一下，因为有可能是万一手动修改了数据，导致字符串不相等，默认值选不中
            reactiveData.form[formKey] = JSON.stringify(value)
          }
        }
      }else {
        reactiveData.form[formKey] = form[formKey]
      }
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
