<script setup name="DataCompanyVcProductCompetitiveProductRelManageCompanyVcCompetitiveProductAssignCompanyVcProductPage" lang="ts">
/**
 * 企业竞品分配企业融资产品表ID页面
 */
import {reactive ,ref} from 'vue'
import {queryCompanyVcProductIdsByCompanyVcCompetitiveProductId, companyVcCompetitiveProductAssignCompanyVcProduct as companyVcCompetitiveProductAssignCompanyVcProductApi} from "../../../api/company/admin/dataCompanyVcProductCompetitiveProductRelAdminApi"
// 以下两项为自动生成，不准确，请手动根据实际情况修改
import {useRemoteSelectCompanyVcCompetitiveProductCompItem, remoteSelectCompanyVcCompetitiveProductProps} from "../../components/companyVcCompetitiveProductCompItem";
import {list as companyVcProductListApi} from "../../api/admin/companyVcProductAdminApi";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...remoteSelectCompanyVcCompetitiveProductProps,
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    [
      useRemoteSelectCompanyVcCompetitiveProductCompItem({props,required: true}),
      {
        field: {
          name: 'checkedCompanyVcProductIds',
          value: []
        },
        element: {
          comp: 'PtTree',
          formItemProps: {
            label: '企业融资产品表ID',
            required: true,
          },
          compProps: ({form})=> {
            return {
              // 加载初始化选中数据
              dataInitMethod: ({param}) => {
                let companyVcCompetitiveProductId = param.companyVcCompetitiveProductId
                if(companyVcCompetitiveProductId){
                  return queryCompanyVcProductIdsByCompanyVcCompetitiveProductId({id: companyVcCompetitiveProductId})
                }
                // 空函数不查询
                return {data: []}
              },
              // dataInitMethod 参数
              dataInitMethodParam: {companyVcCompetitiveProductId: form.companyVcCompetitiveProductId},
              // 可用数据列表
              dataMethod: companyVcProductListApi,
              dataMethodResultHandleConvertToTree: true,
              showCheckbox: true
            }
          }
        }
      },
    ]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认',
  permission: 'admin:web:dataCompanyVcProductCompetitiveProductRel:companyVcCompetitiveProductAssignCompanyVcProduct',
})
// 提交按钮
const submitMethod = () => {
  return companyVcCompetitiveProductAssignCompanyVcProductApi
}
// 成功提示语
const submitMethodSuccess = () => {
  return '分配成功，请刷新数据查看'
}

</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="80"
          :method="submitMethod()"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :layout="1"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>