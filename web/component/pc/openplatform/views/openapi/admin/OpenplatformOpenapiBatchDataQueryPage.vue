<script setup name="OpenplatformOpenapiBatchDataQueryPage" lang="ts">
/**
 * 开放平台开放接口批量查询页面
 */
import {reactive, ref} from 'vue'
import {batchQuery, downloadBatchQueryTemplate,} from "../../../api/openapi/admin/openplatformOpenapiAdminApi"
import {useCascaderOpenapiByOpenplatformAppIdCompItem,} from "../../../components/openplatformOpenapiCompItem";
import {useSelectAppForCurrentUserCompItem} from "../../../components/openplatformAppCompItem";

import {downloadFileByData, extractContentType, extractFileName} from "../../../../../../global/common/tools/FileTools";
import {ElMessage} from 'element-plus'
import {tableColumns} from "../../../components/openapi/admin/openplatformOpenapiBatchQueryRecordManage";
import {detail} from "../../../api/openapi/admin/openplatformOpenapiBatchQueryRecordAdminApi";
import {removeAll, replace} from "../../../../../../global/common/tools/ArrayTools";
import {
  download,
  extractDownloadFileNameByUrl,
  getFinalDownloadUrl
} from "../../../../../../global/common/api/globalApi";

let alert = (message,type='success')=>{
  ElMessage({
    showClose: true,
    message: message,
    type: type,
    showIcon: true,
    grouping: true
  })
}

// form 引用
const formRef = ref(null)
const templateFormRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: [
    useSelectAppForCurrentUserCompItem({
      label: '应用',
      required: true,
      valueChange: ({form,newValue}) => {
        if (!newValue) {
          form.openplatformOpenapiId = null
        }
      },
      tips: ' 1. 请先选择要使用的应用'
    }),
    useCascaderOpenapiByOpenplatformAppIdCompItem({
      fieldName: 'openplatformOpenapiId',
      label: '开放接口',
      required: true,
      disableGroup: true,
      valueChange: ({form,newValue}) => {

      },
      tips: ' 2. 请选择要查询的接口'
    }),

  ],
  templateform: {},
  templateformComps:[
    {
      field: {
        name: 'file',
        value: [],
      },
      element: {
        comp: 'PtUpload',
        formItemProps: {
          label: '查询文件',
          required: true,
          displayBlock: true,
        },
        compProps: {
          drag: true,
          autoUpload: false,
          limit: 1,
          dragTip: '将文件拖到此处或<em>点击上传</em>',
          tipTxt: '只能上传一个文件，请严格按照下载的模板进行填写，并保证第一个sheet为查询参数',
          onExceed: (files,uploadFiles)=>{
            alert('最多只能选择一个文件,如要重新选择，请先删除之前的文件','error')
          }
        }
      }
    },
    {
      field: {
        name: 'remark',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '备注',
          required: true,
          displayBlock: true,
        },
        compProps: {
          clearable: true,
        }
      }
    },
  ],
  // 批量查询后批量查询记录id，用于展示批量查询记录详情
  openplatformOpenapiBatchQueryRecordId: null,
  tableColumns: tableColumns,
  tableData: [],
})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:openplatformOpenapi:batchQuery'
})
// 查询按钮
const submitMethod = ():void => {
  validateForm(()=>{
    validateTemplateForm(()=>{
      submitAttrs.value.loading = true
      doOpenplatformOpenapiBatchQuery()
          .then(res =>{
            let dataResult = res.data.data
            reactiveData.openplatformOpenapiBatchQueryRecordId = dataResult.id
            loadTableData(reactiveData.openplatformOpenapiBatchQueryRecordId)
            alert('查询成功,您可在当前页面刷新结果状态或稍候前往批量查询记录查看结果')
          }).finally(()=> {
        submitAttrs.value.loading = false
      })
    })
  })
  // 不管第一个form验证结果怎能样，都要验证一下模板表单，否则要第一个from验证不通过的情况下，模板表单不会验证
  validateTemplateForm(()=>{})
}
const validateForm = (callback) =>{
  if (formRef.value) {
    formRef.value.formRef.validate((valid, fields) => {
      if (valid) {
        callback()
      }
    })
  }
}
const validateTemplateForm = (callback) => {
  if (templateFormRef.value) {
    templateFormRef.value.formRef.validate((valid, fields) => {
      if (valid) {
        callback()
      }
    })
  }
}
// 数据查询
const doOpenplatformOpenapiBatchQuery = () => {
  let dataForm = new FormData()
  for (let formKey in reactiveData.form) {
    dataForm.append(formKey, reactiveData.form[formKey])
  }

  dataForm.append('file', reactiveData.templateform.file[0].raw)
  dataForm.append('remark', reactiveData.templateform.remark)

  return batchQuery(dataForm)
}


const buttonFormOnResetForm = ()=>{
  // if (formRef.value) {
  //   formRef.value.resetForm()
  // }
  if (templateFormRef.value) {
    templateFormRef.value.resetForm()
  }
}
// 下载批量查询模板,按钮 loading 状态
const downloadBatchQueryTemplateButtonLoading = ref(false)
// 下载批量查询模板
const downloadBatchQueryTemplateMethod = ()=>{
  validateForm(()=>{
    downloadBatchQueryTemplateButtonLoading.value = true
    let dataForm = {
    }
    for (let formKey in reactiveData.form) {
      dataForm[formKey] = reactiveData.form[formKey]
    }
    downloadBatchQueryTemplate(dataForm).then(res => {
      let data = res.data
      let contentType = extractContentType(res)
      let fileName = extractFileName(res)
      fileName = decodeURIComponent(fileName)
      downloadFileByData(data,contentType,fileName)
    }).finally(()=> {
      downloadBatchQueryTemplateButtonLoading.value = false
    })
  })
}
/**
 * 加载表格数据
 * @param openplatformOpenapiBatchQueryRecordId
 */
