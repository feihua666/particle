<script setup name="VerificationCodeLoginForm"  lang="ts">
// 基于验证码的登录
import {getCurrentInstance,reactive ,ref,useTemplateRef} from 'vue'
import {getLoginCaptcha, getLoginDynamicCaptcha, loginDynamicCaptcha} from '../../api/userLoginApi'
import {useLoginUserStore} from '../../../../../global/common/security/loginUserStore'
import {isString} from '../../../../../global/common/tools/StringTools'
import {isFunction} from '../../../../../global/common/tools/FunctionTools'
import {timer} from "../../../../../global/common/tools/TimerExcTools";

const { appContext } = getCurrentInstance()
// 路由
const router = appContext.config.globalProperties.$router
const loginUserStore = useLoginUserStore()
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 登录成功后，执行，如果为 String，就会被当作路由跳转 replace
  loginSuccess: {
    type: [Function,String]
  },
  // 是否启动验证码
  useCaptcha:{
    type: Boolean,
    default: true
  }
})
const captchaSrc = ref('')
const verificationCodeButtonText = ref('获取验证码')
const verificationCodeButtonDisabled = ref(false)
const verificationCodeInputRef = useTemplateRef('verificationCodeInputRef')
const formComps: Array<any> = [
  {
    field: {
      name: 'username'
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '手机号或邮箱',
        required: true
      },
      compProps: {
        clearable: true,
        placeholder: '手机号或邮箱',
        prefixIcon: 'UserFilled'
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
        prefixIcon: 'Cellphone'
      }
    }
  },{
    field: {
      name: ''
    },
    element: {
      comp: 'PtImage',
      formItemProps: {
        label: '',
        labelWidth: '20px'
      },
      compProps: ()=>{
        return {
          class: 'pt-pointer',
          title: '点击切换验证码',
          style: 'width:120px;height: var(--el-input-inner-height);',
          src: captchaSrc.value,
          onClick: loadLoginCaptchaImage
        }
      }
    }
  })
}
formComps.push(  {
  field: {
    name: 'verificationCode'
  },
  element: {
    comp: 'PtInput',
    formItemProps: {
      label: '动态验证码',
      required: true
    },
    compProps: ({form,formData})=> {
      let r = {
        type: 'text',
        clearable: true,
        showPassword: true,
        placeholder: '动态验证码',
        prefixIcon: 'Lock',
        validateEvent: false,
        ref: 'verificationCodeInputRef',
        slots:{
          suffix: {
            is: 'PtButton',
            attrs: {
              link: true,
              icon: 'empty',
              type:  'primary',
              disabled: verificationCodeButtonDisabled.value,
              buttonText: verificationCodeButtonText.value,
              method: ()=> {
                console.log(verificationCodeInputRef)
                // verificationCodeInputRef.focus()
                verificationCodeButtonDisabled.value = true;
                let total = 60
                timer((times)=>{
                  let remaining = (total - times)
                  verificationCodeButtonText.value = remaining + ' s'
                  if (remaining <= 1) {
                    verificationCodeButtonText.value = '重新获取验证码'
                    verificationCodeButtonDisabled.value = false
                  }
                }, 1000, total)

              }
            }
          }
        },

      }
      return r
    }
  }
}, {
  field: {
    name: 'rememberMe',
    value: true
  },
  element: {
    comp: 'el-checkbox',
    formItemProps: {
      label: '记住我',
      required: true
    },
    compProps: {
      label: '记住我',
    }
  }
})
// 属性
const reactiveData = reactive({
  form: {
    captchaUniqueIdentifier: ''
  },
  formComps: formComps
})
// 提交按钮属性
const submitAttrs = ref({
  buttonText: '登录',
  class: 'pt-width-100-pc'
})
// 登录成功后获取登录用户
const loginResult = (result):void => {
  result.then(res => {
    loginUserStore.changeLoginUser(res.data.data)
    if(props.loginSuccess){
      if(isString(props.loginSuccess) && router){
        router.replace(props.loginSuccess)
      }else if(isFunction(props.loginSuccess)){
        props.loginSuccess()
      }
    }
  }).catch(() => {
    // 验证码错误也需要刷新
    loadLoginCaptchaImage()
  //  catch 一下异常，否则控制台打印一大堆
  })
}

const loadLoginCaptchaImage = ()=>{
  if (props.useCaptcha) {
    getLoginCaptcha().then(res => {
      captchaSrc.value = res.data.data.base64
      reactiveData.form.captchaUniqueIdentifier = res.data.data.captchaUniqueIdentifier
    })
  }
}
const loadLoginDynamicCaptcha = ()=> {
  getLoginDynamicCaptcha().then(res => {
    captchaSrc.value = res.data.data.base64
    reactiveData.form.captchaUniqueIdentifier = res.data.data.captchaUniqueIdentifier
  })
}
loadLoginCaptchaImage()
</script>
<template>
  <PtForm :form="reactiveData.form" hide-required-asterisk
          :method="loginDynamicCaptcha"
          labelWidth="0"
          defaultButtonsShow="submit"
          :submitAttrs="submitAttrs"
          class="login-form"
          :layout="[1,[14,10],1,1]"
          size="large"
          @methodResult="loginResult"
          :comps="reactiveData.formComps">
  </PtForm>
</template>

<style scoped>
.login-form{
  width: 25rem !important;
  background: var(--el-bg-color);
}

</style>
<style>
.login-form .el-form-item .el-form-item__label{
  display: none;
}
</style>
