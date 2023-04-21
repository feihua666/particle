<script setup name="UserinfoTenant" lang="ts">
import {changeTenant} from "../../api/userLoginApi";
import {useLoginUserStore} from "../../../../../global/common/security/loginUserStore"
const loginUserStore = useLoginUserStore()

const props = defineProps({
  // 租户数据
  tenants: {
    type: Array,
    default: ()=>[]
  },
  // 当前租户对象
  currentTenant: {
    type: Object,
    default: ()=>({})
  }
})
const dropdownTriggerButtonOptions = {
  text: true,
  buttonText: '更多',
}
const tableColumns = [
  {
    prop: 'code',
    label: '租户编码',
  },
  {
    prop: 'name',
    label: '租户名称',
  },
  {
    prop: 'useStatus',
    label: '使用情况',
    formatter: (row, column, cellValue, index) => {
      let r = ''
      if(row.id == props.currentTenant.id){
        r = '正在使用'
      }
      return r
    }

  },
]
// 表格操作按钮
const getTableRowButtons = ({row, column, $index}) => {
  if($index < 0){
    return []
  }
  let idData = {id: row.id}
  let tableRowButtons = [

  ]
  if(row.id != props.currentTenant.id){
    tableRowButtons.push(    {
      txt: '切换到该租户',
      text: true,
      methodConfirmText: `切换后将会重新加载页面，确定要切换 ${row.name} 吗？`,
      methodSuccess(res){
        // 切换成功后处理
        loginUserStore.changeLoginUser(res.data.data)

      },
      // 删除操作
      method(){
        return changeTenant(idData).then(res => {
          return Promise.resolve(res)
        })
      }
    })
  }

  return tableRowButtons
}
</script>
<template>
  <PtTable ref="tableRef"
           :columns="tableColumns"
           :options="tenants"
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