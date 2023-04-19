import {treeQueryComps} from "../../../treeQueryComps";
import {useCascaderDeptCompItem, useCascaderDeptTreeCompItem, useSelectDeptTreeNameCompItem} from "../deptCompItem";

export const pageFormItems = [

  useCascaderDeptCompItem({fieldName: 'deptId',label: '部门'}),
  useSelectDeptTreeNameCompItem({}),

  useCascaderDeptTreeCompItem({propsAttr: {label: 'deptName'}}),
  ...treeQueryComps
]
export const addPageFormItems = [
  useCascaderDeptCompItem({fieldName: 'deptId',label: '部门'}),

  useSelectDeptTreeNameCompItem({}),
  useCascaderDeptTreeCompItem({propsAttr: {label: 'deptName'}}),

      {
        field: {
          name: 'remark',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '描述',
            
          },
          compProps: {
            clearable: true,
          }
        }
      },

]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

