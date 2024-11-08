<script setup name="NavigationSiteTagRelManageNavigationSiteTagAssignNavigationSitePage" lang="ts">
/**
 * 网站标签分配网站页面
 */
import {reactive ,ref} from 'vue'
import {queryNavigationSiteIdsByNavigationSiteTagId, navigationSiteTagAssignNavigationSite as navigationSiteTagAssignNavigationSiteApi} from "../../api/admin/navigationSiteTagRelAdminApi"
import {useRemoteSelectNavigationSiteTagCompItem, remoteSelectNavigationSiteTagProps} from "../../components/navigationSiteTagCompItem";
import {list as navigationSiteListApi} from "../../api/admin/navigationSiteAdminApi";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...remoteSelectNavigationSiteTagProps,
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
      useRemoteSelectNavigationSiteTagCompItem({props,required: true}),
      {
        field: {
          name: 'checkedNavigationSiteIds',
          value: []
        },
        element: {
          comp: 'PtTree',
          formItemProps: {
            label: '网站',
            required: true,
          },
          compProps: ({form})=> {
            return {
              // 加载初始化选中数据
              dataInitMethod: ({param}) => {
                let navigationSiteTagId = param.navigationSiteTagId
                if(navigationSiteTagId){
                  return queryNavigationSiteIdsByNavigationSiteTagId({id: navigationSiteTagId})
                }
                // 空函数不查询
                return {data: []}
              },
              // dataInitMethod 参数
              dataInitMethodParam: {navigationSiteTagId: form.navigationSiteTagId},
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
  permission: 'admin:web:navigationSiteTagRel:navigationSiteTagAssignNavigationSite',
})
// 提交按钮
const submitMethod = () => {
  return navigationSiteTagAssignNavigationSiteApi
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
