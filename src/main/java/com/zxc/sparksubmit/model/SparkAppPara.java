package com.zxc.sparksubmit.model;

public class SparkAppPara {

    String mainClass;//应用程序的mainClass
    String jarPath;//应用程序jar包的存放位置，可以是本地或HDFS
    String master;//可以是Yarn或StandAlone
    String deployMode;//可以是Cluster或Client
    String driverMemory ;//driver内存
    String executorMemory;//executor内存
    String executorInstances;//executor个数
    String executorCores;//executor核数
    String defaultParallelism;//参数spark.default.parallelism的值
    String driverExtraJavaOptions;//driver额外的JVM选项。如：GC设置或其他日志参数
    String otherConf;//其它配置，可以有多个，每个以分号隔开


    public String getOtherConf() {
        return otherConf;
    }

    public void setOtherConf(String otherConf) {
        this.otherConf = otherConf;
    }

    public String getDriverExtraJavaOptions() {
        return driverExtraJavaOptions;
    }

    public void setDriverExtraJavaOptions(String driverExtraJavaOptions) {
        this.driverExtraJavaOptions = driverExtraJavaOptions;
    }

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    public String getJarPath() {
        return jarPath;
    }

    public void setJarPath(String jarPath) {
        this.jarPath = jarPath;
    }

    public String getDefaultParallelism() {
        return defaultParallelism;
    }

    public void setDefaultParallelism(String defaultParallelism) {
        this.defaultParallelism = defaultParallelism;
    }

    public String getExecutorMemory() {
        return executorMemory;
    }

    public void setExecutorMemory(String executorMemory) {
        this.executorMemory = executorMemory;
    }

    public String getExecutorInstances() {
        return executorInstances;
    }

    public void setExecutorInstances(String executorInstances) {
        this.executorInstances = executorInstances;
    }

    public String getExecutorCores() {
        return executorCores;
    }

    public void setExecutorCores(String executorCores) {
        this.executorCores = executorCores;
    }

    public String getDriverMemory() {
        return driverMemory;
    }

    public void setDriverMemory(String driverMemory) {
        this.driverMemory = driverMemory;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getDeployMode() {
        return deployMode;
    }

    public void setDeployMode(String deployMode) {
        this.deployMode = deployMode;
    }
}
