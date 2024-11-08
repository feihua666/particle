<script setup name="NavigationSiteTagRelManageNavigationSiteAssignNavigationSiteTagPage" lang="ts">
/**
 * 网站分配网站标签页面
 */
import {reactive ,ref} from 'vue'
import {queryNavigationSiteTagIdsByNavigationSiteId, navigationSiteAssignNavigationSiteTag as navigationSiteAssignNavigationSiteTagApi} from "../../api/admin/navigationSiteTagRelAdminApi"
import {useRemoteSelectNavigationSiteCompItem, remoteSelectNavigationSiteProps} from "../../components/navigationSiteCompItem";
import {list as navigationSiteTagListApi} from "../../api/admin/navigationSiteTagAdminApi";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  ...remoteSelectNavigationSiteProps,
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
      useRemoteSelectNavigationSiteCompItem({props,required: true}),
      {
        field: {
          name: 'checkedNavigationSiteTagIds',
          value: []
        },
        element: {
          comp: 'PtTree',
          formItemProps: {
            label: '网站标签',
            required: true,
          },
          compProps: ({form})=> {
            return {
              // 加载初始化选中数据
              dataInitMethod: ({param}) => {
                let navigationSiteId = param.navigationSiteId
                if(navigationSiteId){
                  return queryNavigationSiteTagIdsByNavigationSiteId({id: navigationSiteId})
                }
                // 空函数不查询
                return {data: []}
              },
              // dataInitMethod 参数
              dataInitMethodParam: {navigationSiteId: form.navigationSiteId},
              // 可用数据列表
              dataMethod: navigationSiteTagListApi,
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
  permission: 'admin:web:navigationSiteTagRel:navigationSiteAssignNavigationSiteTag',
})
// 提交按钮
const submitMethod = () => {
  return navigationSiteAssignNavigationSiteTagApi
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
