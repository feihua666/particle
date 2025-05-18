<script setup name="DataCompanyVcFinancingInvestInstitutionRelManageCompanyVcInvestInstitutionAssignCompanyVcFinancingPage" lang="ts">
/**
 * 企业投资机构表分配企业融资表ID页面
 */
import {reactive ,ref} from 'vue'
import {queryCompanyVcFinancingIdsByCompanyVcInvestInstitutionId, companyVcInvestInstitutionAssignCompanyVcFinancing as companyVcInvestInstitutionAssignCompanyVcFinancingApi} from "../../../api/company/admin/dataCompanyVcFinancingInvestInstitutionRelAdminApi"
// 以下两项为自动生成，不准确，请手动根据实际情况修改
import {useRemoteSelectCompanyVcInvestInstitutionCompItem, remoteSelectCompanyVcInvestInstitutionProps} from "../../components/companyVcInvestInstitutionCompItem";
import {list as companyVcFinancingListApi} from "../../api/admin/companyVcFinancingAdminApi";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...remoteSelectCompanyVcInvestInstitutionProps,
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
      useRemoteSelectCompanyVcInvestInstitutionCompItem({props,required: true}),
      {
        field: {
          name: 'checkedCompanyVcFinancingIds',
          value: []
        },
        element: {
          comp: 'PtTree',
          formItemProps: {
            label: '企业融资表ID',
            required: true,
          },
          compProps: ({form})=> {
            return {
              // 加载初始化选中数据
              dataInitMethod: ({param}) => {
                let companyVcInvestInstitutionId = param.companyVcInvestInstitutionId
                if(companyVcInvestInstitutionId){
                  return queryCompanyVcFinancingIdsByCompanyVcInvestInstitutionId({id: companyVcInvestInstitutionId})
                }
                // 空函数不查询
                return {data: []}
              },
              // dataInitMethod 参数
              dataInitMethodParam: {companyVcInvestInstitutionId: form.companyVcInvestInstitutionId},
              // 可用数据列表
              dataMethod: companyVcFinancingListApi,
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
  permission: 'admin:web:dataCompanyVcFinancingInvestInstitutionRel:companyVcInvestInstitutionAssignCompanyVcFinancing',
})
// 提交按钮
const submitMethod = () => {
  return companyVcInvestInstitutionAssignCompanyVcFinancingApi
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
