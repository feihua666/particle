<template>
  <div class="pt-el-cron">
    <el-input size="small" v-model="resultTxtValue" readonly><template slot="prepend">cron表达式：</template><el-button slot="append" @click="$_okSetting" type="primary">确定</el-button></el-input>
    <el-tabs v-model="activeName" type="card">
      <el-tab-pane
          v-for="uiDataItem in uiData"
          :key="uiDataItem.type"
          :label="uiDataItem.tabLabel"
          :name="uiDataItem.type"
      >
        <template  v-for="(radiosItem, radiosIndex) in uiDataItem.radios"  :key="radiosIndex">
          <el-radio class="radio-block" v-model="uiDataItem.radioValue" :label="radiosItem.radioLabel">
            <template v-if="radiosItem.type == 'text'">
              {{radiosItem.txt}}
            </template>
            <template v-else-if="radiosItem.type == 'range'">
              <template v-for="radioRangeItem in radiosItem.txt">
                <template v-if="typeof radioRangeItem == 'string'">{{radioRangeItem}}</template>
                <el-input-number v-model="radiosItem.dataValue[radioRangeItem.dataValueIndex]" v-else-if="isObject(radioRangeItem) && radioRangeItem.type== 'inputNumber'" v-bind="radioRangeItem.props" size="small"></el-input-number>
              </template>
            </template>
            <template v-else-if="radiosItem.type == 'fixed'">
              {{radiosItem.txt}}
              <el-checkbox-group style="margin-left: 30px;" v-model="radiosItem.dataValue">
                <template  v-for="(rangItem,rangeItemIndex) in $_getRangeArray(radiosItem.range)">
                  <el-checkbox :label="rangItem">{{$_prependZero(rangItem)}}</el-checkbox>
                  <br v-if="((rangeItemIndex + 1) % 10 == 0)"/>
                </template>

              </el-checkbox-group>
            </template>
          </el-radio>
        </template>

        <el-button @click="resetByDefine(uiDataItem.type)" size="small">重置当前项</el-button> <el-button @click="resetByDefine()" size="small">全部重置</el-button>
      </el-tab-pane>
      <el-tab-pane name="quick" label="常用表达式">
        <el-radio class="radio-block" v-for="(quickDataItem,index) in quickData" v-model="quickDataValue" :label="quickDataItem[0]" :key="index">
          {{quickDataItem.join('   ')}}
        </el-radio>
      </el-tab-pane>
    </el-tabs>
    <el-slider size="small"
               v-model="runtimes"
               :step="5"
               :min="5"
               show-input
               show-stops :format-tooltip="(val) => {return `模拟运行${val}次`}">
    </el-slider>
    <el-input size="small" class="pt-margin-top-30-px" rows="20" :value="runtimesResult.join('\n')" readonly type="textarea" placeholder="触发时间展示区"></el-input>
  </div>

</template>

<script>
import {clone, isObject} from "../../../../../global/common/tools/ObjectTools";
import {isArray} from "../../../../../global/common/tools/ArrayTools";
import {cronRunTimes} from "../../api/front/toolsFrontApi";
import {ElMessage} from 'element-plus'

const alert = (message,type='success')=>{
  ElMessage({
    showClose: true,
    message: message,
    type: type,
    showIcon: true,
    grouping: true
  })
}
let uiRadioValueParser = {
  per: ()=>{
    return '*'
  },
  range: (dataValue)=>{
    return dataValue.join('-')
  },
  perRange: (dataValue)=>{
    return dataValue.join('/')
  },
  fixed: (dataValue)=>{
    let temp = clone(dataValue)
    temp.sort()
    return temp.join(',')
  },
  lastDay: ()=>{
    return 'L'
  },
  lastWorkDay: ()=>{
    return 'LW'
  },
  perRangeWorkDay: (dataValue)=>{
    let r = dataValue
    if (isArray(r)) {
      r = dataValue[0]
    }
    return r + 'W';
  },
  no: ()=>{
    return '?'
  },
  fixedRange: (dataValue)=>{
    return dataValue.join('#')
  },
  lastWk: (dataValue)=>{
    let r = dataValue
    if (isArray(r)) {
      r = dataValue[0]
    }
    return r + 'L';
  },
  noSet: (dataValue)=>{
    return '';
  },
}

