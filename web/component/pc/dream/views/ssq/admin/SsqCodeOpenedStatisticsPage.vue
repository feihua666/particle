<script setup  name="SsqCodeOpenedStatisticsPage" lang="ts">
import { use } from 'echarts/core';
import { CanvasRenderer } from 'echarts/renderers';
import { LineChart,BarChart } from 'echarts/charts'
import { GridComponent,TooltipComponent } from 'echarts/components'

import VChart, { THEME_KEY } from 'vue-echarts';
import { ref, onMounted, } from 'vue';
import {list} from "../../../api/ssq/admin/ssqCodeOpenedAdminApi";

use([GridComponent,TooltipComponent, LineChart,BarChart, CanvasRenderer])
const computedRegionDataOptions = ref()
const computedRegionsDataPerPhaseOptions = ref()

// 挂载
onMounted(() => {


// provide(THEME_KEY, 'dark');

// 加载全部数据，前端计算
  list({}).then(res => {
    let data = res.data.data;
    computeRegionsData(data)
    computeRegionsDataPerPhase(data)
  })
})
/**
 * 计算每期的趋势数据
 * @param data
 */
const computeRegionsDataPerPhase = (data)=>{

  let year = data[0].openedPhase
  let xAxisData = []
  let seriesData = []
  let result = []
  for (let i = 0; i < data.length; i++) {
    xAxisData.push(data[i].openedPhase)
    seriesData.push(data[i].seqNo)
    if ( year != data[i].openedPhaseYear) {
      result.push(lineChartOptions(xAxisData,seriesData))
      year = data[i].openedPhaseYear
      xAxisData = []
      seriesData = []
    }
  }
  if (xAxisData.length > 0) {
    result.push(lineChartOptions(xAxisData,seriesData))
  }

  computedRegionsDataPerPhaseOptions.value = result
}
/**
 * 计算区域数据量
 * @param data
 */
const computeRegionsData = (data)=>{
  let computeTimes = 5;
  let computeStep = 400;

  let computedRegionsDataResult = []
  for (let i = 0; i < computeTimes; i++) {
    let computedRegionData = computeRegionData(data,(i + 1) * computeStep);
    let chartOptions = barChartOptions(computedRegionData.xAxisData,computedRegionData.seriesData)
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

const lineChartOptions = (xAxisData,seriesData) => {
  return         {
    grid: {
      left: '60',
      right: '60',
      bottom: '100',
      containLabel: true
    },
    tooltip: {
      // 触发类型，默认数据触发，可选为：'item' | 'axis'
      trigger: 'axis',
      // 显示策略，默认 true 显示
      show: true,
      // 内容格式器：{string}（Template） | {Function}，支持使用回调函数生成复杂的提示文本
      formatter: '{c}<br/>{b}'
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
        type: 'line',
        label: {
          show: true,
          position: 'top', // 顶部显示
          formatter: '{c}', // 显示数据值
        }
      }
    ]
  }
}
const barChartOptions = (xAxisData,seriesData) => {
  return {
    grid: {
      left: '60',
      right: '60',
      bottom: '100',
      containLabel: true
    },
    tooltip: {
      // 触发类型，默认数据触发，可选为：'item' | 'axis'
      trigger: 'axis',
      // 显示策略，默认 true 显示
      show: true,
      // 内容格式器：{string}（Template） | {Function}，支持使用回调函数生成复杂的提示文本
      formatter: '{c}<br/>{b}'
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
  <template v-for="(computedRegionDataOption,index) in computedRegionDataOptions">
    <v-chart style="width: 1000%;height: 300px;" :option="computedRegionDataOption" autoresize />
  </template>
  <template v-for="(computedRegionsDataPerPhaseOption,index) in computedRegionsDataPerPhaseOptions">
    <v-chart  v-if="index == computedRegionsDataPerPhaseOptions.length -1" style="width: 63%;height: 300px;" :option="computedRegionsDataPerPhaseOption" autoresize />
    <v-chart v-else style="width: 150%;height: 300px;" :option="computedRegionsDataPerPhaseOption" autoresize />
  </template>
</template>


<style scoped>

</style>
