import{b as g,s as A}from"./openplatformOpenapiCompItem-PgDqVVhz.js";import{a as _}from"./openplatformAppCompItem-DXCwBHcH.js";import{a as R}from"./openplatformDocApiAdminApi-D2XlYoOS.js";import{d as O,r as n,a as P,b as S,ap as q,o as p,c,e as u,aq as B,g as w,E as I,F as k,am as y}from"./index-BGwvwH0Y.js";import"./openplatformAppAdminApi-DiEZQgh-.js";const Q=O({__name:"OpenplatformOpenapiDataQueryPage",setup(x){const i=n(null),l=n(null),s=n(null),e=P({form:{},formComps:[_({label:"应用",required:!0,valueChange:({form:a,newValue:o})=>{o||(a.openplatformOpenapiId=null)},tips:" 1. 请先选择要使用的应用"}),g({fieldName:"openplatformOpenapiId",label:"开放接口",required:!0,disableGroup:!0,valueChange:({form:a,newValue:o})=>{C(o)},tips:" 2. 请选择要查询的接口"})],dynamicform:{},dynamicformComps:[],dynamicformCompsReady:!1,dynamicformLoading:!1,resultForm:{result:""},resultFormComps:[{field:{name:"result"},element:{comp:"el-input",formItemProps:{label:"调用结果"},compProps:{clearable:!0,type:"textarea",rows:25,readonly:!0,placeholder:"该区域显示查询结果，为只读状态，点击重置会清空该区域数据"}}}],openplatformDocApiAllDetail:null}),f=n({buttonText:"查询",loading:!1,permission:"admin:web:openplatformOpenapi:singleQuery"}),F=()=>{v(()=>{b(()=>{f.value.loading=!0,h().then(a=>{let o=a.data.data;try{let r=JSON.parse(o),t=JSON.stringify(r,null,"   ");e.resultForm.result=t}catch{e.resultForm.result=o}}).finally(()=>{f.value.loading=!1})})})},v=a=>{i.value&&i.value.formRef.validate((o,r)=>{o&&a()})},b=a=>{l.value&&l.value.formRef.validate((o,r)=>{o&&a()})},h=()=>{let a={bodyParam:null,queryStringParam:null};for(let r in e.form)a[r]=e.form[r];let o={};for(let r in e.dynamicform)o[r]=e.dynamicform[r];return y(o)||(a.bodyParam=o),A(a)},C=a=>{e.dynamicformCompsReady=!1,a&&(e.dynamicformLoading=!0,R({openplatformOpenapiId:a}).then(o=>{e.openplatformDocApiAllDetail=o.data,d()}).catch(o=>{e.openplatformDocApiAllDetail=null,d()}).finally(()=>{e.dynamicformLoading=!1,e.dynamicformCompsReady=!0}))},d=()=>{if(e.dynamicform={},e.dynamicformComps=[],!e.openplatformDocApiAllDetail)return;let a=e.openplatformDocApiAllDetail.data;if(y(a))return;let r=a.docApiDocParamFieldVOsMap.request_param;for(let t=0;t<r.length;t++){let m=r[t];m.parentId||e.dynamicformComps.push({field:{name:m.name},element:{comp:"el-input",formItemProps:{label:m.explanation,required:m.isRequired},compProps:{clearable:!0}}})}},D=()=>{l.value&&l.value.resetForm(),s.value&&s.value.resetForm()};return(a,o)=>{const r=S("PtForm"),t=q("loading");return p(),c(k,null,[u(r,{ref_key:"formRef",ref:i,form:e.form,labelWidth:"120",defaultButtonsShow:"",inline:"",layout:2,comps:e.formComps},null,8,["form","comps"]),B((p(),c("div",null,[e.dynamicformCompsReady?(p(),w(r,{key:0,ref_key:"dynamicFormRef",ref:l,form:e.dynamicform,labelWidth:"120",defaultButtonsShow:"",inline:"",comps:e.dynamicformComps},null,8,["form","comps"])):I("",!0)])),[[t,e.dynamicformLoading]]),u(r,{form:[],labelWidth:"120",method:F,defaultButtonsShow:"submit,reset",submitAttrs:f.value,inline:"",showButtonItem:!0,onResetForm:D,comps:[]},null,8,["submitAttrs"]),u(r,{ref_key:"resultFormRef",ref:s,form:e.resultForm,labelWidth:"80",defaultButtonsShow:"",layout:1,comps:e.resultFormComps},null,8,["form","comps"])],64)}}});export{Q as default};