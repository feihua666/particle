<script setup name="Logo">
// 声明属性
defineProps({
  // logo标题
  text: {
    type: String,
    default: ' particle'
  }})
</script>

<template>
<div class="logo pt-flex-center-all">
  <slot>
    <img src="@/assets/logo.png"/> <span>{{text}}</span>
  </slot>
</div>
</template>

<style scoped>
.logo img{
  --logo-height: 2rem;
  --logo-width: auto;
  height: var(--logo-height);
  width: var(--logo-width,auto);
  margin-right: .7rem;
}
.logo span{
  font-size: 2rem;
}
</style>