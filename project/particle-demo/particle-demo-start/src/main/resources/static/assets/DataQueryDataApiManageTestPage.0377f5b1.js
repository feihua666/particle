import{h as g,i as b}from"./dataQueryDataApiAdminApi.47519217.js";import{d as C,r as i,a as h,a7 as B,b as c,o as A,c as V,e as d,F as _,X as S}from"./index.858f5c96.js";import{l as J,m as f}from"./dataQueryDatasourceApiManage.5d3aa8d4.js";import"./dataQueryDatasourceAdminApi.607f45ad.js";import"./dataqueryProviderCompItem.a81d04b7.js";import"./dataQueryProviderAdminApi.ce6e5899.js";const I=C({name:"DataQueryDataApiManageTestPage"}),O=C({...I,props:{dataQueryDataApiId:{type:String,required:!0}},setup(P){const F=P;let m=(t,e="success")=>{S({showClose:!0,message:t,type:e,showIcon:!0,grouping:!0})};const r=i(""),s=i(""),a=h({form:{param:""},formComps:[{field:{name:"name",valueChange({form:t,formData:e}){B(()=>{e.name&&(t.param=e.name.content)})}},element:{comp:"PtSelect",formItemProps:{label:"\u7528\u4F8B\u540D\u79F0",tips:"\u9009\u62E9\u4E00\u4E2A\u7528\u4F8B\u6570\u636E\u4F5C\u4E3A\u53C2\u6570\uFF0C\u9ED8\u8BA4\u9009\u4E2D\u7B2C\u4E00\u4E2A"},compProps:{clearable:!0,defaultValueItem:t=>!0,props:{value:"name"},dataMethod:()=>g({id:F.dataQueryDataApiId}).then(t=>{let e=t.data.data;s.value=e.url;let u=e.adaptTypeDictValue,o=p(e);return u!="single_direct"?Promise.resolve(o):J({id:e.dataQueryDatasourceApiId}).then(n=>{let l=n.data.data;e.inParamTypeDictValue&&(l.inParamTypeDictValue=e.inParamTypeDictValue),e.inParamTestCaseDataConfigJson&&(l.inParamTestCaseDataConfigJson=e.inParamTestCaseDataConfigJson);let E=p(l);return Promise.resolve(E)})})}}},{field:{name:"param"},element:{comp:"el-input",formItemProps:{label:"\u53C2\u6570",displayBlock:!0},compProps:{type:"textarea",rows:15}}}],testResult:{value:""}}),T=i({buttonText:"\u67E5\u8BE2",permission:"admin:web:dataQueryDataApi:test",beforeMethod:()=>{let t=a.form.param;if(r.value){if(!t){m("\u63A5\u53E3\u9700\u8981\u8BF7\u6C42\u53C2\u6570\uFF0C\u8BF7\u586B\u5199","error");return}try{t=f[r.value](t)}catch{m("\u53C2\u6570\u683C\u5F0F\u9519\u8BEF\uFF0C\u8BF7\u68C0\u67E5\uFF01","error");return}}return!0}}),D=t=>{let e=t.param;r.value?e=f[r.value](e):e=null;let u={url:s.value,param:e};return r.value=="queryString"&&(u={url:s.value,queryString:e}),b(u)},v=t=>{let e=t.data;return a.testResult.value=JSON.stringify(e,null,"   "),"\u6570\u636E\u67E5\u8BE2\u6210\u529F"},y=()=>{a.testResult.value=""},p=t=>{r.value=t.inParamTypeDictValue;let e=[];return t.inParamTestCaseDataConfigJson&&(e=JSON.parse(t.inParamTestCaseDataConfigJson).inParamTestCases),e};return(t,e)=>{const u=c("PtForm"),o=c("PtAceEditor");return A(),V(_,null,[d(u,{form:a.form,method:D,methodSuccess:v,defaultButtonsShow:"submit,reset",submitAttrs:T.value,labelWidth:"100",buttonsTeleportProps:t.$route.meta.formButtonsTeleportProps,onResetForm:y,inline:"",layout:1,comps:a.formComps},null,8,["form","submitAttrs","buttonsTeleportProps","comps"]),d(o,{modelValue:a.testResult.value,"onUpdate:modelValue":e[0]||(e[0]=n=>a.testResult.value=n),mode:"ace/mode/json",class:"pt-width-100-pc"},null,8,["modelValue"])],64)}}});export{O as default};