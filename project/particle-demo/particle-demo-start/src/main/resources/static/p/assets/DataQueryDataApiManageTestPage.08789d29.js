import{d as C,r as i,a as E,a8 as b,b as c,o as h,c as A,e as B,f as d,F as _,X as V,_ as S}from"./index.3ad8a272.js";import{h as w,i as J}from"./dataQueryDataApiAdminApi.7ddabab1.js";import{l as M,m as f}from"./dataQueryDatasourceApiManage.8dda0fa4.js";import"./dataQueryDatasourceAdminApi.0c718d73.js";import"./dataqueryProviderCompItem.2777c275.js";import"./dataQueryProviderAdminApi.a0748a53.js";const I=C({name:"DataQueryDataApiManageTestPage"}),Q=C({...I,props:{dataQueryDataApiId:{type:String,required:!0}},setup(P){const D=P;let m=(t,e="success")=>{V({showClose:!0,message:t,type:e,showIcon:!0,grouping:!0})};const r=i(""),s=i(""),a=E({form:{param:""},formComps:[{field:{name:"name",valueChange({form:t,formData:e}){b(()=>{e.name&&(t.param=e.name.content)})}},element:{comp:"PtSelect",formItemProps:{label:"\u7528\u4F8B\u540D\u79F0",tips:"\u9009\u62E9\u4E00\u4E2A\u7528\u4F8B\u6570\u636E\u4F5C\u4E3A\u53C2\u6570\uFF0C\u9ED8\u8BA4\u9009\u4E2D\u7B2C\u4E00\u4E2A"},compProps:{clearable:!0,defaultValueItem:t=>!0,props:{value:"name"},dataMethod:()=>w({id:D.dataQueryDataApiId}).then(t=>{let e=t.data.data;s.value=e.url;let u=e.adaptTypeDictValue,o=p(e);return u!="single_direct"?Promise.resolve(o):M({id:e.dataQueryDatasourceApiId}).then(n=>{let l=n.data.data;e.inParamTypeDictValue&&(l.inParamTypeDictValue=e.inParamTypeDictValue),e.inParamTestCaseDataConfigJson&&(l.inParamTestCaseDataConfigJson=e.inParamTestCaseDataConfigJson);let F=p(l);return Promise.resolve(F)})})}}},{field:{name:"param"},element:{comp:"el-input",formItemProps:{label:"\u53C2\u6570",displayBlock:!0},compProps:{type:"textarea",rows:15}}}],testResult:{value:""}}),T=i({buttonText:"\u67E5\u8BE2",permission:"admin:web:dataQueryDataApi:test",beforeMethod:()=>{let t=a.form.param;if(r.value){if(!t){m("\u63A5\u53E3\u9700\u8981\u8BF7\u6C42\u53C2\u6570\uFF0C\u8BF7\u586B\u5199","error");return}try{t=f[r.value](t)}catch{m("\u53C2\u6570\u683C\u5F0F\u9519\u8BEF\uFF0C\u8BF7\u68C0\u67E5\uFF01","error");return}}return!0}}),g=t=>{let e=t.param;r.value?e=f[r.value](e):e=null;let u={url:s.value,param:e};return r.value=="queryString"&&(u={url:s.value,queryString:e}),J(u)},y=t=>{let e=t.data;return a.testResult.value=JSON.stringify(e,null,"   "),"\u6570\u636E\u67E5\u8BE2\u6210\u529F"},v=()=>{a.testResult.value=""},p=t=>{r.value=t.inParamTypeDictValue;let e=[];return t.inParamTestCaseDataConfigJson&&(e=JSON.parse(t.inParamTestCaseDataConfigJson).inParamTestCases),e};return(t,e)=>{const u=c("PtForm"),o=c("PtAceEditor");return h(),A(_,null,[B(" \u67E5\u8BE2\u8868\u5355 "),d(u,{form:a.form,method:g,methodSuccess:y,defaultButtonsShow:"submit,reset",submitAttrs:T.value,labelWidth:"100",buttonsTeleportProps:t.$route.meta.formButtonsTeleportProps,onResetForm:v,inline:"",layout:1,comps:a.formComps},null,8,["form","submitAttrs","buttonsTeleportProps","comps"]),d(o,{modelValue:a.testResult.value,"onUpdate:modelValue":e[0]||(e[0]=n=>a.testResult.value=n),mode:"ace/mode/json",class:"pt-width-100-pc"},null,8,["modelValue"])],64)}}}),j=S(Q,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/dataquery/views/dataapi/admin/DataQueryDataApiManageTestPage.vue"]]);export{j as default};