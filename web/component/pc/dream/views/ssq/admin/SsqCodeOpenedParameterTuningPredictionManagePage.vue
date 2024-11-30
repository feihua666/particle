<script setup name="SsqCodeOpenedPredictionParameterTuningManagePage" lang="ts">
/**
 * 根据双色球开奖号码调参预测管理页面
 */
import {reactive, ref} from 'vue'
import {
  predictionParameterTuning as predictionParameterTuningApi
} from "../../../api/ssq/admin/ssqCodeOpenedAdminApi"
import { predictionParameterTuningFormItems} from "../../../components/ssq/admin/ssqCodeOpenedManage";


const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: predictionParameterTuningFormItems,
  tableColumns: [

    {
      prop: 'openedCount',
      label: '当前区已开奖数量',
    },
    {
      prop: 'regionSeqNoMin',
      label: '当前区序号最小值',
    },
    {
      prop: 'regionSeqNoMax',
      label: '当前区序号最大值',
    },
    {
      prop: 'regionNo',
      label: '区序号',
    },
    {
      prop: 'regionCount',
      label: '总区数',
    },
    {
      prop: 'predictSeqNoMin',
      label: '预测序号最小值',
    },
    {
      prop: 'predictSeqNoMax',
      label: '预测序号最大值',
    },
    {
      prop: 'predictPercent',
      label: '预测基线百分比数',
    },
    {
      prop: 'isPredictHit',
      label: '是否预测命中',
    },
    {
      prop: 'predictHitOpenedPhase',
      label: '预测命中期号',
    },
    {
      prop: 'predictHitOpenedPhaseSize',
      label: '预测命中个数',
    },

  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '开始计算',
  loading: false,
  permission: 'admin:web:ssqCodeOpened:predictionParameterTuning'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doPredictionParameterTuningApi = () => {
  return predictionParameterTuningApi({...reactiveData.form})
}

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
  <!-- 查询表单 -->
  <PtForm :form="reactiveData.form"
          :method="submitMethod"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :doDataMethodOnMounted="false"
          inline
          labelWidth="140"
          :comps="reactiveData.formComps">

  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doPredictionParameterTuningApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :doDataMethodOnMounted="false"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="180">
        <template #default="{row, column, $index}">
          <PtButtonGroup :options="getTableRowButtons({row, column, $index})">
          </PtButtonGroup>
        </template>
      </el-table-column>
    </template>
  </PtTable>
</template>


<style scoped>

</style>
