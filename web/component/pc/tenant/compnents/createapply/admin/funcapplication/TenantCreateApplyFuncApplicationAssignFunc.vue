<script setup name="TenantCreateApplyFuncApplicationAssignFunc" lang="ts">
/**
 * 功能应用分配功能,主要用于创建租户申请时可选择应用下的功能
 */
import {reactive ,ref} from 'vue'
import {list as funcListApi} from "../../../../../func/api/admin/funcAdminApi";
import {
  remoteSelectFuncApplicationCompItem,
  remoteSelectFuncApplicationProps
} from "../../../../../func/compnents/application/funcApplicationCompItem";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...remoteSelectFuncApplicationProps,
  // 可用的功能，限制在该应用下
  limitFuncApplicationId: {
    type: String
  },
  // 选中的功能id
  checkedFuncIds:{
    type: Array,
    default: ()=>[]
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    [
      remoteSelectFuncApplicationCompItem({props,required: true}),
      {
        field: {
          name: 'funcIds',
          value: []
        },
        element: {
          comp: 'PtTree',
          formItemProps: {
            label: '功能',
            required: true,
          },
          compProps: ({form})=> {
            return {
              // 加载初始化选中数据
              dataInitMethod: () => {
                // 空函数不查询
                return {data: props.checkedFuncIds}
              },
              // 可用数据列表
              dataMethod: ()=> {
                return funcListApi({funcApplicationId: props.limitFuncApplicationId})
              },
              dataMethodResultHandleConvertToTree: true,
              showCheckbox: true
            }
          }
        }
      },
    ]
)


defineExpose({
  form: reactiveData.form
})
</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="80"
          defaultButtonsShow=""
          inline
          :layout="1"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>