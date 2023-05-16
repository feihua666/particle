<script setup name="LoginUserUpdatePasswordForm.vue"  lang="ts">

/**
 * 用户当前登录用户修改密码，目前是根据登录标识id修改
 */

import {getCurrentInstance,reactive ,ref} from 'vue'
import { getLoginUserUpdatePasswordCaptcha, identifierPwdUpdate} from '../../api/userLoginApi'
import {useLoginUserStore} from '../../../../../global/common/security/loginUserStore'
import {isString} from '../../../../../global/common/tools/StringTools'
import {isFunction} from '../../../../../global/common/tools/FunctionTools'

const { appContext } = getCurrentInstance()
// 路由
const router = appContext.config.globalProperties.$router
const loginUserStore = useLoginUserStore()
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 密码修改成功后，执行，如果为 String，就会被当作路由跳转 replace
  updateSuccess: {
    type: [Function,String]
  },
  // 是否启动验证码,需要后台配合配置路径拦截，参见后端全局验证码组件
  useCaptcha:{
    type: Boolean,
    default: false
  },
  // 用户登录标识id
  userIdentifierId:{
    type: String,
    required: true
  }
})
const captchaSrc = ref('')
const formComps: Array<any> = [
  {
    field: {
      name: 'oldPassword'
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '原密码',
        required: true
      },
      compProps: {
        type: 'password',
        clearable: true,
        showPassword: true,
        placeholder: '原密码'
      }
    }
  },
  {
    field: {
      name: 'password'
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '新密码',
        required: true
      },
      compProps: {
        type: 'password',
        clearable: true,
        showPassword: true,
        placeholder: '新密码'
      }
    }
  },
  {
    field: {
      name: 'confirmPassword'
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '密码确认',
        required: true,
        rules: [
          { validator: (rule: any, value: any, callback: any) => {
              if (value && reactiveData.form.password) {
                if (value != reactiveData.form.password) {
                  callback(new Error('再次密码输入不一致'))
                }else {
                  callback()
                }
              } else {
                callback()
              }
            }, trigger: 'blur'
          },
        ],
        validate: {
          // 将rules添加到 validate下面，否则已填提示为英文

        },

      },
      compProps: {
        type: 'password',
        clearable: true,
        showPassword: true,
        placeholder: '密码确认'
      }
    }
  },
]
// 启动验证码，则添加验证码表单
if (props.useCaptcha) {
  formComps.push(  {
    field: {
      name: 'captchaValue'
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '验证码',
        required: true
      },
      compProps: {
        type: 'text',
        clearable: true,
        placeholder: '验证码',
      }
    }
  },{
    field: {
      name: ''
    },
    element: {
      comp: 'el-image',
      formItemProps: {
        label: '',
      },
      compProps: ()=>{
        return {
          class: 'pt-pointer',
          title: '点击切换验证码',
          src: captchaSrc.value,
          onClick: loadCaptchaImage
        }
      }
    }
  })
}
// 属性
const reactiveData = reactive({
  form: {
    captchaUniqueIdentifier: '',
    userIdentifierId: props.userIdentifierId
  },
  formComps: formComps
})
// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认修改',
})
// 修改成功后处理逻辑，主要是调用回调参数
const updateResult = (result):void => {
  result.then(res => {
    if (!res.data.success) {
      return
    }
    if(props.updateSuccess){
      if(isString(props.updateSuccess) && router){
        router.replace(props.updateSuccess)
      }else if(isFunction(props.updateSuccess)){
        props.updateSuccess()
      }
    }
  }).catch(() => {
    // 验证码错误也需要刷新
    loadCaptchaImage()
  //  catch 一下异常，否则控制台打印一大堆
  })
}

const loadCaptchaImage = ()=>{
  if (props.useCaptcha) {
    getLoginUserUpdatePasswordCaptcha().then(res => {
      captchaSrc.value = res.data.data.base64
      reactiveData.form.captchaUniqueIdentifier = res.data.data.captchaUniqueIdentifier
    })
  }

}
loadCaptchaImage()
</script>
<template>
  <PtForm :form="reactiveData.form"
          :method="identifierPwdUpdate"
          labelWidth="70"
          defaultButtonsShow="submit"
          :submitAttrs="submitAttrs"
          class="login-user-update-pwd-form"
          :layout="[1,1,1]"
          size="default"
          @methodResult="updateResult"
          :comps="reactiveData.formComps">
  </PtForm>
</template>

<style scoped>
.login-user-update-pwd-form{
  padding: 2rem;
  background: rgba(255, 255, 255, 0.5) none repeat scroll 0 0;
}
</style>