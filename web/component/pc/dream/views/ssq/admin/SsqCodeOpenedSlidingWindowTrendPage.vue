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
const computedSlidingWindowOption = ref({})

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: [
    {
      field: {
        name: 'openedPhaseYearStart',
        value: new Date().getFullYear() + '',
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
        value: 4
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

const currentComputeObjectConfigRef = ref({})
// 计算按钮
const submitMethod = ():void => {
  // 加载全部数据，前端计算
  return list(reactiveData.form).then(res => {
    let data = res.data.data;
    // 计算图表选项数据
    currentComputeObjectConfigRef.value = computeObjectConfig[reactiveData.form.computeObject]
    let result = computeSlidingWindow(data,
        reactiveData.form.slidingWindowSize,
        currentComputeObjectConfigRef.value)
    computedSlidingWindowOption.value = result

    return Promise.resolve(res)
  })
}
// 挂载
onMounted(() => {

})


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
 * 计算每期的趋势数据
 * @param data
 */
const computeSlidingWindow = (data,slidingWindowSize,computeObject)=>{

  let xAxisData = []
  let seriesData = []
  for (let i = slidingWindowSize; i < data.length; i++) {

    let sum = 0;
    let sumArray = []
    for (let j = 0; j < slidingWindowSize; j++) {
      let fieldNameValue = data[i -( slidingWindowSize - j - 1)][computeObject.fieldName]
      sumArray.push(fieldNameValue);
    }
    sum = sumArray.reduce((a, b) => a + b, 0)
    xAxisData.push(data[i].openedPhase);
    seriesData.push(Number(computeObject.valueFormat(sum)))
  }
  return lineChartOptions(xAxisData,seriesData,'')

}
const calculateVarianceAndStdDev = (data) =>{
  // 1. 计算平均值
  let sum = data.reduce((a, b) => a + b, 0);
  let mean = sum / data.length;

  // 2. 计算方差
  let variance = data.reduce((a, b) => a + Math.pow(b - mean, 2), 0) / (data.length - 1); // 样本方差

  // 3. 计算标准差
  let stdDev = Math.sqrt(variance);

  // 返回方差和标准差
  return { variance, stdDev };
}
/**
 * 计算ac值
 * @param numbers
 */
const calculateACValue = (numbers) =>{

  let numbersTemp = new Set();
  for (let i = 0; i < numbers.length; i++) {
    numbersTemp.add(numbers[i])
  }
  numbers = Array.from(numbersTemp);
  // 初始化差值集合
  let differences = new Set();

  // 计算任意两个数字之间的差值，并将差值添加到集合中
  for (let i = 0; i < numbers.length; i++) {
    for (let j = i + 1; j < numbers.length; j++) {
      // 计算正差值并添加到集合中
      let diff = Math.abs(numbers[i] - numbers[j]);
      differences.add(diff);
    }
  }

  // 计算差值集合的大小（不同正差值的总个数）
  let diffCount = differences.size;

  // 根据定义，减去“正选号码数量-1”
  let acValue = diffCount - (numbers.length - 1);

  // 返回AC值
  return acValue;
}

/**
 * 拆线图配置
 * @param xAxisData
 * @param seriesData
 * @param titleText
 */
const lineChartOptions = (xAxisData,seriesData,titleText) => {
  return         {
    myCustom:{

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
      formatter: '和数：{c}<br/>期号：{b}'
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
  <v-chart style="width: 100%;height: 300px;" :option="computedSlidingWindowOption" autoresize />
</template>

<style scoped>

</style>
