import{h as b,i as h}from"./dataQueryDataApiAdminApi-ByQtG2-r.js";import{d as V,r as u,a as A,Q as S,b as c,o as J,c as _,e as d,F as I,G as w}from"./index-_8rL1G8m.js";import{l as M,m as f}from"./dataQueryDatasourceApiManage-DUUysU_G.js";import"./dataQueryDatasourceAdminApi-BUk5qnX0.js";import"./dataqueryProviderCompItem-CXdVZONI.js";import"./dataQueryProviderAdminApi-CC8a5fD0.js";const E=V({__name:"DataQueryDataApiManageTestPage",props:{dataQueryDataApiId:{type:String,required:!0}},setup(P){let m=(t,e="success")=>{w({showClose:!0,message:t,type:e,showIcon:!0,grouping:!0})};const T=P,s=u(""),o=u(""),a=A({form:{param:""},formComps:[{field:{name:"name",valueChange({form:t,formData:e}){S(()=>{e.name&&(t.param=e.name.content)})}},element:{comp:"PtSelect",formItemProps:{label:"用例名称",tips:"选择一个用例数据作为参数，默认选中第一个"},compProps:{clearable:!0,defaultValueItem:t=>!0,props:{value:"name"},dataMethod:()=>b({id:T.dataQueryDataApiId}).then(t=>{let e=t.data.data;o.value=e.url;let r=e.adaptTypeDictValue,n=p(e);return r!="single_direct"?Promise.resolve(n):M({id:e.dataQueryDatasourceApiId}).then(l=>{let i=l.data.data;e.inParamTypeDictValue&&(i.inParamTypeDictValue=e.inParamTypeDictValue),e.inParamTestCaseDataConfigJson&&(i.inParamTestCaseDataConfigJson=e.inParamTestCaseDataConfigJson);let D=p(i);return Promise.resolve(D)})})}}},{field:{name:"param"},element:{comp:"el-input",formItemProps:{label:"参数",displayBlock:!0},compProps:{type:"textarea",rows:15}}}],testResult:{value:""}}),v=u({buttonText:"查询",permission:"admin:web:dataQueryDataApi:test",beforeMethod:()=>{let t=a.form.param;if(s.value){if(!t){m("接口需要请求参数，请填写","error");return}try{t=f[s.value](t)}catch{m("参数格式错误，请检查！","error");return}}return!0}}),C=t=>{let e=t.param;s.value?e=f[s.value](e):e=null;let r={url:o.value,param:e};return s.value=="queryString"&&(r={url:o.value,queryString:e}),h(r)},y=t=>{let e=t.data;return a.testResult.value=JSON.stringify(e,null,"   "),"数据查询成功"},g=()=>{a.testResult.value=""},p=t=>{s.value=t.inParamTypeDictValue;let e=[];return t.inParamTestCaseDataConfigJson&&(e=JSON.parse(t.inParamTestCaseDataConfigJson).inParamTestCases),e};return(t,e)=>{const r=c("PtForm"),n=c("PtAceEditor");return J(),_(I,null,[d(r,{form:a.form,method:C,methodSuccess:y,defaultButtonsShow:"submit,reset",submitAttrs:v.value,labelWidth:"100",buttonsTeleportProps:t.$route.meta.formButtonsTeleportProps,onResetForm:g,inline:"",layout:1,comps:a.formComps},null,8,["form","submitAttrs","buttonsTeleportProps","comps"]),d(n,{modelValue:a.testResult.value,"onUpdate:modelValue":e[0]||(e[0]=l=>a.testResult.value=l),mode:"ace/mode/json",class:"pt-width-100-pc"},null,8,["modelValue"])],64)}}});export{E as default};