<script setup name="DataCompanyVcProductCompetitiveProductRelManageCompanyVcProductAssignCompanyVcCompetitiveProductPage" lang="ts">
/**
 * 企业融资产品表ID分配企业竞品页面
 */
import {reactive ,ref} from 'vue'
import {queryCompanyVcCompetitiveProductIdsByCompanyVcProductId, companyVcProductAssignCompanyVcCompetitiveProduct as companyVcProductAssignCompanyVcCompetitiveProductApi} from "../../../api/company/admin/dataCompanyVcProductCompetitiveProductRelAdminApi"
// 以下两项为自动生成，不准确，请手动根据实际情况修改
import {useRemoteSelectCompanyVcProductCompItem, remoteSelectCompanyVcProductProps} from "../../components/companyVcProductCompItem";
import {list as companyVcCompetitiveProductListApi} from "../../api/admin/companyVcCompetitiveProductAdminApi";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...remoteSelectCompanyVcProductProps,
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
      useRemoteSelectCompanyVcProductCompItem({props,required: true}),
      {
        field: {
          name: 'checkedCompanyVcCompetitiveProductIds',
          value: []
        },
        element: {
          comp: 'PtTree',
          formItemProps: {
            label: '企业竞品',
            required: true,
          },
          compProps: ({form})=> {
            return {
              // 加载初始化选中数据
              dataInitMethod: ({param}) => {
                let companyVcProductId = param.companyVcProductId
                if(companyVcProductId){
                  return queryCompanyVcCompetitiveProductIdsByCompanyVcProductId({id: companyVcProductId})
                }
                // 空函数不查询
                return {data: []}
              },
              // dataInitMethod 参数
              dataInitMethodParam: {companyVcProductId: form.companyVcProductId},
              // 可用数据列表
              dataMethod: companyVcCompetitiveProductListApi,
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
  permission: 'admin:web:dataCompanyVcProductCompetitiveProductRel:companyVcProductAssignCompanyVcCompetitiveProduct',
})
// 提交按钮
const submitMethod = () => {
  return companyVcProductAssignCompanyVcCompetitiveProductApi
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