<script setup name="UserinfoCard" lang="ts">
/**
 * 用户信息
 * 主要用来 个人中心 和 账号设置 左上角头像信息
 */
const props = defineProps({
  // 当前登录用户昵称，如果传了就使用该值
  nickname: {
    type: String,
  },
  // 当前登录用户头像，如果传了就使用该值
  avatar: {
    type: String,
  },

})
</script>

<template>
  <el-card shadow="hover" style="width: 200px;border: none;">

    <div class="pt-flex-direction-c pt-flex-align-cross-center">
      <el-avatar :src="avatar" size="large">
        {{ nickname ? nickname.substr(0,1) : '无' }}
      </el-avatar>
      <div style="padding: 14px">
        <span>{{ nickname}}</span>
      </div>
    </div>
  </el-card>
</template>

<style scoped>

</style>