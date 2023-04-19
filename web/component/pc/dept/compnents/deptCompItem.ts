import {list as deptListApi} from "../api/admin/deptAdminApi";
import {list as deptTreeListApi} from "../api/admin/deptTreeAdminApi";
import {list as deptTreeNameListApi} from "../api/admin/deptTreeNameAdminApi";

export const useCascaderDeptCompItem = ({fieldName= 'parentId',required=false,label= '父级'})=>{
    return         {
        field: {
            name: fieldName
        },
        element: {
            comp: 'PtCascader',
            formItemProps: {
                label: label,
                required: required
            },
            compProps: {
                clearable: true,
                // 加载数据
                dataMethod: () => { return deptListApi({})},
                dataMethodResultHandleConvertToTree: true,
            }
        }
    }
}
export const useCascaderDeptTreeCompItem = ({fieldName= 'parentId',required=false,label= '父级',propsAttr={}})=>{
    return {
        field: {
            name: fieldName
        },
        element: {
            comp: 'PtCascader',
            formItemProps: {
                label: label,
                required: required
            },
            compProps: {
                clearable: true,
                // 加载数据
                dataMethod: () => { return deptTreeListApi({})},
                dataMethodResultHandleConvertToTree: true,
                props: propsAttr
            }
        }
    }
}

export const useSelectDeptTreeNameCompItem = ({}) => {
    return       {
        field: {
            name: 'deptTreeNameId',
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: '部门树名称',
            },
            compProps: {
                // 加载数据
                dataMethod: deptTreeNameListApi
            }
        }
    }
}