<script setup name="Upload">
/**
 * 自定义封装 upload 上传功能
 * 封装理由：1. 结合后端使用时支持权限控制
 *          2. 提供了默认的上传表示形式
 *          3. 默认自带上传 dataLoading 功能效果
 */
import {reactive ,inject,ref,watch,computed} from 'vue'
import PtButton from './Button.vue'
import { ElMessage } from 'element-plus'
import {permissionProps,hasPermissionConfig} from './permission'
import {disabledProps,disabledConfig} from './disabled'
import {reactiveDataModelData,emitDataModelEvent,updateDataModelValueEventHandle,changeDataModelValueEventHandle} from './dataModel'
import {getUploadUrl, getUrl} from "../common/axios/axiosRequest";
const uploadRef = ref(null)
const dialogImageUrl = ref('')
const dialogVisible = ref(false)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 上传地址，如果不传默认按 axiosRequest.ts 中的get url
  action: String,
  // 值绑定
  modelValue: [Array],
  // 提示文本，也可以通过 tip 插槽覆盖该行为
  tipTxt: {
    type: String
  },
  // 在拖拽时显示的文字，默认为 '将文件拖到此处，或点击上传'
  dragTip: {
    type: String
  },
  // 文件列表的类型 'text' | 'picture' | 'picture-card'
  listType: {
    type: String,
    default: 'text'
  },
  // 启动拖拽
  drag: {
    type: Boolean,
    default: false
  },
  // 默认自动上传
  autoUpload: {
    type: Boolean,
    default: true
  },
  // 禁用相关属性
  ...disabledProps,
  // 权限相关
  ...permissionProps,
  // 鼠标 hover 提示语
  title: {
    type: String
  },
  // 上传文件之前的钩子，参数为上传的文件， 若返回false或者返回 Promise 且被 reject，则停止上传。
  beforeUpload: {
    type: Function
  },
  // 删除文件之前的钩子，参数为上传的文件和文件列表， 若返回 false 或者返回 Promise 且被 reject，则停止删除。
  beforeRemove: {
    type: Function
  },
  // 文件状态改变时的钩子，添加文件、上传成功和上传失败时都会被调用
  onChange: {
    type: Function
  },
  // 点击文件列表中已上传的文件时的钩子
  onPreview: {
    type: Function
  },
  // 文件上传成功时的钩子
  onSuccess: {
    type: Function
  },
  // on-error
  onError: {
    type: Function
  }
})
// 属性
const reactiveData = reactive({
  ...reactiveDataModelData(props),
  // 上传中，加载状态
  uploading: false
})
// 计算属性
// 这里和 props.loading 重名了，但在模板是使用 loading 变量是这个值，也就是说这里会覆盖在模板中的值
const action = computed(() => {
  return props.action || getUploadUrl()
})

const injectPermissions = inject('permissions', [])

// 计算属性

// 是否有权限
const hasPermission = hasPermissionConfig({
  props,
  injectPermissions,
  noPermissionSimpleText: `「此」文件上传或删除`
})
// 是否禁用
const hasDisabled = disabledConfig({props,hasPermission})
// 侦听
watch(
    () => props.modelValue,
    (val) => {
      reactiveData.oldModelValue = val
      reactiveData.currentModelValue = val
    }
)
// 侦听
watch(
    () => reactiveData.uploading,
    (uploading,oldUploading) => {
      emit('uploading',uploading)
    }
)

// 事件
const emit = defineEmits([
  // 用来更新 modelValue
  emitDataModelEvent.updateModelValue,
  emitDataModelEvent.change,
  'on-change',
  'uploading',
])
// 方法
// 值更新事件
const updateModelValueEvent = updateDataModelValueEventHandle({reactiveData,hasPermission,emit})
// 值改变事件
const changeModelValueEvent = changeDataModelValueEventHandle({reactiveData,hasPermission,emit,eventName: 'on-change'})