const loadTableData = (openplatformOpenapiBatchQueryRecordId) => {
  removeAll(reactiveData.tableData)
  getOpenplatformOpenapiBatchQueryRecordDetail(openplatformOpenapiBatchQueryRecordId).then(res => {
    reactiveData.tableData = [res.data.data]
  })
}
/**
 * 刷新表格数据
 * @param openplatformOpenapiBatchQueryRecordId
 */
const refreshTableData = (openplatformOpenapiBatchQueryRecordId) => {
  return getOpenplatformOpenapiBatchQueryRecordDetail(openplatformOpenapiBatchQueryRecordId).then(res => {
    for (let i = 0; i < reactiveData.tableData.length; i++) {
      if (reactiveData.tableData[i].id == openplatformOpenapiBatchQueryRecordId) {
        replace(reactiveData.tableData,i,res.data.data)
      }
    }
    return Promise.resolve(res)
  })
}
/**
 * 获取批量查询记录详情
 * @param openplatformOpenapiBatchQueryRecordId
 */
const getOpenplatformOpenapiBatchQueryRecordDetail = (openplatformOpenapiBatchQueryRecordId) => {
  return detail({id: openplatformOpenapiBatchQueryRecordId})
}
// 表格操作按钮
const getTableRowButtons = ({row, column, $index}) => {
  if($index < 0){
    return []
  }
  let idData = {id: row.id}
  let recordIdData = {openplatformOpenapiBatchQueryRecordId: row.id}

  let tableRowButtons = [
    {
      txt: '刷新',
      text: true,
      // 刷新操作
      method(){
        return refreshTableData(reactiveData.openplatformOpenapiBatchQueryRecordId)
      }
    },
    {
      txt: '下载',
      text: true,
      disabled: row.executeStatusDictValue != 'finished',
      disabledReason: row.executeStatusDictValue != 'finished' ? '执行完成后才可能下载' : undefined,
      // 刷新操作
      method(){
        let downloadUrl = getFinalDownloadUrl(row.exportFileUrl)
        let exportFileName = extractDownloadFileNameByUrl(downloadUrl)
        return download(downloadUrl).then(res => {
          let data = res.data
          let contentType = extractContentType(res)
          let fileName = decodeURIComponent(exportFileName)
          downloadFileByData(data,contentType,fileName)
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '查看详情',
      text: true,
      position: 'more',
      permission: 'admin:web:openplatformOpenapiBatchQueryRecordDetail:pageQuery',
      // 跳转到编辑
      route: {path: '/admin/openplatformOpenapiBatchQueryRecordDetail1',query: recordIdData}
    },
    {
      txt: '删除',
      text: true,
      position: 'more',
      methodConfirmText: `确定要删除当前数据吗？删除后您可以前往批量查询记录查看结果`,
      // 删除操作
      method(){
        reactiveData.openplatformOpenapiBatchQueryRecordId = null
        removeAll(reactiveData.tableData)
      }
    }
  ]

  return tableRowButtons
}
</script>
<template>
  <!-- 查询表单 -->
  <PtForm ref="formRef" :form="reactiveData.form"
          labelWidth="120"
          defaultButtonsShow=""
          inline
          :layout="2"
          :comps="reactiveData.formComps">
  </PtForm>
  <!-- 模板上传查询表单 -->
  <PtForm ref="templateFormRef" :form="reactiveData.templateform"
          labelWidth="120"
          defaultButtonsShow=""
          inline
          :layout="1"
          :comps="reactiveData.templateformComps">
  </PtForm>

  <!-- 按钮表单 -->
  <PtForm  :form="[]"
           labelWidth="120"
           :method="submitMethod"
           defaultButtonsShow="submit,reset"
           :submitAttrs="submitAttrs"
           inline
           :showButtonItem="true"
           :onResetForm="buttonFormOnResetForm"
           :comps="[]">
    <template #buttons>
      <PtButton permission="admin:web:openplatformOpenapi:batchQuery" :loading="downloadBatchQueryTemplateButtonLoading" :method="downloadBatchQueryTemplateMethod">下载模板</PtButton>
    </template>
  </PtForm>

  <div v-if="reactiveData.openplatformOpenapiBatchQueryRecordId">
    <!-- 查询后的结果，可以进行状态查看 -->
  <PtTable ref="tableRef"
           default-expand-all
           :data="reactiveData.tableData"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="220">
        <template #default="{row, column, $index}">
          <PtButtonGroup :options="getTableRowButtons({row, column, $index})" :dropdownTriggerButtonOptions="{  text: true,buttonText: '更多'}">
          </PtButtonGroup>
        </template>
      </el-table-column>
    </template>
  </PtTable>
    <el-alert title="注意：以上表格仅展示最后一次查询的查询结果" type="info" style="width: 350px;margin-top: 10px;"/>
  </div>
  <!-- 子级路由 -->
  <PtRouteViewPopover :level="3"></PtRouteViewPopover>
</template>


<style scoped>

</style>
