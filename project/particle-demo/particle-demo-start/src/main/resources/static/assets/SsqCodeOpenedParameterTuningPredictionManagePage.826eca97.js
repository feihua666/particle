import{c as B}from"./ssqCodeOpenedAdminApi.c48a07a5.js";import{a as P}from"./ssqCodeOpenedManage.847bc0bc.js";import{d,r as i,a as g,b as t,o as A,c as h,e as u,w as m,F as E}from"./index.858f5c96.js";const M=d({name:"SsqCodeOpenedPredictionParameterTuningManagePage"}),q=d({...M,setup(T){const r=i(null),e=g({form:{},formComps:P,tableColumns:[{prop:"openedCount",label:"\u5F53\u524D\u533A\u5DF2\u5F00\u5956\u6570\u91CF"},{prop:"regionSeqNoMin",label:"\u5F53\u524D\u533A\u5E8F\u53F7\u6700\u5C0F\u503C"},{prop:"regionSeqNoMax",label:"\u5F53\u524D\u533A\u5E8F\u53F7\u6700\u5927\u503C"},{prop:"regionNo",label:"\u533A\u5E8F\u53F7"},{prop:"regionCount",label:"\u603B\u533A\u6570"},{prop:"predictSeqNoMin",label:"\u9884\u6D4B\u5E8F\u53F7\u6700\u5C0F\u503C"},{prop:"predictSeqNoMax",label:"\u9884\u6D4B\u5E8F\u53F7\u6700\u5927\u503C"},{prop:"predictPercent",label:"\u9884\u6D4B\u57FA\u7EBF\u767E\u5206\u6BD4\u6570"},{prop:"isPredictHit",label:"\u662F\u5426\u9884\u6D4B\u547D\u4E2D"},{prop:"predictHitOpenedPhase",label:"\u9884\u6D4B\u547D\u4E2D\u671F\u53F7"},{prop:"predictHitOpenedPhaseSize",label:"\u9884\u6D4B\u547D\u4E2D\u4E2A\u6570"}]}),l=i({buttonText:"\u5F00\u59CB\u8BA1\u7B97",loading:!1,permission:"admin:web:ssqCodeOpened:predictionParameterTuning"}),c=()=>{r.value.refreshData()},b=()=>B({...e.form}),F=({row:s,column:o,$index:n})=>n<0?[]:(s.id,[]);return(s,o)=>{const n=t("PtForm"),p=t("PtButtonGroup"),f=t("el-table-column"),D=t("PtTable");return A(),h(E,null,[u(n,{form:e.form,method:c,defaultButtonsShow:"submit,reset",submitAttrs:l.value,doDataMethodOnMounted:!1,inline:"",labelWidth:"120",comps:e.formComps},null,8,["form","submitAttrs","comps"]),u(D,{ref_key:"tableRef",ref:r,"default-expand-all":"",dataMethod:b,onDataMethodDataLoading:o[0]||(o[0]=a=>l.value.loading=a),doDataMethodOnMounted:!1,columns:e.tableColumns},{defaultAppend:m(()=>[u(f,{label:"\u64CD\u4F5C",width:"180"},{default:m(({row:a,column:C,$index:_})=>[u(p,{options:F({row:a,column:C,$index:_})},null,8,["options"])]),_:1})]),_:1},8,["columns"])],64)}}});export{q as default};