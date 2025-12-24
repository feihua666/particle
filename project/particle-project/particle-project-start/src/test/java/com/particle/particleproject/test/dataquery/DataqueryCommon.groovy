package com.particle.particleproject.test.dataquery


import java.util.concurrent.Executors

def data = [:]
def dataQueryDataApiExecutor = Executors.newCachedThreadPool()
def datasourceApi = new TestForDatasourceApiInvoker();

