<script setup name="CmsChannelManageUrlPage" lang="ts">
/**
 * 栏目地址管理页面
 */
import {reactive, ref} from 'vue'
import { indexItemsUrl} from "../../api/admin/cmsChannelAdminApi"


const tableRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  cmsChannelId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询
  form: {
    id: props.cmsChannelId
  },
  formComps: [],
  tableColumns: [
    {
      prop: 'typeName',
      label: '地址类型',
    },
    {
      prop: 'indexUrl',
      label: '地址',
    },

  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '刷新',
  loading: false,
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 数据查询
const doIndexItemsUrl = () => {
  return indexItemsUrl({...reactiveData.form})
}
const tablePaginationProps = {
  permission: submitAttrs.value.permission
}
// 表格操作按钮
const getTableRowButtons = ({row, column, $index}) => {
  if($index < 0){
    return []
  }
    let dt = {__dt: row.name}
  let idData = {id: row.id,...dt}

  let tableRowButtons = [
    {
      txt: '跳转',
      text: true,
      type: 'primary',
      // 跳转操作
      method(){
        window.open(row.indexUrl)
      }
    },
  ]

  return tableRowButtons
}
</script>
<template>
  <!-- 查询表单 -->
  <PtForm :form="reactiveData.form"
          :method="submitMethod"
          defaultButtonsShow="submit"
          :submitAttrs="submitAttrs"
          :showButtonItem="true"
          inline
          :comps="reactiveData.formComps">
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doIndexItemsUrl"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"

           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="100">
        <template #default="{row, column, $index}">
          <PtButtonGroup :options="getTableRowButtons({row, column, $index})" :dropdownTriggerButtonOptions="{  text: true,buttonText: '更多'}">
          </PtButtonGroup>
        </template>
      </el-table-column>
    </template>
  </PtTable>
</template>


<style scoped>

</style>
