<script setup name="NavigationSiteCategoryRelManageNavigationCategoryAssignNavigationSitePage" lang="ts">
/**
 * 导航分类分配导航网站页面
 */
import {reactive, ref} from 'vue'
import {
  navigationCategoryAssignNavigationSite as navigationCategoryAssignNavigationSiteApi,
  queryNavigationSiteIdsByNavigationCategoryId
} from "../../api/admin/navigationSiteCategoryRelAdminApi"
import {useCascaderNavigationCategoryCompItem} from "../../components/navigationCategoryCompItem";
import {list as navigationSiteListApi} from "../../api/admin/navigationSiteAdminApi";
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  navigationCategoryId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {navigationCategoryId: props.navigationCategoryId},
  // 表单数据对象
  formData: {},
})
console.log(props.navigationCategoryId)
// 表单项
const formComps = ref(
    [
      useCascaderNavigationCategoryCompItem({fieldName: 'navigationCategoryId',label: '导航分类',required: true}),
      {
        field: {
          name: 'checkedNavigationSiteIds',
          value: []
        },
        element: {
          comp: 'PtTree',
          formItemProps: {
            label: '导航网站',
            required: true,
          },
          compProps: ({form})=> {
            return {
              checkStrictly: true,
              // 加载初始化选中数据
              dataInitMethod: ({param}) => {
                let navigationCategoryId = param.navigationCategoryId
                if(navigationCategoryId){
                  return queryNavigationSiteIdsByNavigationCategoryId({id: navigationCategoryId})
                }
                // 空函数不查询
                return {data: []}
              },
              // dataInitMethod 参数
              dataInitMethodParam: {navigationCategoryId: form.navigationCategoryId},
              // 可用数据列表
              dataMethod: navigationSiteListApi,
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
  permission: 'admin:web:navigationSiteCategoryRel:navigationCategoryAssignNavigationSite',
})
// 提交按钮
const submitMethod = () => {
  return navigationCategoryAssignNavigationSiteApi
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
