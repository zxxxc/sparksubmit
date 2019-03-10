package com.zxc.sparksubmit.mapper;

import com.zxc.sparksubmit.model.AppInfo;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AppInfoMapper {
    @Select("SELECT * FROM appinfo")
    @Results({
            @Result(property = "mainClass",  column = "mainclass"),
            @Result(property = "jarPath", column = "jarpath"),
            @Result(property = "note", column = "note")
    })
    List<AppInfo> getAllAppInfo();
}
