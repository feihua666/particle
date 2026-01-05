<script setup name="AdminComponentDependencyManageDependComponentAssignComponentPage" lang="ts">
/**
 * 依赖组件分配源组件页面
 */
import {reactive ,ref} from 'vue'
import {queryComponentIdsByDependComponentId, dependComponentAssignComponent as dependComponentAssignComponentApi} from "../../api/admin/adminComponentDependencyAdminApi"
import {list as componentListApi} from "../../api/admin/adminComponentAdminApi";
import {useSelectAdminComponentCompItem} from "../../components/componentAdminCompItem";

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  dependComponentId: {
    type: String
  },
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {dependComponentId: props.dependComponentId},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    [
      useSelectAdminComponentCompItem({fieldName: 'dependComponentId',required: true}),
      {
        field: {
          name: 'isRequired',
          value: true,
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否必需',
            required: true,
            tips: '是否一定需要依赖的组件，如果不是必须的，那就是可选的',
          },
          compProps: {
            activeText: '必需',
            inactiveText: '可选',
          }
        }
      },
      {
        field: {
          name: 'checkedComponentIds',
          value: []
        },
        element: {
          comp: 'PtTree',
          formItemProps: {
            label: '源组件',
            required: true,
          },
          compProps: ({form})=> {
            return {
              // 加载初始化选中数据
              dataInitMethod: ({param}) => {
                let dependComponentId = param.dependComponentId
                if(dependComponentId){
                  return queryComponentIdsByDependComponentId({id: dependComponentId})
                }
                // 空函数不查询
                return {data: []}
              },
              // dataInitMethod 参数
              dataInitMethodParam: {dependComponentId: form.dependComponentId},
              // 可用数据列表
              dataMethod: componentListApi,
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
  permission: 'admin:web:adminComponentDependency:dependComponentAssignComponent',
})
// 提交按钮
const submitMethod = () => {
  return dependComponentAssignComponentApi
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
