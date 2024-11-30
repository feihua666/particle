<script setup name="UserinfoDropdown" lang="ts">
/**
 * 当前登录用户相关操作
 * 主要展示头像和昵称且可直接操作退出登录等
 */
import {computed, ref} from "vue"
import {logout} from "../../api/userLoginApi"
import {useLoginUserStore} from "../../../../../global/common/security/loginUserStore"
import {useRoute, useRouter} from 'vue-router'
import PtUserinfoCenter from './usercenter/UserinfoCenter.vue'
import UserAccountSetting from './useraccountSetting/UserAccountSetting.vue'

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
  let loginUser = loginUserStore.loginUser
  if (loginUser) {
    r = loginUser.avatar
  }
  return r
})
const userinfoCenterDialogVisible = ref(false)
const userAccountSettingDialogVisible = ref(false)
const handleUserinfoCommand = (command) => {
  if(props.dropdownMethod){
    let dropdownMethodResult = props.dropdownMethod(command)
    if(dropdownMethodResult !== false){
      return dropdownMethodResult
    }
  }

  switch (command) {
    case 'userinfoCenter': {
      userinfoCenterDialogVisible.value = true
      break
    }
    case 'userinfoAccountSetting': {
      userAccountSettingDialogVisible.value = true
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
               v-bind="$attrs"
               trigger="click"
               size="large"
               element-loading-spinner="el-icon-loading">
    <div class="pt-pointer pt-flex-align-cross-center" style="padding: 0 5px;">
      <span style="margin-right: 0.8rem;"> {{ nickname }}</span>
      <el-avatar :src="avatar">
        {{ nickname ? nickname.substr(0,1) : '无' }}
      </el-avatar>
    </div>

    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item command="userinfoCenter" :disabled="!loginUserStore.hasLogin" icon="User">个人中心</el-dropdown-item>
        <el-dropdown-item command="userinfoAccountSetting" :disabled="!loginUserStore.hasLogin" icon="Setting">账号设置</el-dropdown-item>
        <!-- <el-dropdown-item  command="updatePwd">修改密码</el-dropdown-item>
        <el-dropdown-item command="userinfoEdit">修改信息</el-dropdown-item>-->
        <el-dropdown-item command="logout" icon="SwitchButton">退出登陆</el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>

  <el-dialog class="pt-dialog-tab-use-scroll" v-model="userinfoCenterDialogVisible" title="个人中心" width="90%" top="5vh" style="height:90vh" append-to-body destroy-on-close>

    <PtUserinfoCenter class="pt-dialog-tab"></PtUserinfoCenter>

  </el-dialog>

  <el-dialog class="pt-dialog-tab-use-scroll" v-model="userAccountSettingDialogVisible" title="账号设置" width="90%" top="5vh" style="height:90vh" append-to-body destroy-on-close>

    <UserAccountSetting class="pt-dialog-tab"></UserAccountSetting>

  </el-dialog>
</template>

<style scoped>

</style>

<style>
/* 数据太多时，添加滚动条 */
.pt-dialog-tab-use-scroll .el-dialog__body{
  height: 75vh !important;
}
.pt-dialog-tab-use-scroll .pt-dialog-tab,.pt-dialog-tab-use-scroll .el-tabs,.pt-dialog-tab-use-scroll .el-tabs__content{
  height: 100% !important;
}
.pt-dialog-tab-use-scroll .el-tabs__content{
  overflow-y: scroll !important;
}
</style>
