import{u as g,H as F,i as N,a as y,b as q,c as C,d as Y,e as S}from"./index.esm.min-DmOlxDyc.js";import{l as _}from"./ssqCodeOpenedAdminApi-DBBTRNFx.js";import{d as w,r as m,a as A,X as O,b as D,o as k,c as $,e as f,k as j,F as I}from"./index-BGwvwH0Y.js";const E=w({__name:"SsqCodeOpenedSlidingWindowTrendPage",setup(W){g([N,y,q,C,Y,S]);const n=m({}),a=A({form:{},formComps:[{field:{name:"openedPhaseYearStart",value:new Date().getFullYear()+""},element:{comp:"PtDatePicker",formItemProps:{label:"开奖期号年份开始",tips:"包括数值本身"},compProps:{type:"year",valueFormat:"YYYY"}}},{field:{name:"openedPhaseYearEnd"},element:{comp:"PtDatePicker",formItemProps:{label:"开奖期号年份结束",tips:"不包括数值本身"},compProps:{type:"year",valueFormat:"YYYY"}}},{field:{name:"slidingWindowSize",value:4},element:{comp:"el-input-number",formItemProps:{label:"滑动窗口大小",tips:"可调用滑动窗口大小以寻找最佳滑动窗口大小"},compProps:{}}},{field:{name:"computeObject",value:"seqNo"},element:{comp:"PtSelect",formItemProps:{label:"计算对象",required:!0},compProps:{dataMethod:()=>({data:[{id:"seqNo",name:"序号"},{id:"redSeqNo",name:"红序号"},{id:"blue",name:"蓝球"}]})}}}]}),b=m({buttonText:"计算",loading:!1,permission:"admin:web:ssqCodeOpened:queryList"}),i=m({}),h=()=>_(a.form).then(e=>{let t=e.data.data;i.value=P[a.form.computeObject];let o=v(t,a.form.slidingWindowSize,i.value);return n.value=o,Promise.resolve(e)});O(()=>{});const P={seqNo:{max:17721008,fieldName:"seqNo",valueFormat:e=>e.toFixed(5)},redSeqNo:{max:1107568,fieldName:"redSeqNo",valueFormat:e=>e.toFixed(5)},blue:{max:16,fieldName:"blue",valueFormat:e=>e.toFixed(5)}},v=(e,t,o)=>{let u=[],p=[];for(let s=t;s<e.length;s++){let d=0,c=[];for(let r=0;r<t;r++){let l=e[s-(t-r-1)][o.fieldName];c.push(l)}d=c.reduce((r,l)=>r+l,0),u.push(e[s].openedPhase),p.push(Number(o.valueFormat(d)))}return x(u,p,"")},x=(e,t,o)=>({myCustom:{},title:{text:o},grid:{left:"0",right:"0",bottom:"0",containLabel:!0},tooltip:{trigger:"axis",show:!0,formatter:"和数：{c}<br/>期号：{b}"},xAxis:{type:"category",data:e,axisLabel:{rotate:30}},yAxis:{type:"value"},series:[{data:t,type:"line",label:{show:!0,position:"top",formatter:"{c}"}}]});return(e,t)=>{const o=D("PtForm");return k(),$(I,null,[f(o,{form:a.form,method:h,defaultButtonsShow:"submit,reset",submitAttrs:b.value,inline:"",labelWidth:"140",comps:a.formComps},null,8,["form","submitAttrs","comps"]),f(j(F),{style:{width:"100%",height:"300px"},option:n.value,autoresize:""},null,8,["option"])],64)}}});export{E as default};