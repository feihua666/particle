<script setup name="UserinfoLoginDevice" lang="ts">

import {getLoginDevice} from "../../../api/userLoginApi";
import {userLoginDeviceColumns} from "../../userLoginDeviceCompItem";

const props = defineProps({

})
const dropdownTriggerButtonOptions = {
  text: true,
  buttonText: '更多',
}
const tableColumns = userLoginDeviceColumns
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
</script>
<template>
  <PtTable ref="tableRef"
           :columns="tableColumns"
           :dataMethod="getLoginDevice"
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
