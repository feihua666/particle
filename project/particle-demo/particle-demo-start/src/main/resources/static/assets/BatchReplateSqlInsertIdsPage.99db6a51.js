import{b as _}from"./toolsFrontApi.73f76031.js";import{d as F,a as x,r as m,b as q,o as w,c as y,e as i,F as M,a4 as R,P as V}from"./index.858f5c96.js";const L=F({name:"BatchReplateSqlInsertIdsPage"}),W=F({...L,setup(N){const{proxy:f}=V(),e=x({form:{},resultForm:{result:""},formData:{}}),d=m([{field:{name:"insertSql"},element:{comp:"el-input",formItemProps:{label:"sql\u63D2\u5165\u8BED\u53E5",required:!0},compProps:{clearable:!0,type:"textarea",rows:10}}},{field:{name:"idExtractPattern",value:"VALUES \\(([^,]+),"},element:{comp:"el-input",formItemProps:{label:"id\u63D0\u53D6\u6B63\u5219\u8868\u8FBE\u5F0F",required:!0,tips:"\u7ECF\u6D4B\u8BD5\uFF0C\u9ED8\u8BA4\u7684\u6B63\u5219\u8868\u8FBE\u5F0F\u652F\u6301datagrip\u548Cnavicat\u590D\u5236\u7684sql\u63D2\u5165\u8BED\u53E5"},compProps:{clearable:!0}}},{field:{name:"valueSeparator",value:"="},element:{comp:"el-input",formItemProps:{label:"\u503C\u5206\u9694\u7B26",tips:"\u5982\u679C\u88AB\u66FF\u6362\u7684\u6587\u672C\u5305\u62EC\u5206\u9694\u7B26\u65F6\uFF0C\u8BF7\u586B\u5199\u5176\u5B83\u5206\u9694\u7B26",required:!0},compProps:{clearable:!0}}},{field:{name:"groupSeparator",value:";"},element:{comp:"el-input",formItemProps:{label:"\u7EC4\u5206\u9694\u7B26",tips:"\u5982\u679C\u88AB\u66FF\u6362\u7684\u6587\u672C\u5305\u62EC\u5206\u9694\u7B26\u65F6\uFF0C\u8BF7\u586B\u5199\u5176\u5B83\u5206\u9694\u7B26",required:!0},compProps:{clearable:!0}}},{field:{name:"replaceText",value:"VALUES (=;,="},element:{comp:"el-input",formItemProps:{label:"\u63D0\u53D6\u5B8C\u6210\u540E\u66FF\u6362",tips:"\u652F\u6301\u591A\u7EC4\u66FF\u6362\u5982\uFF1Aa=b,c=d\u5C06\u4F1A\u628Aa\u66FF\u6362\u6210b\uFF0Cc\u66FF\u6362\u6210d"},compProps:{clearable:!0}}}]),B=m([{field:{name:"result"},element:{comp:"el-input",formItemProps:{label:"\u66FF\u6362\u7ED3\u679C"},compProps:{clearable:!0,type:"textarea",rows:25}}}]),b=m({buttonText:"\u786E\u8BA4\u751F\u6210"}),h=()=>{n.value=[],c.value=0,e.resultForm.result="",E()},C=(t,u)=>{_({num:t}).then(r=>{for(let l=0;l<r.data.data.length;l++)n.value.push(r.data.data[l]);u()})},E=()=>{let t=e.form.insertSql.split(`
`),u=100,r=Math.ceil(t.length/u)+1;const l=s=>{s.forEach(o=>{if(!o||!o.trim())return;let p=R(e.form.idExtractPattern,o);if(p.length===0){v("\u6CA1\u6709\u5339\u914D\u5230\u5185\u5BB9");return}let S=A(p[0]),g=I();D(S,g)})},a=(s,o)=>{C(u,()=>{s-=1,s>0?a(s,o):o()})};a(r,()=>{l(t)})},n=m([]),c=m(0),I=()=>n.value[c.value++],A=t=>{let u="",r=e.form.replaceText;return r&&r.split(e.form.groupSeparator).forEach(l=>{let a=l.split(e.form.valueSeparator);u?u=u.replaceAll(a[0],a[1]):u=t.replaceAll(a[0],a[1])}),u},D=(t,u)=>{e.resultForm.result?e.resultForm.result=e.resultForm.result.replaceAll(t,u):e.resultForm.result=e.form.insertSql.replaceAll(t,u)},v=t=>{f.$message({showClose:!0,message:t,type:"error",showIcon:!0,grouping:!0})},P=()=>"\u66FF\u6362\u6210\u529F";return(t,u)=>{const r=q("PtForm");return w(),y(M,null,[i(r,{form:e.form,formData:e.formData,labelWidth:"80",method:h,methodSuccess:P,defaultButtonsShow:"submit,reset",submitAttrs:b.value,layout:[1,3,1],comps:d.value},null,8,["form","formData","submitAttrs","comps"]),i(r,{form:e.resultForm,labelWidth:"80",defaultButtonsShow:"",layout:1,comps:B.value},null,8,["form","comps"])],64)}}});export{W as default};