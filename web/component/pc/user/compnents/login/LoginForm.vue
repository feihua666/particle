<script setup name="LoginForm"  lang="ts">
import {getCurrentInstance,reactive ,ref} from 'vue'
import {login} from '../../api/userLoginApi'
import {useLoginUserStore} from '../../../../../global/common/security/loginUserStore'
import {isString} from '../../../../../global/common/tools/StringTools'
import {isFunction} from '../../../../../global/common/tools/FunctionTools'

const { appContext } = getCurrentInstance()
// 路由
const router = appContext.config.globalProperties.$router
const loginUserStore = useLoginUserStore()

interface Props {
  // 登录成功后，执行，如果为 String，就会被当作路由跳转 replace
  loginSuccess?: Function | string
}
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps<Props>()

// 属性
const reactiveData = reactive({
  form: {},
  formComps: [
    {
      field: {
        name: 'username'
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '账 号',
          required: true
        },
        compProps: {
          clearable: true,
          placeholder: '账号'
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
          label: '密 码',
          required: true
        },
        compProps: {
          type: 'password',
          clearable: true,
          showPassword: true,
          placeholder: '密码'
        }
      }
    }
  ]
})
// 提交按钮属性
const submitAttrs = ref({
  buttonText: '登录',
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
  //  catch 一下异常，否则控制台打印一大堆
  })
}
</script>
<template>
  <PtForm :form="reactiveData.form"
          :method="login"
          defaultButtonsShow="submit"
          :submitAttrs="submitAttrs"
          class="login-form"
          :layout="1"
          size="default"
          @methodResult="loginResult"
          :comps="reactiveData.formComps">
  </PtForm>
</template>

<style scoped>
.login-form{
  width: 25rem !important;
  padding: 2rem;
  background: rgba(255, 255, 255, 0.5) none repeat scroll 0 0;
  border: 1px solid rgba(255, 255, 255, 0.3);
  border-radius: 3px;
  box-shadow: 0 3px 0 rgba(12, 12, 12, 0.03);
}
</style>