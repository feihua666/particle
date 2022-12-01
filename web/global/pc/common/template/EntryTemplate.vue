<script setup name="EntryTemplate">
/**
 * 入口页面
 * 主要应用场景是完成用户进入首页或登录页面的一个过渡页面，就是说用户输入浏览器地址，会先进到这个页面，判断用户是否登录，如果登录进入首页，如果未登录，进入登录页面
 */
import {getCurrentInstance,onMounted} from 'vue'
import {promiseBoolValue} from "../../../common/tools/PromiseTools.js"
import {useLoginUserStore} from '../../../common/security/loginUserStore.js'

const loginUserStore = useLoginUserStore()
const { appContext } = getCurrentInstance()
// 路由
const router = appContext.config.globalProperties.$router
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 登录页面的路由
  loginPageRoute: {
    default: '/login'
  },
  // index页面的路由
  indexPageRoute: {
    default: '/index'
  },
  // 判断用户是否登录的方法，可以返回promise，如果promise被reject则失败
  // 不传默认使用 loginUserStore
  hasLoginMethod: {
    type: Function
  },
  // 提示方法，在页面加载后提拉loading的方法，如果不传，默认显示文本
  hitMethod: {
    type: Function
  }
})

onMounted(() => {
  if(props.hitMethod){
    props.hitMethod()
  }
  if(router){
    let result = loginUserStore.hasLogin
    if(props.hasLoginMethod){
      result = promiseBoolValue(props.hasLoginMethod)
    }
    // 根据登录成功或失败跳转到不同的路由
    if (result) {
      router.replace(props.indexPageRoute)
    }else {
      router.replace(props.loginPageRoute)
    }
  }

})
</script>
<template>
<div v-if="!hitMethod" class="pt-hit-default">页面跳转中，请稍候...</div>
</template>

<style scoped>
.pt-hit-default{
  text-align: center;
}
</style>