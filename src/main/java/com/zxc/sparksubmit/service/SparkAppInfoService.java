package com.zxc.sparksubmit.service;

import com.alibaba.fastjson.JSONObject;
import com.zxc.sparksubmit.mapper.AppInfoMapper;
import com.zxc.sparksubmit.model.AppInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SparkAppInfoService {

    @Autowired
    private AppInfoMapper appInfo;

    public String getAllAppInfo(){
        List<AppInfo> list = appInfo.getAllAppInfo();
        return JSONObject.toJSONString(list);
    }

}
