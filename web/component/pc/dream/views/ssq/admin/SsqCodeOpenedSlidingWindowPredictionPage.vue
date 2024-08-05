<script setup  name="SsqCodeOpenedSeqNoTrendPage.vue" lang="ts">
/**
 * 已开奖双色球开奖在滑动窗口趋势图基础上预测号码
 * 主要思路：在滑动窗口内将已开奖序号想加求平均值，再减去一个固定参数值（即理想的平均值）得到的数据趋势
 */
import { use } from 'echarts/core';
import { CanvasRenderer } from 'echarts/renderers';
import { LineChart,BarChart } from 'echarts/charts'
import { GridComponent,TooltipComponent,TitleComponent } from 'echarts/components'

import VChart from 'vue-echarts';
import {reactive, ref, onMounted, } from 'vue';
import {list, page} from "../../../api/ssq/admin/ssqCodeOpenedAdminApi";
import {min, reverse} from "../../../../../../global/common/tools/ArrayTools";

use([GridComponent,TooltipComponent,TitleComponent, LineChart,BarChart, CanvasRenderer])
const computedSlidingWindowOption = ref({})
const computedSlidingWindowOption1 = ref({})
const computedSlidingWindowOption2 = ref({})
const computedSlidingWindowOption3 = ref({})
const computedSlidingWindowOption4 = ref({})
const resultRef = ref([])

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: [
    {
      field: {
        name: 'openedPhaseEnd',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '开奖期号截止',
          tips: '不包括数值本身，将按滑动窗口往前推来预测,不填写将按最新期号',
        },
        compProps: {
          clearable: true,
        }
      }
    },
    {
      field: {
        name: 'openedPhasePageSize',
        value: 80
      },
      element: {
        comp: 'el-input-number',
        formItemProps: {
          label: '开奖期号截止前条数',
        },
        compProps: {

        }
      }
    },
    {
      field: {
        name: 'slidingWindowSize',
        value: 10
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
    {
      field: {
        name: 'slidingWindowSize1',
        value: 4
      },
      element: {
        comp: 'el-input-number',
        formItemProps: {
          label: '滑动窗口大小1',
          tips: '可调用滑动窗口大小以寻找最佳滑动窗口大小',
        },
        compProps: {

        }
      }
    },
    {
      field: {
        name: 'slidingWindowSize2',
        value: 6
      },
      element: {
        comp: 'el-input-number',
        formItemProps: {
          label: '滑动窗口大小2',
          tips: '可调用滑动窗口大小以寻找最佳滑动窗口大小',
        },
        compProps: {

        }
      }
    },

    {
      field: {
        name: 'predictionNum',
        value: 10
      },
      element: {
        comp: 'el-input-number',
        formItemProps: {
          label: '预测数量',
        },
        compProps: {

        }
      }
    },
    {
      field: {
        name: 'predictionScopeStart',
        value: 10
      },
      element: {
        comp: 'el-input-number',
        formItemProps: {
          label: '预测区间开始',
          tips: '包括数值本身'
        },
        compProps: {

        }
      }
    },
    {
      field: {
        name: 'predictionScopeEnd',
        value: 10
      },
      element: {
        comp: 'el-input-number',
        formItemProps: {
          label: '预测区间结束',
          tips: '包括数值本身'
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
  permission: 'admin:web:ssqCodeOpened:pageQuery'
})

const currentComputeObjectConfigRef = ref({})
// 计算按钮
const submitMethod = ():void => {
  // 加载全部数据，前端计算
  let form = {
    pageNo: 1,
    pageSize: reactiveData.form.openedPhasePageSize,
    // 按openedPhase降序排序，取最新的数据
    orderBy: 'openedPhase-0',
    openedPhaseEnd: reactiveData.form.openedPhaseEnd
  }
  return page(form).then(res => {
    let data = res.data.data;

    // 计算图表选项数据
    currentComputeObjectConfigRef.value = computeObjectConfig[reactiveData.form.computeObject]
    data = reverse(data)
    let result = computeSlidingWindow(data,
        reactiveData.form.slidingWindowSize,
        currentComputeObjectConfigRef.value)


    let lineChartOptionsValue = lineChartOptions(result.xAxisData,result.seriesData,'滑动窗口大小')

    computedSlidingWindowOption.value = lineChartOptionsValue

    data = []
    let computeObjectValue = reactiveData.form.computeObject
    for (let i = 0; i < result.xAxisData.length; i++) {
      let newItem = {
        openedPhase: result.xAxisData[i],
      }
      newItem[computeObjectValue] = result.seriesData[i]
      data.push(newItem)
    }
    result = computeSlidingWindow(data,
        reactiveData.form.slidingWindowSize1,
        currentComputeObjectConfigRef.value)


    lineChartOptionsValue = lineChartOptions(result.xAxisData,result.seriesData,'滑动窗口大小1')

    computedSlidingWindowOption1.value = lineChartOptionsValue

    data = []
    for (let i = 0; i < result.xAxisData.length; i++) {
      let newItem = {
        openedPhase: result.xAxisData[i],
      }
      newItem[computeObjectValue] = result.seriesData[i]
      data.push(newItem)
    }
    result = computeSlidingWindow(data,
        reactiveData.form.slidingWindowSize2,
        currentComputeObjectConfigRef.value)


    lineChartOptionsValue = lineChartOptions(result.xAxisData,result.seriesData,'滑动窗口大小2')

    computedSlidingWindowOption2.value = lineChartOptionsValue
    resultRef.value = result.seriesData

    let xAxisData1 = []
    let seriesData1 = []
    for (let i = 1; i < result.xAxisData.length; i++) {
      seriesData1.push(Math.abs(result.seriesData[i] - result.seriesData[i - 1]))
      xAxisData1.push(result.xAxisData[i])
    }

    lineChartOptionsValue = lineChartOptions(xAxisData1,seriesData1,'差值')

    computedSlidingWindowOption3.value = lineChartOptionsValue

    let xAxisData2 = []
    let seriesData2 = []
    for (let i = 1; i < result.xAxisData.length; i++) {
      seriesData2.push(Math.abs(seriesData1[i] - seriesData1[i - 1]))
      xAxisData2.push(xAxisData1[i])
    }

    lineChartOptionsValue = lineChartOptions(xAxisData2,seriesData2,'差值的差值')

    computedSlidingWindowOption4.value = lineChartOptionsValue


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
const computeSlidingWindow = (data,slidingWindowSize,computeObjectConfigItem)=>{

  let xAxisData = []
  let seriesData = []
  for (let i = slidingWindowSize; i < data.length; i++) {

    let sum = 0;
    let sumArray = []
    for (let j = 0; j < slidingWindowSize; j++) {
      let fieldNameValue = data[i -( slidingWindowSize - j - 1)][computeObjectConfigItem.fieldName]
      sumArray.push(fieldNameValue);
    }
    sum = sumArray.reduce((a, b) => a + b, 0)
    xAxisData.push(data[i].openedPhase);
    seriesData.push(Number(computeObjectConfigItem.valueFormat(sum)))
  }
  let minValue = min(seriesData)
  for (let i = 0; i < seriesData.length; i++) {
    seriesData[i] = Number((seriesData[i] - minValue).toFixed(5))
  }
  return {xAxisData,seriesData}

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
  <v-chart style="width: 100%;height: 300px;" :option="computedSlidingWindowOption1" autoresize />
  <v-chart style="width: 100%;height: 300px;" :option="computedSlidingWindowOption2" autoresize />
  <v-chart style="width: 100%;height: 300px;" :option="computedSlidingWindowOption3" autoresize />
  <v-chart style="width: 100%;height: 300px;" :option="computedSlidingWindowOption4" autoresize />
  <div>{{resultRef}}</div>
</template>

<style scoped>

</style>