// 本来想的是上传之前判断权限并提示，但如果这里返回 经查看源码，发现会调用 onRemove 事件，导出提示重复，所以这里直接判断权限，加了 canRemove 作为控制
// 查看 element plus 源码发现，就算是 BeforeUpload 返回 false，阻止了上传仍然会调用 onChange 和 onRemove 事件，也会调用 update:file-list 事件，需要综合考虑
// 调用顺序 customOnChangeHandle > customBeforeUpload > customBeforeRemove
const canRemove = ref(false)
const customBeforeUpload = () => {
  if (hasPermission.value.enable && !hasPermission.value.hasPm) {
    canRemove.value = true
    return false
  }
  if (props.beforeUpload) {
    let r =  props.beforeUpload()
    if (r == false) {
      // 只有在未阻止上传时，启用 uploading
      reactiveData.uploading = true
    }
    return r
  }else {
    // 只有在未阻止上传时，启用 uploading
    reactiveData.uploading = true
    return true
  }
}
const customOnChangeHandle = (uploadFile, uploadFiles)=>{

  if (changeModelValueEvent(uploadFile, uploadFiles)) {
    canRemove.value = true
    // 有可能不存在删除失败，加一个 try
    if(!props.autoUpload){
      uploadRef.value?.handleRemove(uploadFile)
    }

    return
  }
  if (props.onChange) {
    props.onChange(uploadFile, uploadFiles)
  }
}
const customBeforeRemove = () => {

  if (hasPermission.value.enable && !hasPermission.value.hasPm) {

    if (canRemove.value) {
      canRemove.value = false
      return true
    }
    // 主要是为了提示没有权限
    updateModelValueEvent(reactiveData.currentModelValue)
    return false
  }
  if (props.beforeRemove) {
    return props.beforeRemove()
  }else {
    return true
  }
}
const customOnSuccessHandle = (response, uploadFile, uploadFiles) => {
  reactiveData.uploading = false
  if (props.onSuccess) {
    props.onSuccess(response, uploadFile, uploadFiles)
  }
}
const customOnErrorHandle = (error, uploadFile, uploadFiles) => {
  reactiveData.uploading = false
  if (props.onError) {
    props.onError(error, uploadFile, uploadFiles)
  }else {
    ElMessage({
      showClose: true,
      message: `上传失败 ${uploadFile.name} ${error.message}`,
      type: 'error',
      showIcon: true,
      grouping: true
    })
  }
}
// 预留手动上传方法，需要设置 auto-upload 为 false
const submitUpload = () => {
  uploadRef.value?.submit()
}
const customOnPreview = (uploadFile) => {
  if (props.onPreview) {
    props.onPreview(uploadFile)
    return
  }
  if (props.listType == 'picture-card') {
    handlePictureCardPreview(uploadFile)
  }
}
const handlePictureCardPreview = (uploadFile) => {
  dialogImageUrl.value = uploadFile.url
  dialogVisible.value = true
}
</script>
<template>
  <!--      :on-remove="(uploadFile, uploadFiles) => $emit('on-remove', uploadFile, uploadFiles)"-->
  <!--      :on-success="(response, uploadFile, uploadFiles) => $emit('on-success', response, uploadFile, uploadFiles)"-->
  <!--      :on-error="(error, uploadFile, uploadFiles) => $emit('on-error', error, uploadFile, uploadFiles)"-->
  <!--      :on-progress="(event, uploadFile, uploadFiles) => $emit('on-progress', event, uploadFile, uploadFiles)"-->
  <!--      :on-exceed="(files,uploadFiles) => $emit('on-exceed', files,uploadFiles)"-->
  <el-upload
      v-if="hasPermission.render"
      ref="uploadRef"
      :action="action"
      v-model:file-list="reactiveData.currentModelValue"
      v-bind="$attrs"
      :title="hasDisabled.disabledReason || title"
      :listType="listType"
      :autoUpload="autoUpload"
      :drag="drag"
      :disabled="hasDisabled.disabled"
      :on-success="customOnSuccessHandle"
      :on-error="customOnErrorHandle"
      :on-preview="customOnPreview"

      :on-change="customOnChangeHandle"
      :before-upload="customBeforeUpload"
      :before-remove="customBeforeRemove"
      @update:file-list="updateModelValueEvent">
    <template v-if="$slots.default">
      <slot :hasPermission="hasPermission">
      </slot>
    </template>

    <template #trigger v-if="!$slots.trigger && !$slots.default && drag == false  && listType !== 'picture-card'">
      <PtButton v-if="!drag" type="primary"
                :loading="reactiveData.uploading"
                :permission="permission"
                :permissions="permissions"
                :noPermissionView="noPermissionView"
                :noPermissionFn="noPermissionFn" :noPermissionText="hasPermission.noPermissionText">点击选择</PtButton>
    </template>
    <template v-if="!$slots.trigger && !$slots.default && autoUpload == false && drag == false  && listType !== 'picture-card'">
      <PtButton type="success"
                :loading="reactiveData.uploading"
                class="pt-upload-submit"
                @click="submitUpload"
                :permission="permission"
                :permissions="permissions"
                :noPermissionView="noPermissionView"
                :noPermissionFn="noPermissionFn" :noPermissionText="hasPermission.noPermissionText">上传</PtButton>
    </template>
    <template #default v-if="!$slots.trigger && !$slots.default && drag && listType !== 'picture-card'">
      <el-icon class="el-icon--upload"><upload-filled /></el-icon>
      <div class="el-upload__text" v-if="dragTip" v-html="dragTip">
      </div>
      <div class="el-upload__text" v-else>
        Drop file here or <em>click to upload</em>
      </div>
    </template>
    <template #default v-if="!$slots.trigger && !$slots.default && listType == 'picture-card'">
      <el-icon  :class="{'is-loading': reactiveData.uploading}">
        <Loading v-if="reactiveData.uploading"/>
        <Plus v-else />
      </el-icon>
    </template>

    <!-- 不添加 v-if="$slots.trigger" 判断不会使用默认插槽触发，选择文件动作-->
    <template #trigger v-if="$slots.trigger">
      <slot name="trigger" :hasPermission="hasPermission"></slot>
    </template>
    <template #tip v-if="$slots.tip">
      <slot name="tip" :hasPermission="hasPermission">
      </slot>
    </template>
    <template  #tip v-if="!$slots.tip && tipTxt">
      <div class="el-upload__tip">
        {{tipTxt}}
      </div>
    </template>
    <template v-if="$slots.file" #file="{ file }">
      <slot name="file" :hasPermission="hasPermission" :file="file" />
    </template>

  </el-upload>
  <el-dialog v-model="dialogVisible">
    <img style="width:100%;" :src="dialogImageUrl" alt="Preview Image" />
  </el-dialog>
</template>
<style scoped>
.pt-upload-submit{
  margin-left: 0.75rem;
  /* 没有和点击选择对齐，调整一下 */
  vertical-align: top;
}
</style>