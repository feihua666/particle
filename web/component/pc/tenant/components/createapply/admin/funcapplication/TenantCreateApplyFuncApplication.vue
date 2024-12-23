<script setup name="TenantCreateApplyFuncApplication" lang="ts">
/**
 * 租户创建申请 要申请的功能应用
 */
import {ref} from 'vue'
import {list as funcApplicationListApi} from "../../../../../func/api/application/admin/funcApplicationAdminApi";
import TenantCreateApplyFuncApplicationAssignFunc from './TenantCreateApplyFuncApplicationAssignFunc.vue'

const funcApplications = ref([])
const activeName = ref('')
const funcApplicationAssignFuncRef = ref(null)

const props = defineProps({
  // 初始化的选中数据 和方法 getSelectedData 数据结构一致
  // array item 数据结构为 funcApplicationAssignFuncRef.value[i].form
  initSelectedData: {
    type: Array,
    default: () => []
  }
})

const getCheckedFuncIds = (funcApplicationId) => {

  if (props.initSelectedData.length == 0) {
    return []
  }
  for (let i = 0; i < props.initSelectedData.length; i++) {
    if(props.initSelectedData[i].funcApplicationId == funcApplicationId){
      return props.initSelectedData[i].funcIds
    }
  }
  return []
}

funcApplicationListApi({}).then(res => {
  funcApplications.value = res.data.data
  activeName.value = res.data.data[0].code
})
const getSelectedData = ()=>{
  let r = []
  for (let i = 0; i < funcApplicationAssignFuncRef.value.length; i++) {
    // 只获取有选中数据的
    if (funcApplicationAssignFuncRef.value[i].form.funcIds.length == 0) {
      continue
    }
    r.push(funcApplicationAssignFuncRef.value[i].form);
  }

  return r
}
const isCheckedEmpty = (checkedIds)=>{
  if(!checkedIds){
    return true
  }
  return checkedIds.length == 0
}
defineExpose({
  getSelectedData
})
</script>
<template>
  <el-tabs v-model="activeName">
    <template v-for="item in funcApplications" :key="item.id">
      <el-tab-pane :label="item.name" :name="item.code">
        <template #label v-if="!isCheckedEmpty(getCheckedFuncIds(item.id))">
          <el-badge type="success" is-dot class="pt-message-template-content-detail-json-badge">{{ item.name }}</el-badge>
        </template>
        <TenantCreateApplyFuncApplicationAssignFunc
            ref="funcApplicationAssignFuncRef"
            :funcApplicationId="item.id"
            :funcApplicationName="item.name"
            :limitFuncApplicationId="item.id"
            :checkedFuncIds="getCheckedFuncIds(item.id)"
        ></TenantCreateApplyFuncApplicationAssignFunc>
      </el-tab-pane>
    </template>

  </el-tabs>
</template>


<style scoped>

</style>
<style>
.pt-message-template-content-detail-json-badge .el-badge__content.is-fixed{
  top: .8rem;
}
</style>
