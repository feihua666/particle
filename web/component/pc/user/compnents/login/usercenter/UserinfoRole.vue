<script setup name="UserinfoRole" lang="ts">
import {changeRole} from "../../../api/userLoginApi";
import {useLoginUserStore} from "../../../../../../global/common/security/loginUserStore"
const loginUserStore = useLoginUserStore()

const props = defineProps({
  // 角色数据
  roles: {
    type: Array,
    default: ()=>[]
  },
  // 当前角色对象
  currentRole: {
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
    label: '角色编码',
  },
  {
    prop: 'name',
    label: '角色名称',
  },
  {
    prop: 'useStatus',
    label: '使用情况',
    formatter: (row, column, cellValue, index) => {
      let r = ''
      if(row.id == props.currentRole.id){
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
  if(row.id != props.currentRole.id){
    tableRowButtons.push({
      txt: '切换到该角色',
      text: true,
      methodConfirmText: `切换后将会重新加载页面，确定要切换 ${row.name} 吗？`,
      methodSuccess(res){
        // 切换成功后处理
        loginUserStore.changeLoginUser(res.data.data)
      },
      // 删除操作
      method(){
        return changeRole(idData).then(res => {
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
           :options="roles"
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