let uiDataDefine = [
  {
    type: 'second',
    index: 0,
    tabLabel: '秒',
    radioValue: 'per',
    radios: [// radio内容ui数据
      {
        type: 'text', // 内容类型
        txt: '每秒 允许的通配符[, - * /]',
        radioLabel: 'per', // radio 选中值
      },
      {
        type: 'range',
        txt: ['周期从 ',{type: 'inputNumber',props: {min:0,max: 59},dataValueIndex: 0},' - ',{type: 'inputNumber',props: {min:0,max: 59},dataValueIndex: 1},' 秒'],
        radioLabel: 'range',
        dataValue: [0,1],
      },
      {
        type: 'range',
        txt: ['从 ',{type: 'inputNumber',props: {min:0,max: 59},dataValueIndex: 0},' 秒开始，每 ',{type: 'inputNumber',props: {min:0,max: 59},dataValueIndex: 1},' 秒执行一次'],
        radioLabel: 'perRange',
        dataValue: [0,1]
      },
      {
        type: 'fixed',
        txt: '指定',
        range:[0,59],
        radioLabel: 'fixed',
        dataValue: [0,1]
      }
    ]
  },
  {
    type: 'minute',
    index: 1,
    tabLabel: '分',
    radioValue: 'per',
    radios: [// radio内容ui数据
      {
        type: 'text', // 内容类型
        txt: '每分 允许的通配符[, - * /]',
        radioLabel: 'per' // radio 选中值
      },
      {
        type: 'range',
        txt: ['周期从 ',{type: 'inputNumber',props: {min:0,max: 59},dataValueIndex: 0},' - ',{type: 'inputNumber',props: {min:0,max: 59},dataValueIndex: 1},' 分'],
        radioLabel: 'range',
        dataValue: [0,1]
      },
      {
        type: 'range',
        txt: ['从 ',{type: 'inputNumber',props: {min:0,max: 59},dataValueIndex: 0},' 分开始，每 ',{type: 'inputNumber',props: {min:0,max: 59},dataValueIndex: 1},' 分执行一次'],
        radioLabel: 'perRange',
        dataValue: [0,1]
      },
      {
        type: 'fixed',
        txt: '指定',
        range:[0,59],
        radioLabel: 'fixed',
        dataValue: [0,1]
      }
    ]
  },
  {
    type: 'hour',
    index: 2,
    tabLabel: '时',
    radioValue: 'per',
    radios: [// radio内容ui数据
      {
        type: 'text', // 内容类型
        txt: '每小时 允许的通配符[, - * /]',
        radioLabel: 'per' // radio 选中值
      },
      {
        type: 'range',
        txt: ['周期从 ',{type: 'inputNumber',props: {min:0,max: 23},dataValueIndex: 0},' - ',{type: 'inputNumber',props: {min:0,max: 23},dataValueIndex: 1},' 小时'],
        radioLabel: 'range',
        dataValue: [0,1]
      },
      {
        type: 'range',
        txt: ['从 ',{type: 'inputNumber',props: {min:0,max: 23},dataValueIndex: 0},' 小时开始，每 ',{type: 'inputNumber',props: {min:0,max: 23},dataValueIndex: 1},' 小时执行一次'],
        radioLabel: 'perRange',
        dataValue: [0,1]
      },
      {
        type: 'fixed',
        txt: '指定',
        range:[0,23],
        radioLabel: 'fixed',
        dataValue: [0,1]
      }
    ]
  },
  {
    type: 'day',
    index: 3,
    tabLabel: '日',
    radioValue: 'per',
    radios: [// radio内容ui数据
      {
        type: 'text', // 内容类型
        txt: '每日 允许的通配符[, - * / L W]',
        radioLabel: 'per' // radio 选中值
      },
      {
        type: 'range',
        txt: ['周期从 ',{type: 'inputNumber',props: {min:0,max: 31},dataValueIndex: 0},' - ',{type: 'inputNumber',props: {min:0,max: 31},dataValueIndex: 1},' 日'],
        radioLabel: 'range',
        dataValue: [0,1]
      },
      {
        type: 'range',
        txt: ['从 ',{type: 'inputNumber',props: {min:0,max: 23},dataValueIndex: 0},' 日开始，每 ',{type: 'inputNumber',props: {min:0,max: 23},dataValueIndex: 1},' 日执行一次'],
        radioLabel: 'perRange',
        dataValue: [0,1]
      },
      {
        type: 'fixed',
        txt: '指定',
        range:[0,31],
        radioLabel: 'fixed',
        dataValue: [0,1]
      },
      {
        type: 'text',
        txt: '本月最后一天',
        radioLabel: 'lastDay'
      },
      {
        type: 'text',
        txt: '本月最后一个工作日',
        radioLabel: 'lastWorkDay'
      },
      {
        type: 'range',
        txt: ['每月 ',{type: 'inputNumber',props: {min:1,max: 31},dataValueIndex: 0},' 号最近的那个工作日'],
        radioLabel: 'perRangeWorkDay',
        dataValue: [1]
      },
      {
        type: 'text',
        txt: '不指定',
        radioLabel: 'no'
      },
    ]
  },
  {
    type: 'month',
    index: 4,
    tabLabel: '月',
    radioValue: 'per',
    radios: [// radio内容ui数据
      {
        type: 'text', // 内容类型
        txt: '每月 允许的通配符[, - * /]',
        radioLabel: 'per' // radio 选中值
      },
      {
        type: 'range',
        txt: ['周期从 ',{type: 'inputNumber',props: {min:1,max: 12},dataValueIndex: 0},' - ',{type: 'inputNumber',props: {min:1,max: 12},dataValueIndex: 1},' 月'],
        radioLabel: 'range',
        dataValue: [1,2]
      },
      {
        type: 'range',
        txt: ['从 ',{type: 'inputNumber',props: {min:1,max: 12},dataValueIndex: 0},' 月开始，每 ',{type: 'inputNumber',props: {min:1,max: 12},dataValueIndex: 1},' 月执行一次'],
        radioLabel: 'perRange',
        dataValue: [0,1]
      },
      {
        type: 'fixed',
        txt: '指定',
        range:[1,12],
        radioLabel: 'fixed',
        dataValue: [1,2]
      },
    ]
  },
  {
    type: 'week',
    index: 5,
    tabLabel: '周',
    radioValue: 'no',
    radios: [// radio内容ui数据
      {
        type: 'text', // 内容类型
        txt: '每周 允许的通配符[, - * / L #]',
        radioLabel: 'per' // radio 选中值
      },
      {
        type: 'range',
        txt: ['周期从星期 ',{type: 'inputNumber',props: {min:1,max: 7},dataValueIndex: 0},' - 星期',{type: 'inputNumber',props: {min:1,max: 7},dataValueIndex: 1}],
        radioLabel: 'range',
        dataValue: [1,2]
      },
      {
        type: 'range',
        txt: ['每月第 ',{type: 'inputNumber',props: {min:1,max: 4},dataValueIndex: 0},' 周的星期 ',{type: 'inputNumber',props: {min:1,max: 12},dataValueIndex: 1}],
        radioLabel: 'fixedRange',
        dataValue: [1,1]
      },
      {
        type: 'fixed',
        txt: '指定',
        range:[1,7],
        radioLabel: 'fixed',
        dataValue: [1,2]
      },
      {
        type: 'range',
        txt: ['本月最后一个星期 ',{type: 'inputNumber',props: {min:1,max: 7},dataValueIndex: 0}],
        radioLabel: 'lastWk',
        dataValue: [1]
      },
      {
        type: 'text',
        txt: '不指定',
        radioLabel: 'no'
      },
    ]
  },
  {
    type: 'year',
    index: 6,
    tabLabel: '年',
    radioValue: 'noSet',
    radios: [// radio内容ui数据
      {
        type: 'text', // 内容类型
        txt: '每年 允许的通配符[, - * /] 非必填',
        radioLabel: 'per' // radio 选中值
      },
      {
        type: 'range',
        txt: ['周期从 ',{type: 'inputNumber',props: {},dataValueIndex: 0},'年 - ',{type: 'inputNumber',props: {},dataValueIndex: 1},'年'],
        radioLabel: 'range',
        dataValue: [new Date().getFullYear(),new Date().getFullYear() + 2]
      },
      {
        type: 'range',
        txt: ['从 ',{type: 'inputNumber',props: {},dataValueIndex: 0},' 年开始，每 ',{type: 'inputNumber',props: {},dataValueIndex: 1},' 年执行一次'],
        radioLabel: 'perRange',
        dataValue: [new Date().getFullYear(),2]
      },
      {
        type: 'fixed',
        txt: '指定',
        range:[new Date().getFullYear(),new Date().getFullYear() + 59],
        radioLabel: 'fixed',
        dataValue: [new Date().getFullYear()]
      },
      {
        type: 'text',
        txt: '不指定,忽略该位',
        radioLabel: 'noSet'
      },
    ]
  },
]
let quickData = [
  ['0 0 2 1 * ?',				'表示在每月的1日的凌晨2点触发'],
  ['0 15 10 ? * MON-FRI',		'表示周一到周五每天上午10:15触发'],
  ['0 15 10 ? 6L 2002-2006',	'表示2002-2006年的每个月的最后一个星期五上午10:15触发'],
  ['0 0 10,14,16 * * ?',		'每天上午10点，下午2点，4点触发'],
  ['0 0/30 9-17 * * ?',		'朝九晚五工作时间内每半小时触发'],
  ['0 0 12 ? * WED',			'表示每个星期三中午12点触发'],
  ['0 0 12 * * ?',				'每天中午12点触发'],
  ['0 15 10 ? * *',			'每天上午10:15触发'],
  ['0 15 10 * * ?',			'每天上午10:15触发'],
  ['0 15 10 * * ? *',			'每天上午10:15触发'],
  ['0 15 10 * * ? 2005',		'2005年的每天上午10:15触发'],
  ['0 * 14 * * ?',				'在每天下午2点到下午2:59期间的每1分钟触发'],
  ['0 0/5 14 * * ?',			'在每天下午2点到下午2:55期间的每5分钟触发 '],
  ['0 0/5 14,18 * * ?',		'在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发'],
  ['0 0-5 14 * * ?',			'在每天下午2点到下午2:05期间的每1分钟触发'],
  ['0 10,44 14 ? 3 WED',		'每年三月的星期三的下午2:10和2:44触发'],
  ['0 15 10 ? * MON-FRI',		'周一至周五的上午10:15触发'],
  ['0 15 10 15 * ?',			'每月15日上午10:15触发'],
  ['0 15 10 L * ?',			'每月最后一日的上午10:15触发'],
  ['0 15 10 ? * 6L',			'每月的最后一个星期五上午10:15触发'],
  ['0 15 10 ? * 6L 2002-2005',	'2002年至2005年的每月的最后一个星期五上午10:15触发'],
  ['0 15 10 ? * 6#3',          '每月的第三个星期五上午10:15触发'],
]

