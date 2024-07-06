// 该文件内存放整个模块有组件需要单独抽出的内容
import {list as crmCompanyListApi} from "../api/company/admin/crmCompanyAdminApi";
import {list as crmDeptListApi} from "../api/company/admin/crmDeptAdminApi";
import {list as crmCustomerListApi} from "../api/customer/admin/crmCustomerAdminApi";
import {list as crmCustomerRelationDefineListApi} from "../api/ralation/admin/crmCustomerRelationDefineAdminApi";

import {list as crmCustomerTagListApi} from "../api/tag/admin/crmCustomerTagAdminApi";

export const useSelectCrmCustomerTagCompItem = ({fieldName= 'crmCustomerTagId',required=false,label= '标签'})=>{
    return         {
        field: {
            name: fieldName
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: label,
                required: required
            },
            compProps: {
                clearable: true,
                // 加载数据
                dataMethod: () => { return crmCustomerTagListApi({})},
            }
        }
    }
}

export const useSelectCrmCustomerRelationDefineCompItem = ({
                                                               fieldName= 'crmCustomerRelationDefineId',
                                                               required=false,
                                                               label= '客户关系定义',
                                                               isBidirectional = null,
    tips = null
})=>{
    return         {
        field: {
            name: fieldName
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: label,
                required: required,
                tips: tips
            },
            compProps: {
                clearable: true,
                // 加载数据
                dataMethod: () => { return crmCustomerRelationDefineListApi({isBidirectional})},
            }
        }
    }
}
export const useSelectCrmCustomerCompItem = ({fieldName= 'crmCustomerId',required=false,label= '客户'})=>{
    return         {
        field: {
            name: fieldName
        },
        element: {
            comp: 'PtSelect',
            formItemProps: {
                label: label,
                required: required
            },
            compProps: {
                clearable: true,
                // 加载数据
                dataMethod: () => { return crmCustomerListApi({})},
            }
        }
    }
}
export const useCascaderCrmCompanyCompItem = ({fieldName= 'parentId',required=false,label= '父级'})=>{
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
                dataMethod: () => { return crmCompanyListApi({})},
                dataMethodResultHandleConvertToTree: true,
            }
        }
    }
}

export const useCascaderCrmDeptCompItem = ({fieldName= 'parentId',required=false,label= '父级'})=>{
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
                dataMethod: () => { return crmDeptListApi({})},
                dataMethodResultHandleConvertToTree: true,
            }
        }
    }
}