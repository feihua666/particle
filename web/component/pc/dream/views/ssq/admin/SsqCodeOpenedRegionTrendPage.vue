<script setup  name="SsqCodeOpenedSeqNoTrendPage.vue" lang="ts">
/**
 * 已开奖双色球开奖区域分布趋势
 */
import { use } from 'echarts/core';
import { CanvasRenderer } from 'echarts/renderers';
import { LineChart,BarChart } from 'echarts/charts'
import { GridComponent,TooltipComponent,TitleComponent, } from 'echarts/components'

import VChart from 'vue-echarts';
import {reactive, ref, onMounted, } from 'vue';
import {list} from "../../../api/ssq/admin/ssqCodeOpenedAdminApi";

use([GridComponent,TooltipComponent,TitleComponent, LineChart,BarChart, CanvasRenderer])
const computedRegionDataOptions = ref()

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: [
    {
      field: {
        name: 'openedPhaseYearStart',
        value: '2013'
      },
      element: {
        comp: 'PtDatePicker',
        formItemProps: {
          label: '开奖期号年份开始',
          tips: '包括数值本身',
        },
        compProps: {
          type: 'year',
          valueFormat: 'YYYY'
        }
      }
    },
    {
      field: {
        name: 'openedPhaseYearEnd',
      },
      element: {
        comp: 'PtDatePicker',
        formItemProps: {
          label: '开奖期号年份结束',
          tips: '不包括数值本身',
        },
        compProps: {
          type: 'year',
          valueFormat: 'YYYY'
        }
      }
    },
    {
      field: {
        name: 'computeRegionMin',
        value: 2
      },
      element: {
        comp: 'el-input-number',
        formItemProps: {
          label: '分区最小值',
          tips: '包括数值本身',
          required: true
        },
        compProps: {
        }
      }
    },
    {
      field: {
        name: 'computeRegionMax',
        value: 200
      },
      element: {
        comp: 'el-input-number',
        formItemProps: {
          label: '分区最大值',
          tips: '不包括数值本身',
          required: true
        },
        compProps: {
        }
      }
    },
    {
      field: {
        name: 'computeStep',
        value: 200
      },
      element: {
        comp: 'el-input-number',
        formItemProps: {
          label: '分区计算步长',
          required: true
        },
        compProps: {
        }
      }
    },
  ],


})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '计算',
  loading: false,
  permission: 'admin:web:ssqCodeOpened:queryList'
})
// 计算按钮
const submitMethod = ():void => {
  // 加载全部数据，前端计算
  return list(reactiveData.form).then(res => {
    let data = res.data.data;
    computeRegionsData(data)
    return Promise.resolve(res)
  })
}
// 挂载
onMounted(() => {

})
/**
 * 计算区域数据量
 * @param data
 */
const computeRegionsData = (data)=>{
  let computeRegionMin = reactiveData.form.computeRegionMin
  let computeRegionMax = reactiveData.form.computeRegionMax
  let computeStep = reactiveData.form.computeStep


  let computedRegionsDataResult = []
  for (let i = computeRegionMin; i < computeRegionMax; i+=computeStep) {
    let computedRegionData = computeRegionData(data,i);
    let chartOptions = barChartOptions(computedRegionData.xAxisData,computedRegionData.seriesData,i+'')
    computedRegionsDataResult.push(chartOptions)
  }
  computedRegionDataOptions.value = computedRegionsDataResult
}
/**
 * 计算序号分区数据
 * @param allData
 * @param regionCount
 */
const computeRegionData = (allData: any[],regionCount) => {
  // let maxNum = 1107568;
  let maxNum = 17721008;
  let perRegion = maxNum/regionCount
  perRegion = perRegion.toFixed(1)

  let xAxisData = []
  let seriesData = []
  for (let i = 0; i < regionCount; i++) {
    let perRegionMax = (i + 1) * perRegion
    let perRegionMin = perRegionMax-perRegion
    perRegionMax = perRegionMax.toFixed(1)
    perRegionMin = perRegionMin.toFixed(1)
    xAxisData.push(perRegionMin + '-' + perRegionMax)
    seriesData.push(0)
  }

  for (let i = 0; i < allData.length; i++) {
    let seqNo = allData[i].seqNo
    for (let j = 0; j < regionCount; j++) {
      let xAxisDataScope = xAxisData[j].split('-')
      let xAxisDataMin = xAxisDataScope[0]
      let xAxisDataMax = xAxisDataScope[1]
      if (seqNo >= xAxisDataMin && seqNo < xAxisDataMax) {
        seriesData[j] = seriesData[j] + 1
      }
    }
  }

  return {xAxisData,seriesData}

}
const barChartOptions = (xAxisData,seriesData,titleText) => {
  return {
    title: {
      text: titleText,
    },
    grid: {
      left: '0',
      right: '0',
      bottom: '0',
      containLabel: true
    },
    tooltip: {
      // 触发类型，默认数据触发，可选为：'item' | 'axis'
      trigger: 'axis',
      // 显示策略，默认 true 显示
      show: true,
      // 内容格式器：{string}（Template） | {Function}，支持使用回调函数生成复杂的提示文本
      formatter: '个数：{c}<br/>序号区间：{b}'
    },
    xAxis: {
      type: 'category',
      data: xAxisData,
      axisLabel: {
        rotate: 30 // X轴标签倾斜 45 度
      }
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data: seriesData,
        type: 'bar',
        label: {
          show: true,
          position: 'top', // 顶部显示
          formatter: '{c}', // 显示数据值
        }
      }
    ]
  }
}
</script>
<template>
  <!-- 查询表单 -->
  <PtForm :form="reactiveData.form"
          :method="submitMethod"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          inline
          :comps="reactiveData.formComps">
  </PtForm>
  <template v-for="(computedRegionDataOption,index) in computedRegionDataOptions">
    <v-chart style="width: 100%;height: 300px;" :option="computedRegionDataOption" autoresize />
  </template>
</template>


<style scoped>

</style>
