import{g as e}from"./index.6cdd7c8b.js";let a="/admin/web/openplatform_openapi_record_customer_month_bill";const r=t=>e.delete(a+"/delete",{data:t}),s=t=>e.put(a+"/update",t),n=t=>e.get(a+"/detail-for-update",{params:t}),i=t=>e.get(a+"/page",{params:t}),p=t=>e.post(a+"/lastMonthStatistic",t),l=t=>e.post(a+"/thisMonthStatistic",t);export{n as d,p as l,i as p,r,l as t,s as u};