export default {
  name: "Cron",
  components: {},
  props: {
  },
  computed: {
    resultTxt(){
      let r = []
      for (let key in this.result) {
        if (this.result[key] !== '') {
          r.push(this.result[key])
        }
      }
      return r.join(' ')
    }
  },
  data (){
    return {
      activeName: 'second',
      // ui用数据定义
      uiData: clone(uiDataDefine),
      resultTxtValue: '',
      runtimes: 10,
      runtimesResult: [],
      getRunTimesLoading: false,
      quickData: quickData,
      quickDataValue: '',
      result: {
        second: '',
        minute: '',
        hour: '',
        day: '',
        month: '',
        week: '',
        year: '',
      }
    }
  },
  mounted() {
    this.$_parseValue()
  },
  methods: {
    isObject(val){
      return isObject(val)
    },
    $_getRangeArray(range) {
      let r = []
      for (let i = range[0]; i <= range[1]; i++) {
        r.push(i)
      }
      return r
    },
    $_prependZero(num) {
      if (num < 10) {
        return '0' + num
      }
      return num
    },
    $_parseValue() {
      this.uiData.forEach(item => {
        item.radios.filter(radioItem => radioItem.radioLabel == item.radioValue).forEach(radioItem => {
          this.result[item.type] = uiRadioValueParser[radioItem.radioLabel](radioItem.dataValue)
        })
      })
    },
    $_okSetting() {
      this.$emit('okSetting',this.resultTxtValue)
    },

    // 根据ui数据定义重置
    resetByDefine(type) {
      if (type) {
        uiDataDefine.filter(item => item.type == type).forEach(item => {
          this.uiData.splice(item.index,1,item)
        })
      }else {
        this.activeName = 'second'
        this.uiData =clone(uiDataDefine)
      }
    },
    // 获取执行次数
    getRunTimes() {
      if (this.getRunTimesLoading) {
        return
      }
      if (this.resultTxtValue.split(' ').length == 7) {
        alert('计算运行时间不支持年','warning')
        this.runtimesResult = []
        return
      }
      this.getRunTimesLoading = true;
      cronRunTimes({cronExpression: this.resultTxtValue,times: this.runtimes}).then(res=>{
            this.runtimesResult = res.data.data
          }).catch(error=>{
        if(error.response){
          if(error.response.data && error.response.data.errorMsg){
            alert(error.response.data.errorMsg,'error')
          }
        }else {
          alert('网络错误或服务不可用','error')
        }
      }).finally(()=>{
        this.getRunTimesLoading = false
      })
    }
  },
  watch: {
    uiData: {
      deep: true,
      handler(){
        this.$_parseValue()
      }
    },
    resultTxt(val){
      this.$emit('change',val)
      this.resultTxtValue = val
      this.getRunTimes()
    },
    quickDataValue(val){
      this.$emit('change',val)
      this.resultTxtValue = val
      this.getRunTimes()
    },
    runtimes() {
      this.getRunTimes()
    }
  }
}
</script>

<style scoped>
.radio-block{
  display: block;
  margin: 10px 0;
  height: auto;
}
</style>
