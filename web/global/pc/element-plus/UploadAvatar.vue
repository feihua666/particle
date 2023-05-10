<script setup name="UploadAvatar">
/**
 * 自定义封装 upload 头像上传
 * 封装理由：1. 结合后端使用时支持权限控制
 *          2. 提供了默认的上传表示形式
 *          3. 默认自带上传 dataLoading 功能效果
 */
import {reactive,computed,watch} from 'vue'
import {emitDataModelEvent,} from './dataModel'
import PtUpload from './Upload.vue'
import {getPreviewUrl} from "../common/axios/axiosRequest";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 值绑定
  modelValue: String,
  // 配置属性
  props: {
    type: Object,
    default: () => ({})
  },
  // 没有权限的提示,拼接没有权限提示语句，如：您没有 + noPermissionSimpleText + 权限
  noPermissionSimpleText: {
    type: String,
    default: '上传头像'
  }
})
// 属性
const reactiveData = reactive({
  currentModelValue: props.modelValue,
  uploading: false
})
// propsOptions
const propsOptions = computed(() => {
  let defaultProps = {
    // 取 response 值的 url 属性名
    url: 'absoluteHttpUrl'
  }
  return Object.assign(defaultProps, props.props)
})
watch(()=>props.modelValue,(val)=>{
  reactiveData.currentModelValue = val
})
// 事件
const emit = defineEmits([
  // 用来更新 modelValue
  emitDataModelEvent.updateModelValue,
])

const handleSuccess = (response, uploadFile, uploadFiles) => {

  let url = response[propsOptions.value.url]
  reactiveData.currentModelValue = url
  emit(emitDataModelEvent.updateModelValue,url)
}

</script>
<template>
  <PtUpload
      ref="uploadAvatarRef"
      :show-file-list="false"
      :noPermissionSimpleText="noPermissionSimpleText"
      @uploading="(uploading) => {reactiveData.uploading = uploading}"
      :on-success="handleSuccess">
    <el-avatar   v-loading="reactiveData.uploading" :size="60" :src="getPreviewUrl(reactiveData.currentModelValue)">
      {{reactiveData.currentModelValue ? '加载失败':'请上传'}}
    </el-avatar>

  </PtUpload>
</template>
<style scoped>

</style>