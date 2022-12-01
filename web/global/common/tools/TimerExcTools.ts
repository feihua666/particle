//定时器
//fn执行函数，time时间，多长时间执行一次,单位毫秒，times执行次数，0为无限次
export function timer (fn:()=>any,time: number,times: number) {
    let _times = 0;
    //默认次数为无限次
    if(!times){
        times = 0;
    }
    //默认间隔时间为一秒钟
    if(!time){
        time = 1000;
    }
    let t= setInterval(function () {
        if (fn && typeof fn == "function") {
            fn.call(null,_times);
            _times ++;
            //如果到了执行次数
            if (_times==times) {
                clearInterval(t);
            }
        }else{
            clearInterval(t);
        }
    },time);
    return t;
}