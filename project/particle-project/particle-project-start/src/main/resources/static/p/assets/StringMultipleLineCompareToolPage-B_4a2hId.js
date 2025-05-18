import{d as g,a as b,r as u,b as F,o as P,c as v,e as c,F as L,P as m}from"./index-COCB7OXO.js";const D=g({__name:"StringMultipleLineCompareToolPage",setup(I){const t=b({form:{},resultForm:{leftText:"",rightText:""},formData:{}}),p=u([{field:{name:"leftText"},element:{comp:"el-input",formItemProps:{label:"左侧多行文本",required:!0},compProps:{clearable:!0,rows:20,type:"textarea"}}},{field:{name:"rightText"},element:{comp:"el-input",formItemProps:{label:"右侧多行文本"},compProps:{clearable:!0,rows:20,type:"textarea"}}},{field:{name:"handleLogical",value:"removeIntersection"},element:{comp:"PtRadioGroup",formItemProps:{label:"处理逻辑",required:!0},compProps:{options:[{id:"removeIntersection",name:"去除交集"},{id:"fetchIntersection",name:"获取交集"},{id:"fetchUnion",name:"获取并集"},{id:"removeDuplicate",name:"按行去重"}]}}}]),f=u([{field:{name:"leftText"},element:{comp:"el-input",formItemProps:{label:"左侧处理结果"},compProps:{clearable:!0,type:"textarea",rows:20}}},{field:{name:"rightText"},element:{comp:"el-input",formItemProps:{label:"右侧处理结果"},compProps:{clearable:!0,type:"textarea",rows:20}}}]),x=u({buttonText:"确认处理"}),h={removeIntersection:e=>{let s=e.leftText||"",r=e.rightText||"",l=s.split(`
`),o=r.split(`
`),n="",i="";l.forEach(a=>{o.indexOf(a)>=0||(n+=a+`
`)}),o.forEach(a=>{l.indexOf(a)>=0||(i+=a+`
`)}),t.resultForm.leftText=n,t.resultForm.rightText=i},fetchIntersection:e=>{let s=e.leftText||"",r=e.rightText||"",l=s.split(`
`),o=r.split(`
`),n=l.filter(i=>o.indexOf(i)>=0);t.resultForm.leftText=n,t.resultForm.rightText=n},fetchUnion:e=>{let s=e.leftText||"",r=e.rightText||"",l=s.split(`
`),o=r.split(`
`),n=l.concat(o),i=o.concat(l);t.resultForm.leftText=m(n).join(`
`),t.resultForm.rightText=m(i).join(`
`)},removeDuplicate:e=>{let s=e.leftText||"",r=e.rightText||"",l=s.split(`
`),o=r.split(`
`),n=m(l).join(`
`),i=m(o).join(`
`);t.resultForm.leftText=n,t.resultForm.rightText=i}},T=e=>{h[e.handleLogical](e)},d=()=>"处理成功";return(e,s)=>{const r=F("PtForm");return P(),v(L,null,[c(r,{form:t.form,formData:t.formData,labelWidth:"80",method:T,methodSuccess:d,defaultButtonsShow:"submit,reset",submitAttrs:x.value,layout:2,comps:p.value},null,8,["form","formData","submitAttrs","comps"]),c(r,{form:t.resultForm,labelWidth:"80",defaultButtonsShow:"",layout:2,comps:f.value},null,8,["form","comps"])],64)}}});export{D as default};
