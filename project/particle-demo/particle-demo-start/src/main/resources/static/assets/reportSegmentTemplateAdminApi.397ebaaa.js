import{g as t}from"./index.30ac3b34.js";let r="/admin/web/report_segment_template";const s=e=>t.post(r+"/create",e),p=e=>t.delete(r+"/delete",{data:e}),o=e=>t.put(r+"/update",e),n=e=>t.get(r+"/detail-for-update",{params:e}),c=e=>t.get(r+"/list",{params:e}),u=e=>t.get(r+"/page",{params:e}),m=e=>t.post(r+"/copy",e);export{m as a,s as c,n as d,c as l,u as p,p as r,o as u};