<script setup name="UserinfoDropdown" lang="ts">
/**
 * 当前登录用户相关操作
 * 主要展示头像和昵称且可直接操作退出登录等
 */
import {computed} from "vue"
import {logout} from "../../api/userLoginApi"
import {useLoginUserStore} from "../../../../../global/common/security/loginUserStore"
import {useRoute, useRouter} from 'vue-router'

const router = useRouter()
const route = useRoute()

const loginUserStore = useLoginUserStore()
const props = defineProps({
  // 用户信息加载状态
  userinfoLoading: {
    type: Boolean,
    default: false
  },
  // 当前登录用户昵称，如果传了就使用该值
  nickname: {
    type: String,
  },
  // 当前登录用户头像，如果传了就使用该值
  avatar: {
    type: String,
  },
  dropdownMethod: {
    type: Function
  }
})

const nickname = computed(() => {
  if (props.nickname) {
    return props.nickname
  }
  let r = ''
  let loginUser = loginUserStore.loginUser
  if (loginUser) {
    r = loginUser.nickname || loginUser.username
  }
  return r
})
const avatar = computed(() => {
  if (props.avatar) {
    return props.avatar
  }
  let r = ''
  let loginUser = loginUserStore.avatar
  if (loginUser) {
    r = loginUser.avatar
  }
  return r
})
const handleUserinfoCommand = (command) => {
  if(props.dropdownMethod){
    let dropdownMethodResult = props.dropdownMethod(command)
    if(dropdownMethodResult !== false){
      return dropdownMethodResult
    }
  }

  switch (command) {
    case 'userinfo': {
      router.push('/base/user/userinfo/current')
      break
    }
    case 'updatePwd': {

      router.push('/base/user/updatePwd')
      break
    }
    case 'userinfoEdit': {
      router.push('/base/user/userinfoEdit/current')
      break
    }
    case 'logout': {
      // jwt退出不需要调用接口，废弃token就可以了
      logout()
      loginUserStore.changeHasLogin(false)
      // 如果不是向前端
      router.replace('/login')
      break
    }
  }
}
</script>

<template>
  <el-dropdown class=" g-flex-align-cross-center" @command="handleUserinfoCommand"
               v-loading="userinfoLoading"
               element-loading-spinner="el-icon-loading">
    <div class="pt-pointer pt-flex-align-cross-center" style="padding: 0 5px;">
      <el-avatar :src="avatar">
        {{ nickname ? nickname.substr(0,1) : '无' }}
      </el-avatar>
      <span style="margin-left: 5px;"> {{ nickname }}</span>
    </div>

    <template #dropdown>
      <el-dropdown-menu>
        <!--<el-dropdown-item command="userinfo">个人信息</el-dropdown-item>
        <el-dropdown-item  command="updatePwd">修改密码</el-dropdown-item>
        <el-dropdown-item command="userinfoEdit">修改信息</el-dropdown-item>-->
        <el-dropdown-item command="logout">退出登陆</el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
</template>

<style scoped>

</style>