package com.zxc.sparksubmit.service;


import com.zxc.sparksubmit.model.SparkAppPara;
import com.zxc.sparksubmit.util.RestUtil;
import org.springframework.stereotype.Service;

import org.apache.spark.launcher.SparkAppHandle;
import org.apache.spark.launcher.SparkLauncher;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

@Service
public class SparkSubmitService {

    public String submitApp(SparkAppPara sparkAppPara) throws IOException, InterruptedException {
        HashMap env = new HashMap();
        //这两个属性必须设置
        env.put("HADOOP_CONF_DIR", "/usr/local/hadoop/etc/hadoop");
        env.put("JAVA_HOME", "/usr/lib/jdk/jdk1.8.0_191/");
        //可以不设置
        //env.put("YARN_CONF_DIR","");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        //这里调用setJavaHome()方法后，JAVA_HOME is not set 错误依然存在
        SparkLauncher launcher = new SparkLauncher(env)
                .setSparkHome("/usr/local/spark/")
                .setAppResource(sparkAppPara.getJarPath())
                .setMainClass(sparkAppPara.getMainClass())
                .setMaster(sparkAppPara.getMaster())
                .setDeployMode(sparkAppPara.getDeployMode())
                .setConf("spark.driver.memory", sparkAppPara.getDriverMemory()+"g")
                .setConf("spark.executor.memory", sparkAppPara.getExecutorMemory()+"g")
                .setConf("spark.executor.instances", sparkAppPara.getExecutorInstances())
                .setConf("spark.executor.cores", sparkAppPara.getExecutorCores())
                .setConf("spark.default.parallelism", sparkAppPara.getDefaultParallelism())
                .setConf("spark.driver.extraJavaOptions", sparkAppPara.getDriverExtraJavaOptions());

        String otherConf = sparkAppPara.getOtherConf();
        if (!otherConf.isEmpty()){
            String[] confs = otherConf.split(";");
            if (confs.length>0){
                for (String conf:confs) {
                    launcher.setConf(conf.substring(0,conf.indexOf("=")).trim(),conf.substring(conf.indexOf("=")+1,conf.length()));
                }
            }
        }

        SparkAppHandle handle =launcher.setVerbose(true).startApplication(new SparkAppHandle.Listener() {
                //这里监听任务状态，当任务结束时（不管是什么原因结束）,isFinal（）方法会返回true,否则返回false
                @Override
                public void stateChanged(SparkAppHandle sparkAppHandle) {
                    if (sparkAppHandle.getState().isFinal()) {
                        countDownLatch.countDown();
                    }
                    System.out.println("state:" + sparkAppHandle.getState().toString());
                }


                @Override
                public void infoChanged(SparkAppHandle sparkAppHandle) {
                    System.out.println("Info:" + sparkAppHandle.getState().toString());
                }
        });
        System.out.println("The task is executing, please wait ....");
        //线程等待任务结束
        countDownLatch.await();
        System.out.println("The task is finished!");

        String restUrl = "http://master:18080/api/v1/applications/"+handle.getAppId();
        String resultJson = RestUtil.httpGet(restUrl,null);

        return resultJson;
    }
}
