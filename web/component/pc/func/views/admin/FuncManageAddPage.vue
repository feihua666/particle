<script setup name="FuncManageAddPage" lang="ts">
/**
 * 功能菜单管理添加页面
 */
import {reactive, ref} from 'vue'
import {create as funcCreateApi} from "../../api/admin/funcAdminApi"
import {useAddPageFormItems} from "../../compnents/admin/funcManage";
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  parentId: {
    type: String
  },
  // 编码，添加时使用，主要为了低代码添加
  code: {
    type: String
  },
  // 名称
  name: {
    type: String
  },
  // 功能分组
  funcGroupId: {
    type: String
  },
  // 图标，一般为element ui 的图标字符串如：Avatar
  icon: {
    type: String
  },
  // 类型字典id，如：页面、按钮
  typeDictId: {
    type: String
  },
  // 路由
  url: {
    type: String
  },
  // 权限码，多个以逗号分隔
  permissions: {
    type: String
  },
  // 归属组件
  componentOf: {
    type: String
  },
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    parentId: props.parentId,
    code: props.code,
    name: props.name,
    funcGroupId: props.funcGroupId,
    icon: props.icon,
    typeDictId: props.typeDictId,
    url: props.url,
    permissions: props.permissions,
    componentOf: props.componentOf,
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    useAddPageFormItems({formData: reactiveData.formData})
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认添加',
  permission: 'admin:web:func:create'
})
// 提交按钮
const submitMethod = () => {
  return funcCreateApi
}
// 成功提示语
const submitMethodSuccess = () => {
  return '添加成功，请刷新数据查看'
}
</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="80"
          :method="submitMethod()"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          inline
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>