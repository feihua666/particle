<script setup name="DataScopeCustomDataRelManageCustomDataAssignDataScopePage" lang="ts">
/**
 * 数据范围分配自定义数据页面
 */
import {reactive ,ref} from 'vue'
import {ElMessage} from 'element-plus'
import {queryCustomDataIdsByDataScopeId, dataScopeAssignCustomData as dataScopeAssignCustomDataApi} from "../../api/admin/dataScopeCustomDataRelAdminApi"
import {useRemoteSelectDataScopeCompItem, remoteSelectDataScopeProps} from "../../components/dataconstraintCompItem";
import {detail as dataObjectDetailApi} from "../../api/admin/dataObjectAdminApi";
import {customData} from "../../api/admin/dataScopeAdminApi";

let alert = (message,type='success')=>{
  ElMessage({
    showClose: true,
    message: message,
    type: type,
    showIcon: true,
    grouping: true
  })
}
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...remoteSelectDataScopeProps,
  // 数据对象id
  dataObjectId: {
    type: String
  },
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    isLazyLoad: false
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    []
)

const ready = ref(false)
dataObjectDetailApi({id: props.dataObjectId}).then((res) => {

  let dataObjectData = res.data.data
  if (!dataObjectData.customDataUrl) {
    alert('数据对象未配置自定义数据url', 'error')
  }
  let customDataConfigJsonStr = res.data.data.customDataConfigJson
  let isCustomDataLazy = res.data.data.isCustomDataLazy
  // 目前可用值 tree_list、table_list
  let customDataTypeDictValue = res.data.data.customDataTypeDictValue
  let customDataTypeDictName = res.data.data.customDataTypeDictName
  if (customDataTypeDictValue && customDataTypeDictValue != 'tree_list') {
    alert(`数据对象自定义数据交互方式为 ${customDataTypeDictName},暂不支持，已自动适配为 树形列表`, 'warn')
  }

  reactiveData.form.isLazyLoad = isCustomDataLazy;


  let treeProps = null
  if (customDataConfigJsonStr) {
    let customDataConfigJson =JSON.parse(customDataConfigJsonStr)
    treeProps = customDataConfigJson.treeProps
  }
  let formCompsResult = [
    useRemoteSelectDataScopeCompItem({props,required: true}),
    {
      field: {
        name: 'checkedDataIds',
        value: []
      },
      element: {
        comp: 'PtTree',
        formItemProps: {
          label: '自定义数据',
          required: true,
        },
        compProps: ({form}) => {
          return {
            // 加载初始化选中数据
            dataInitMethod: ({param}) => {
              let dataScopeId = param.dataScopeId
              if (dataScopeId) {
                return queryCustomDataIdsByDataScopeId({id: dataScopeId})
              }
              // 空函数不查询
              return {data: []}
            },
            // dataInitMethod 参数
            dataInitMethodParam: {dataScopeId: form.dataScopeId},
            // 可用数据列表
            dataMethod: () => customData(dataObjectData.customDataUrl,{}),
            dataMethodResultHandleConvertToTree: true,
            showCheckbox: true,
            // 下拉显示昵称
            props: treeProps
          }
        }
      }
    },
  ];
  formComps.value = formCompsResult
  ready.value = true
})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认',
  permission: 'admin:web:dataScopeCustomDataRel:dataScopeAssignCustomData',
})
// 提交按钮
const submitMethod = () => {
  return dataScopeAssignCustomDataApi
}
// 成功提示语
const submitMethodSuccess = () => {
  return '分配成功，请刷新数据查看'
}

</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form" v-if="ready"
          :formData="reactiveData.formData"
          labelWidth="80"
          :method="submitMethod()"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :layout="1"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>