<script setup name="DataCompanyVcFinancingInvestInstitutionRelManageCompanyVcFinancingAssignCompanyVcInvestInstitutionPage" lang="ts">
/**
 * 企业融资表ID分配企业投资机构表页面
 */
import {reactive ,ref} from 'vue'
import {queryCompanyVcInvestInstitutionIdsByCompanyVcFinancingId, companyVcFinancingAssignCompanyVcInvestInstitution as companyVcFinancingAssignCompanyVcInvestInstitutionApi} from "../../../api/company/admin/dataCompanyVcFinancingInvestInstitutionRelAdminApi"
// 以下两项为自动生成，不准确，请手动根据实际情况修改
import {useRemoteSelectCompanyVcFinancingCompItem, remoteSelectCompanyVcFinancingProps} from "../../components/companyVcFinancingCompItem";
import {list as companyVcInvestInstitutionListApi} from "../../api/admin/companyVcInvestInstitutionAdminApi";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...remoteSelectCompanyVcFinancingProps,
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
      useRemoteSelectCompanyVcFinancingCompItem({props,required: true}),
      {
        field: {
          name: 'checkedCompanyVcInvestInstitutionIds',
          value: []
        },
        element: {
          comp: 'PtTree',
          formItemProps: {
            label: '企业投资机构表',
            required: true,
          },
          compProps: ({form})=> {
            return {
              // 加载初始化选中数据
              dataInitMethod: ({param}) => {
                let companyVcFinancingId = param.companyVcFinancingId
                if(companyVcFinancingId){
                  return queryCompanyVcInvestInstitutionIdsByCompanyVcFinancingId({id: companyVcFinancingId})
                }
                // 空函数不查询
                return {data: []}
              },
              // dataInitMethod 参数
              dataInitMethodParam: {companyVcFinancingId: form.companyVcFinancingId},
              // 可用数据列表
              dataMethod: companyVcInvestInstitutionListApi,
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
  permission: 'admin:web:dataCompanyVcFinancingInvestInstitutionRel:companyVcFinancingAssignCompanyVcInvestInstitution',
})
// 提交按钮
const submitMethod = () => {
  return companyVcFinancingAssignCompanyVcInvestInstitutionApi
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
