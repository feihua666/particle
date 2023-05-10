<script setup name="UploadSingleImage">
/**
 * 自定义封装 upload 上传单个图片功能
 * 封装理由：1. 结合后端使用时支持权限控制
 *          2. 提供了默认的上传表示形式
 *          3. 默认自带上传 dataLoading 功能效果
 */
import {reactive, computed, watch} from 'vue'
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
    default: '上传图片'
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
})// 事件
const emit = defineEmits([
  // 用来更新 modelValue
  emitDataModelEvent.updateModelValue,
])

watch(()=> props.modelValue,(url)=>{
  reactiveData.currentModelValue = url
})

const handleSuccess = (response, uploadFile, uploadFiles) => {


  let url = response[propsOptions.value.url]
  reactiveData.currentModelValue = url
  emit(emitDataModelEvent.updateModelValue,url)
}

</script>
<template>
  <PtUpload class="single-image-uploader"
      ref="UploadSingleImageRef"
      :show-file-list="false"
      :noPermissionSimpleText="noPermissionSimpleText"
      @uploading="(uploading) => {reactiveData.uploading = uploading}"
      :on-success="handleSuccess">
    <div  v-loading="reactiveData.uploading">
      <img v-if="reactiveData.currentModelValue" :src="getPreviewUrl(reactiveData.currentModelValue)" class="single-image-uploader-img" />
      <el-icon v-else class="single-image-uploader-icon"><Plus /></el-icon>
    </div>

  </PtUpload>
</template>
<style scoped>
.single-image-uploader .single-image-uploader-img {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

  <style>
  .single-image-uploader .el-upload {
    border: 1px dashed var(--el-border-color);
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: var(--el-transition-duration-fast);
  }

.single-image-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.single-image-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>