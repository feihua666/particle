<script setup  name="SsqCodeOpenedSeqNoTrendPage.vue" lang="ts">
/**
 * 已开奖双色球开奖在滑动窗口趋势图
 * 主要思路：在滑动窗口内将已开奖序号想加求平均值，再减去一个固定参数值（即理想的平均值）得到的数据趋势
 */
import { use } from 'echarts/core';
import { CanvasRenderer } from 'echarts/renderers';
import { LineChart,BarChart } from 'echarts/charts'
import { GridComponent,TooltipComponent,TitleComponent } from 'echarts/components'

import VChart from 'vue-echarts';
import {reactive, ref, onMounted, } from 'vue';
import {list} from "../../../api/ssq/admin/ssqCodeOpenedAdminApi";

use([GridComponent,TooltipComponent,TitleComponent, LineChart,BarChart, CanvasRenderer])
const computedSlidingWindowOptions = ref()

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
        name: 'slidingWindowSize',
        value: 5
      },
      element: {
        comp: 'el-input-number',
        formItemProps: {
          label: '滑动窗口大小',
          tips: '可调用滑动窗口大小以寻找最佳滑动窗口大小',
        },
        compProps: {

        }
      }
    },
    {
      field: {
        name: 'computeObject',
        value: 'seqNo'
      },
      element: {
        comp: 'PtSelect',
        formItemProps: {
          label: '计算对象',
          required: true
        },
        compProps: {
          dataMethod: () => {
            return {
              data: [
                {id: 'seqNo',name: '序号'},
                {id: 'redSeqNo',name: '红序号'},
                {id: 'blue',name: '蓝球'},
              ]
            }
          }
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

const minWindowSizeRef = ref({})
const finalMaxOffsetRef = ref([])
const currentComputeObjectConfigRef = ref({})
// 计算按钮
const submitMethod = ():void => {
  // 加载全部数据，前端计算
  return list(reactiveData.form).then(res => {
    let data = res.data.data;
    // 计算图表选项数据
    currentComputeObjectConfigRef.value = computeObjectConfig[reactiveData.form.computeObject]
    let result = computeMultipleSlidingWindow(data,reactiveData.form.slidingWindowSize,
        currentComputeObjectConfigRef.value.fieldName,
        currentComputeObjectConfigRef.value.max,
        currentComputeObjectConfigRef.value.valueFormat,true)
    computedSlidingWindowOptions.value = result

    // 计算最大偏移系数
    let a = []
    for (let i = 0; i < result.length; i++) {
      a = a.concat(result[i].myCustom.maxOffset)
    }
    finalMaxOffsetRef.value = computeFinalMaxOffset(a)

    // 计算最优的滑动窗口
    autoComputeMultipleSlidingWindow(data,currentComputeObjectConfigRef.value.fieldName,
        currentComputeObjectConfigRef.value.max,
        currentComputeObjectConfigRef.value.valueFormat)
    return Promise.resolve(res)
  })
}
// 挂载
onMounted(() => {
  let predictBuleResult = predictBlue([7,13,8,6,3,3,7,3,6,2,11,2,15,15,3],[8.8,8.2,8.9,8.1,9.1,9.3,9.6])
  console.log(predictBuleResult)
})

/**
 * 所统计，最佳7个偏移量：0.3 -0.3  0.4 -0.4 0.6 0.8 1.1
 * 根据已开奖蓝球和预测偏移系数预测蓝球
 * @param arr 长度必须15如：[2,15,7,13,8,6,3,3,7,3,6,2,11,2,15]
 * @param arr1 长度代表在指个位置上预测一个号，如：[5.5,6.5,7.5,8.5,9.5]
 */
const predictBlue = (arr,arr1) => {

  let resultArr = []

  for (let i = 1; i < 17; i++) {
    let arrTemp = arr.concat([i])
    let sum = arrTemp.reduce((a,b) => a  + b )
    let avg = sum/16
    avg = avg.toFixed(1)
    if (arr1.some((item, index) => avg >=7.5 && avg <= 9.5)) {
      resultArr.push(i)
    }
  }
  return resultArr
}

/**
 * 计算对象配置，用来提前定义几个在切换计算对象的差异参数
 */
const computeObjectConfig = {
  'seqNo': {
    max: 17721008,
    fieldName: 'seqNo',
    valueFormat: (val)=>{
      // return Math.round(val)
      return val.toFixed(5)
    }
  },
  'redSeqNo': {
    max: 1107568,
    fieldName: 'redSeqNo',
    valueFormat: (val)=>{
      // return Math.round(val)
      return val.toFixed(5)
    }
  },
  'blue': {
    max: 16,
    fieldName: 'blue',
    valueFormat: (val)=>{
      return val.toFixed(5)
    }
  }
}
/**
 * 自动计算最优滑动窗口大小
 * @param data
 */
const autoComputeMultipleSlidingWindow = (data,computeObject,computeObjectMax,valueFormat) => {

  let minVarianceResult = null;
  let minWindowSize = null;
  let offsetDelta = null;

  for (let i = 4; i <= reactiveData.form.slidingWindowSize; i++) {
    let result = computeMultipleSlidingWindow(data,i,computeObject,computeObjectMax,valueFormat,false)
    if(!result){
      continue
    }
    for (let j = 0; j < result.length; j++) {
      let singleResult = result[j]
      let varianceResult = variance(singleResult.series[0].data)
      if (minVarianceResult == null) {
        minVarianceResult = varianceResult
      }else {
        if (minVarianceResult > varianceResult) {
          minVarianceResult = varianceResult
          minWindowSize = i
          offsetDelta = singleResult.myCustom.offsetDelta
        }
      }
    }

  }
  minWindowSizeRef.value = {minWindowSize,offsetDelta}
}
/**
 * 计算多个滑动窗口的图表数据
 * @param data
 * @param slidingWindowSize
 * @param computeObject
 * @param computeObjectMax
 * @param valueFormat
 */
const computeMultipleSlidingWindow = (data,slidingWindowSize,computeObject,computeObjectMax,valueFormat,isComputeMaxOffset) => {
  let result = []
  for (let i = 0; i < slidingWindowSize; i++) {
    let singleResult = computeSlidingWindow(data,i,slidingWindowSize,computeObject,computeObjectMax,valueFormat,isComputeMaxOffset)
    result = result.concat(singleResult)
  }

  return result
}
/**
 * 计算每期的趋势数据
 * @param data
 */
const computeSlidingWindow = (data,delta,slidingWindowSize,computeObject,computeObjectMax,valueFormat,isComputeMaxOffset)=>{

  let xAxisData = []
  let seriesData = []
  let result = []
  for (let i = delta; i < data.length; i++) {
    // 去掉最后一组不满滑动窗口的数据
    if (i + slidingWindowSize - 1 >= data.length) {
      continue
    }
    let sum = 0;
    for (let j = 0; j < slidingWindowSize; j++) {
      sum += data[i + j][computeObject]
    }
    xAxisData.push(data[i + slidingWindowSize - 1].openedPhase)

    let averageValue = sum / slidingWindowSize
    let constAverageValue = (computeObjectMax + 1) / 2
    let averageValueDelta = (averageValue - constAverageValue)
    // averageValueDelta = Math.abs(averageValueDelta)

    seriesData.push(Number(valueFormat(averageValueDelta)))
  }
  if (xAxisData.length > 0) {
    result.push(lineChartOptions(xAxisData,seriesData,'偏移：'+ delta,delta,isComputeMaxOffset))
  }
  return result

}

/**
 * 计算一组数据的方差
 * @param arr
 */
function variance(arr) {
  const n = arr.length;
  const mean = arr.reduce((a, b) => a + b) / n;

  const diffSquares = arr.map(x => Math.pow(x - mean, 2)).reduce((a, b) => a + b);
  const variance = diffSquares / n;
  return variance;
}

/**
 * 计算最终的最大偏移量
 * @param arr
 */
const computeFinalMaxOffset = (arr) => {

  let map = {}
  for (let i = 0; i < arr.length; i++) {
    let value = map[arr[i].offset]
    if(value == undefined){
      map[arr[i].offset] = 0
    }else {
      if(arr[i].count > value){
        map[arr[i].offset] = arr[i].count
      }

    }
  }
  let resultArr = []
  for (let mapKey in map) {
    resultArr.push({
      offset: mapKey,
      count: map[mapKey]
    })
  }
  resultArr.sort((x, y) => y.count - x.count);

// 取排序后的前10个对象

  let top10 = resultArr.slice(0, 10);
  return top10
}
/**
 * 计算一组数据中，偏移量出现次数最多的偏移量
 * @param seriesData
 */
const computeMaxOffset = (seriesData) => {
  let map = {}
  for (let i = 0; i < seriesData.length; i++) {
    let value = map[seriesData[i] + '']
    if(value == undefined){
      map[seriesData[i] + ''] = 0
    }else {
      map[seriesData[i] + ''] = value + 1
    }
  }
  let arr = []
  for (let mapKey in map) {
    arr.push({
      offset: mapKey,
      count: map[mapKey]
    })
  }
  arr.sort((x, y) => y.count - x.count);

// 取排序后的前10个对象

  let top10 = arr.slice(0, 10);
  return top10
}
/**
 * 拆线图配置
 * @param xAxisData
 * @param seriesData
 * @param titleText
 */
const lineChartOptions = (xAxisData,seriesData,titleText,myCustomOffsetDelta,isComputeMaxOffset) => {
  return         {
    myCustom:{
      offsetDelta: myCustomOffsetDelta,
      maxOffset: isComputeMaxOffset ? computeMaxOffset(seriesData): []
    },
    title: {
      text: titleText
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
      formatter: '序号：{c}<br/>期号：{b}'
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
  <div v-if="minWindowSizeRef.minWindowSize">
    最佳滑动窗口大小：{{minWindowSizeRef.minWindowSize}} 偏移：{{minWindowSizeRef.offsetDelta}}
  </div>
  <div v-if="finalMaxOffsetRef.length > 0">
    最佳偏移量top10：{{finalMaxOffsetRef}}
  </div>
  <template v-for="(computedSlidingWindowOption,index) in computedSlidingWindowOptions">
    <v-chart style="width: 1000%;height: 300px;" :option="computedSlidingWindowOption" autoresize />
    <div>{{(computedSlidingWindowOption.myCustom.maxOffset)}}</div>
  </template>
</template>


<style scoped>

</style>
