package com.particle.particledemo.test.dataquery


import java.util.concurrent.Executors

def data = [pageNo:1,pageSize:10,"name": "北京市"]
def dataQueryDataApiExecutor = Executors.newCachedThreadPool()
def datasourceApi = new TestForDatasourceApiInvoker();

def invoke = datasourceApi.invoke("area_page", data, null)

invoke;

