
<script setup name="Frame" lang="ts">

/**
 * iframe封装
 */
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
import {onMounted, ref, watch} from "@nm/vue";
let iframeFrame = ref()

const props = defineProps({
  // 页面的地址
  url: {
    type: String,
    required: true
  },
  // 是否计算高度，计算高度是为了让内容的高度撇开iframe，全iframe的高度和内容一样高
  computeHeight:{
    type: Boolean,
    default: false
  }
})
// 属性
const reactiveData = reactive({
  loading: false,
  localUrl: props.url,
  heightStyle: ''
})
// 事件
const emit = defineEmits(['loading','mClick'])

onMounted(()=>{
  initLoading(reactiveData.localUrl)
})
watch(()=>props.url,(url)=>{
  reactiveData.localUrl = url
  initLoading(url)
})
const initLoading = (url)=>{
  if(!url){
    return
  }
  reactiveData.loading = true
  emit('loading',reactiveData.loading)
  if(iframeFrame.value.attachEvent){ //IE
    iframeFrame.value.attachEvent("onreadystatechange", function() {
      //此事件在内容没有被载入时候也会被触发，所以我们要判断状态
      //有时候会比较怪异 readyState状态会跳过 complete 所以我们loaded状态也要判断
      if (iframeFrame.value.readyState === "complete" || iframeFrame.value.readyState == "loaded") {
        //代码能执行到这里说明已经载入成功完毕了
        //要清除掉事件
        iframeFrame.value.detachEvent("onreadystatechange", arguments.callee);
        //这里是回调函数
        reactiveData.loading = false
        emit('loading',reactiveData.loading)
        trySetIframeHeight(iframeFrame.value,10)
      }
    });
  }else{
    iframeFrame.value.addEventListener("load", function() {
      //代码能执行到这里说明已经载入成功完毕了
      this.removeEventListener("load", arguments.call, false);
      //这里是回调函数
      reactiveData.loading = false
      emit('loading',reactiveData.loading)
      trySetIframeHeight(iframeFrame.value,10)
    }, false);
  }
}
const refresh = ()=>{
  if(!reactiveData.localUrl){
    return
  }
  if(reactiveData.localUrl.indexOf('?') >= 0){
    reactiveData.localUrl += '&nocaching=' + new Date().getTime()
  }else {
    reactiveData.localUrl += '?nocaching=' + new Date().getTime()
  }
  initLoading(reactiveData.localUrl)
}
const setIframeHeight = (iframe) =>{
  if (iframe) {
    let iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
    if (iframeWin.document.body) {
      iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
      reactiveData.heightStyle = 'height:' + (iframe.height) + 'px'
    }
  }
}
const trySetIframeHeight = (iframe,times) =>{
  if (!reactiveData.computeHeight) {
    return
  }
  setIframeHeight(iframe)
  if(times > 0){
    setTimeout(()=>{
      trySetIframeHeight(iframe,times - 1)
    },1000)
  }

}

</script>
<template>
  <iframe ref="iframeFrame" :src="localUrl" class="st-width-100-pc st-border-none" :class="{'st-height-100-pc': !computeHeight}" :style="reactiveData.heightStyle"></iframe>
</template>

<style scoped>

</style>