function l(n,t){const e=document.createElement("a");e.href=n,e.style.display="none",e.setAttribute("target","_blank"),t&&e.setAttribute("download",t),document.body.appendChild(e),e.click(),document.body.removeChild(e)}function s(n,t,e){const o=window.URL.createObjectURL(new Blob([n]));l(o,e),window.URL.revokeObjectURL(o)}function r(n){const t=/filename\*=([^;]*)/i,e=n.match(t);if(e){const[,c]=e,[d,i]=c.split("''");return i}const o=/filename=\"([^\"]*)\"/i,a=n.match(o);return a?a[1]:null}function u(n){let t=null,e=n.headers;return e["content-disposition"]&&(t=r(e["content-disposition"])),t}function f(n){let t=null,e=n.headers;return e["content-type"]&&(t=e["content-type"]),t}export{u as a,l as b,s as d,f as e};