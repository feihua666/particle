<script setup name="Logo">
import {ref} from "vue"
import defaultImagSrc from '@/assets/logo.png'
// 声明属性
defineProps({
      // logo标题
      text: {
        type: String,
        default: ' particle'
      },
      // 是否展示 logo标题
      showText: {
        type: Boolean,
        default: true
      },
      imgSrc: {
        type: String,
        default: defaultImagSrc
      },
      imgAttr: {
        type: Object
      },
      textAttr: {
        type: Object
      }
    }
)
const logoImg = ref(null)
const defaultImg = () =>{
  logoImg.value.src = defaultImagSrc

}
</script>

<template>
  <div class="logo pt-flex-center-all">
    <slot>
      <img ref="logoImg" class="logo-img" :src="imgSrc" v-bind="imgAttr" :onerror="defaultImg"/> <span class="logo-text" v-if="text" v-show="showText" v-bind="textAttr">{{text}}</span>
    </slot>
  </div>
</template>

<style scoped>
.logo .logo-img{
  --logo-height: 2rem;
  --logo-width: auto;
  height: var(--logo-height);
  width: var(--logo-width,auto);

}
.logo .logo-text{
  font-size: 2rem;
  margin-left: .7rem;
  transition: display .3s ease
}
</style>