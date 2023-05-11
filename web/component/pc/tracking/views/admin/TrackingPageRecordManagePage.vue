<script setup name="TrackingPageRecordManagePage" lang="ts">
/**
 * 页面埋点记录管理页面
 */
import {reactive, ref} from 'vue'
import { page as trackingPageRecordPageApi, remove as trackingPageRecordRemoveApi} from "../../api/admin/trackingPageRecordAdminApi"
import {pageFormItems} from "../../compnents/admin/trackingPageRecordManage";


const tableRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  trackingPageCode: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    trackingPageCode: props.trackingPageCode
  },
  formComps: pageFormItems,
  tableColumns: [

    {
      prop: 'userNickname',
      label: '用户昵称',
    },
    {
      prop: 'userAvatar',
      label: '用户头像',
      columnView: 'image'
    },
    {
      prop: 'isUserTrigger',
      label: '用户触发',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '是' : '否'
        return r
      },
    },
    {
      prop: 'session',
      label: '会话标识',
      showOverflowTooltip: true
    },
    {
      prop: 'sessionMd5',
      label: '会话标识md5',
      showOverflowTooltip: true
    },
    {
      prop: 'imei',
      label: '设备串号',
    },
    {
      prop: 'deviceId',
      label: '设备id',
    },
    {
      prop: 'deviceName',
      label: '设备名称',
    },
    {
      prop: 'trackingPageCode',
      label: '页面编码',
    },
    {
      prop: 'preTrackingPageCode',
      label: '前驱页面编码',
    },
    {
      prop: 'operatingSystem',
      label: '操作系统及版本',
    },
    {
      prop: 'appVersion',
      label: '客户端版本',
    },
    {
      prop: 'actionType',
      label: '行为类型',
    },
    {
      prop: 'actionAt',
      label: '行为产生时间',
    },
    {
      prop: 'actionResult',
      label: '行为值',
    },
    {
      prop: 'actionOnX',
      label: '行为位置 x',
    },
    {
      prop: 'actionOnY',
      label: '行为位置 y',
    },
    {
      prop: 'netType',
      label: '网络类型',
    },
    {
      prop: 'longitude',
      label: '位置经度',
    },
    {
      prop: 'latitude',
      label: '位置纬度',
    },
    {
      prop: 'screenHeight',
      label: '屏幕高度',
    },
    {
      prop: 'screenWidth',
      label: '屏幕宽度',
    },
    {
      prop: 'entryAt',
      label: '进入页面时间',
    },
    {
      prop: 'leaveAt',
      label: '离开页面时间',
    },
    {
      prop: 'duration',
      label: '页面停留时长',
    },
    {
      prop: 'extInfoJson',
      label: '额外数据',
      showOverflowTooltip: true
    },
    {
      prop: 'traceId',
      label: '追踪id',
      showOverflowTooltip: true
    },
    {
      prop: 'frontTraceId',
      label: '前端追踪id',
      showOverflowTooltip: true
    },
    {
      prop: 'remark',
      label: '描述',
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:TrackingPageRecord:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doTrackingPageRecordPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return trackingPageRecordPageApi({...reactiveData.form,...pageQuery})
}
const tablePaginationProps = {
  permission: submitAttrs.value.permission
}
// 表格操作按钮
const getTableRowButtons = ({row, column, $index}) => {
  if($index < 0){
    return []
  }
  let idData = {id: row.id}
  let tableRowButtons = [

    {
      txt: '删除',
      text: true,
      permission: 'admin:web:TrackingPageRecord:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return trackingPageRecordRemoveApi({id: row.id}).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    }
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
          inline
          labelWidth="120"
          :comps="reactiveData.formComps">
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doTrackingPageRecordPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           
           :paginationProps="tablePaginationProps"
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