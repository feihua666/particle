import {useRemoteSelectRoleCompItem} from "../../../components/roleCompItem";
import {
  useRemoteSelectDataScopeCompItem,
  useSelectDataConstraintDataObjectCompItem, useSelectDataScopeCompItem
} from "../../../../dataconstraint/compnents/dataconstraintCompItem";

export const pageFormItems = [
  useRemoteSelectRoleCompItem({props:{},required: false}),
  useSelectDataConstraintDataObjectCompItem({props: {}}),
  useSelectDataScopeCompItem({props:{},required: false}),
]
export const useAddPageFormItems = ({props={}}) => {
  return [

    useRemoteSelectRoleCompItem({props,required: true}),
    useSelectDataConstraintDataObjectCompItem({props: {}}),
    useSelectDataScopeCompItem({props:{},required: false}),

  ]
}

// 更新和添加一致
export const useUpdatePageFormItems = useAddPageFormItems

