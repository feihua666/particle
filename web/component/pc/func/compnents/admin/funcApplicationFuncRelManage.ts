import {list as funcApplicationListApi} from "../../api/application/admin/funcApplicationAdminApi";
import {list as funcListApi} from "../../api/admin/funcAdminApi";

export const pageFormItems = [
      {
        field: {
          name: 'funcApplicationId',
        },
        element: {
          comp: 'PtCascader',
          formItemProps: {
            label: '功能应用',
          },
          compProps: {
            // 加载数据
            dataMethod: () => { return funcApplicationListApi({})},
            dataMethodResultHandleConvertToTree: true,
          }
        }
      },
  {
    field: {
      name: 'funcId'
    },
    element: {
      comp: 'PtCascader',
      formItemProps: {
        label: '功能',
      },
      compProps: {
        // 加载数据
        dataMethod: () => { return funcListApi({})},
        dataMethodResultHandleConvertToTree: true,
      }
    }
  },
]
export const addPageFormItems = [

  {
    field: {
      name: 'funcApplicationId',
    },
    element: {
      comp: 'PtCascader',
      formItemProps: {
        label: '功能应用',
        required: true,
      },
      compProps: {
        // 加载数据
        dataMethod: () => { return funcApplicationListApi({})},
        dataMethodResultHandleConvertToTree: true,
      }
    }
  },
  {
    field: {
      name: 'funcId'
    },
    element: {
      comp: 'PtCascader',
      formItemProps: {
        label: '功能',
        required: true,
      },
      compProps: {
        // 加载数据
        dataMethod: () => { return funcListApi({})},
        dataMethodResultHandleConvertToTree: true,
      }
    }
  },
]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

