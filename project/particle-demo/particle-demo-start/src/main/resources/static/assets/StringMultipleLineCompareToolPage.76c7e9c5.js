import{d as p,a as F,r as m,b,o as C,c as D,e as c,F as P,a5 as a}from"./index.bcd8c549.js";const v=p({name:"StringMultipleLineCompareToolPage"}),B=p({...v,setup(L){const t=F({form:{},resultForm:{leftText:"",rightText:""},formData:{}}),f=m([{field:{name:"leftText"},element:{comp:"el-input",formItemProps:{label:"\u5DE6\u4FA7\u591A\u884C\u6587\u672C",required:!0},compProps:{clearable:!0,rows:20,type:"textarea"}}},{field:{name:"rightText"},element:{comp:"el-input",formItemProps:{label:"\u53F3\u4FA7\u591A\u884C\u6587\u672C"},compProps:{clearable:!0,rows:20,type:"textarea"}}},{field:{name:"handleLogical",value:"removeIntersection"},element:{comp:"PtRadioGroup",formItemProps:{label:"\u5904\u7406\u903B\u8F91",required:!0},compProps:{options:[{id:"removeIntersection",name:"\u53BB\u9664\u4EA4\u96C6"},{id:"fetchIntersection",name:"\u83B7\u53D6\u4EA4\u96C6"},{id:"fetchUnion",name:"\u83B7\u53D6\u5E76\u96C6"},{id:"removeDuplicate",name:"\u6309\u884C\u53BB\u91CD"}]}}}]),x=m([{field:{name:"leftText"},element:{comp:"el-input",formItemProps:{label:"\u5DE6\u4FA7\u5904\u7406\u7ED3\u679C"},compProps:{clearable:!0,type:"textarea",rows:20}}},{field:{name:"rightText"},element:{comp:"el-input",formItemProps:{label:"\u53F3\u4FA7\u5904\u7406\u7ED3\u679C"},compProps:{clearable:!0,type:"textarea",rows:20}}}]),h=m({buttonText:"\u786E\u8BA4\u5904\u7406"}),T={removeIntersection:e=>{let n=e.leftText||"",r=e.rightText||"",l=n.split(`
`),o=r.split(`
`),u="",s="";l.forEach(i=>{o.indexOf(i)>=0||(u+=i+`
`)}),o.forEach(i=>{l.indexOf(i)>=0||(s+=i+`
`)}),t.resultForm.leftText=u,t.resultForm.rightText=s},fetchIntersection:e=>{let n=e.leftText||"",r=e.rightText||"",l=n.split(`
`),o=r.split(`
`),u=l.filter(s=>o.indexOf(s)>=0);t.resultForm.leftText=u,t.resultForm.rightText=u},fetchUnion:e=>{let n=e.leftText||"",r=e.rightText||"",l=n.split(`
`),o=r.split(`
`),u=l.concat(o),s=o.concat(l);t.resultForm.leftText=a(u).join(`
`),t.resultForm.rightText=a(s).join(`
`)},removeDuplicate:e=>{let n=e.leftText||"",r=e.rightText||"",l=n.split(`
`),o=r.split(`
`),u=a(l).join(`
`),s=a(o).join(`
`);t.resultForm.leftText=u,t.resultForm.rightText=s}},d=e=>{T[e.handleLogical](e)},g=()=>"\u5904\u7406\u6210\u529F";return(e,n)=>{const r=b("PtForm");return C(),D(P,null,[c(r,{form:t.form,formData:t.formData,labelWidth:"80",method:d,methodSuccess:g,defaultButtonsShow:"submit,reset",submitAttrs:h.value,layout:2,comps:f.value},null,8,["form","formData","submitAttrs","comps"]),c(r,{form:t.resultForm,labelWidth:"80",defaultButtonsShow:"",layout:2,comps:x.value},null,8,["form","comps"])],64)}}});export{B as default};
