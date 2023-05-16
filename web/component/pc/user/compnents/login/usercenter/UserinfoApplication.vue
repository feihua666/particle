<script setup name="UserinfoApplication" lang="ts">
import {tenantFuncApplicationColumns} from "../../../../tenant/compnents/tenantCompItem";
import {listToTree} from "../../../../../../global/common/tools/ArrayTools";
import {getFuncApplicationList} from "../../../../func/api/funcLoginApi";

const props = defineProps({

})
const dropdownTriggerButtonOptions = {
  text: true,
  buttonText: '更多',
}
const tableColumns = tenantFuncApplicationColumns
// 表格操作按钮
const getTableRowButtons = ({row, column, $index}) => {
  if($index < 0){
    return []
  }
  let idData = {id: row.id}
  let tableRowButtons = [

  ]

  return tableRowButtons
}

const listToTreeMethod = (data)=>{
  return listToTree(data,null,'funcApplicationId','parentFuncApplicationId')
}
</script>
<template>
  <PtTable ref="tableRef"
           default-expand-all
           :columns="tableColumns"
           :dataMethodResultHandleConvertToTree="true"
           :dataMethodResultHandleListToTreeMethod="listToTreeMethod"
           :dataMethod="getFuncApplicationList"
  >

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="220">
        <template #default="{row, column, $index}">
          <PtButtonGroup :options="getTableRowButtons({row, column, $index})" :dropdownTriggerButtonOptions="dropdownTriggerButtonOptions">
          </PtButtonGroup>
        </template>
      </el-table-column>
    </template>
  </PtTable>
</template>


<style scoped>

</style>