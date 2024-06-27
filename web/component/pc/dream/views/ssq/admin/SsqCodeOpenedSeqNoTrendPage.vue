<script setup  name="SsqCodeOpenedSeqNoTrendPage.vue" lang="ts">
/**
 * 已开奖双色球开奖对应序号趋势
 */
import { use } from 'echarts/core';
import { CanvasRenderer } from 'echarts/renderers';
import { LineChart,BarChart } from 'echarts/charts'
import { GridComponent,TooltipComponent,TitleComponent } from 'echarts/components'

import VChart from 'vue-echarts';
import {reactive, ref, onMounted, } from 'vue';
import {list} from "../../../api/ssq/admin/ssqCodeOpenedAdminApi";

use([GridComponent,TooltipComponent,TitleComponent, LineChart,BarChart, CanvasRenderer])
const computedRegionsDataPerPhaseOptions = ref()

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
        name: 'openedPhaseMonth',
      },
      element: {
        comp: 'PtDatePicker',
        formItemProps: {
          label: '开奖期号月份',
        },
        compProps: {
          type: 'month',
          valueFormat: 'MM'
        }
      }
    },
    {
      field: {
        name: 'openedPhaseNum',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '开奖期号数',
        },
        compProps: {
          clearable: true,
          placeholder: '如：从1~154',
        }
      }
    },
    {
      field: {
        name: 'openedWeekDay',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '开奖星期号',
        },
        compProps: {
          clearable: true,
          placeholder: '如：2、4、7',
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
// 计算按钮
const submitMethod = ():void => {
  // 加载全部数据，前端计算
  return list(reactiveData.form).then(res => {
    let data = res.data.data;
    computeRegionsDataPerPhase(data)
    return Promise.resolve(res)
  })
}
// 挂载
onMounted(() => {

})
/**
 * 计算每期的趋势数据
 * @param data
 */
const computeRegionsDataPerPhase = (data)=>{

  let year = data[0].openedPhaseYear
  let xAxisData = []
  let seriesData = []
  let result = []
  for (let i = 0; i < data.length; i++) {
/*
    if ( year != data[i].openedPhaseYear) {
      result.push(lineChartOptions(xAxisData,seriesData))
      year = data[i].openedPhaseYear
      xAxisData = []
      seriesData = []
    }*/

    xAxisData.push(data[i].openedPhase)
    seriesData.push(data[i][reactiveData.form.computeObject])
  }
  if (xAxisData.length > 0) {
    result.push(lineChartOptions(xAxisData,seriesData))
  }

  computedRegionsDataPerPhaseOptions.value = result
}

const lineChartOptions = (xAxisData,seriesData) => {
  return         {
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
  <template v-for="(computedRegionsDataPerPhaseOption,index) in computedRegionsDataPerPhaseOptions">
    <v-chart style="width: 100%;height: 300px;" :option="computedRegionsDataPerPhaseOption" autoresize />
  </template>
</template>


<style scoped>

</style>
