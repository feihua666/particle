import{d as F,r as n,a as h,af as g,b as A,o as x,c as _,e as N,f,u as q,F as D,_ as y}from"./index.3ad8a272.js";import{u as B,H as S,i as w,a as Y,b as O,c as $,d as j,e as k}from"./index.esm.min.9aac8d11.js";import{l as I}from"./ssqCodeOpenedAdminApi.465ccd69.js";const T=F({name:"SsqCodeOpenedSeqNoTrendPage.vue"}),W=F({...T,setup(L){B([w,Y,O,$,j,k]);const l=n({}),o=h({form:{},formComps:[{field:{name:"openedPhaseYearStart",value:new Date().getFullYear()+""},element:{comp:"PtDatePicker",formItemProps:{label:"\u5F00\u5956\u671F\u53F7\u5E74\u4EFD\u5F00\u59CB",tips:"\u5305\u62EC\u6570\u503C\u672C\u8EAB"},compProps:{type:"year",valueFormat:"YYYY"}}},{field:{name:"openedPhaseYearEnd"},element:{comp:"PtDatePicker",formItemProps:{label:"\u5F00\u5956\u671F\u53F7\u5E74\u4EFD\u7ED3\u675F",tips:"\u4E0D\u5305\u62EC\u6570\u503C\u672C\u8EAB"},compProps:{type:"year",valueFormat:"YYYY"}}},{field:{name:"slidingWindowSize",value:4},element:{comp:"el-input-number",formItemProps:{label:"\u6ED1\u52A8\u7A97\u53E3\u5927\u5C0F",tips:"\u53EF\u8C03\u7528\u6ED1\u52A8\u7A97\u53E3\u5927\u5C0F\u4EE5\u5BFB\u627E\u6700\u4F73\u6ED1\u52A8\u7A97\u53E3\u5927\u5C0F"},compProps:{}}},{field:{name:"computeObject",value:"seqNo"},element:{comp:"PtSelect",formItemProps:{label:"\u8BA1\u7B97\u5BF9\u8C61",required:!0},compProps:{dataMethod:()=>({data:[{id:"seqNo",name:"\u5E8F\u53F7"},{id:"redSeqNo",name:"\u7EA2\u5E8F\u53F7"},{id:"blue",name:"\u84DD\u7403"}]})}}}]}),b=n({buttonText:"\u8BA1\u7B97",loading:!1,permission:"admin:web:ssqCodeOpened:queryList"}),m=n({}),C=()=>I(o.form).then(e=>{let t=e.data.data;m.value=E[o.form.computeObject];let u=v(t,o.form.slidingWindowSize,m.value);return l.value=u,Promise.resolve(e)});g(()=>{});const E={seqNo:{max:17721008,fieldName:"seqNo",valueFormat:e=>e.toFixed(5)},redSeqNo:{max:1107568,fieldName:"redSeqNo",valueFormat:e=>e.toFixed(5)},blue:{max:16,fieldName:"blue",valueFormat:e=>e.toFixed(5)}},v=(e,t,u)=>{let i=[],p=[];for(let r=t;r<e.length;r++){let d=0,c=[];for(let a=0;a<t;a++){let s=e[r-(t-a-1)][u.fieldName];c.push(s)}d=c.reduce((a,s)=>a+s,0),i.push(e[r].openedPhase),p.push(Number(u.valueFormat(d)))}return P(i,p,"")},P=(e,t,u)=>({myCustom:{},title:{text:u},grid:{left:"0",right:"0",bottom:"0",containLabel:!0},tooltip:{trigger:"axis",show:!0,formatter:"\u548C\u6570\uFF1A{c}<br/>\u671F\u53F7\uFF1A{b}"},xAxis:{type:"category",data:e,axisLabel:{rotate:30}},yAxis:{type:"value"},series:[{data:t,type:"line",label:{show:!0,position:"top",formatter:"{c}"}}]});return(e,t)=>{const u=A("PtForm");return x(),_(D,null,[N(" \u67E5\u8BE2\u8868\u5355 "),f(u,{form:o.form,method:C,defaultButtonsShow:"submit,reset",submitAttrs:b.value,inline:"",comps:o.formComps},null,8,["form","submitAttrs","comps"]),f(q(S),{style:{width:"100%",height:"300px"},option:l.value,autoresize:""},null,8,["option"])],64)}}}),H=y(W,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/dream/views/ssq/admin/SsqCodeOpenedSlidingWindowTrendPage.vue"]]);export{H as default};