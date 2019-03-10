package com.zxc.sparksubmit.controller;

import com.zxc.sparksubmit.model.SparkAppPara;
import com.zxc.sparksubmit.service.SparkAppInfoService;
import com.zxc.sparksubmit.service.SparkSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class SparkController {

    @Autowired
    SparkSubmitService submitService;

    @Autowired
    SparkAppInfoService sparkAppInfoService;

    @RequestMapping("/appInfo")
    public String appInfo(){
        return "appInfo";
    }

    @RequestMapping(value = "/submit")
    @ResponseBody
    public String Submit(@RequestBody SparkAppPara sparkAppPara) throws IOException, InterruptedException {
        String resultJson = submitService.submitApp(sparkAppPara);
        System.out.println("======resultJson======"+resultJson);

        return resultJson;
    }

    @RequestMapping("/result")
    public ModelAndView toResult(String resultJson){
        System.out.println("======toResult======"+resultJson);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("result");
        mav.addObject("resultJson",resultJson);

        return mav;
    }

    @RequestMapping("/submitApp")
    public ModelAndView submitApp(String mainClass,String jarPath){

        ModelAndView mav = new ModelAndView();
        mav.setViewName("submitApp");
        mav.addObject("mainClass",mainClass);
        mav.addObject("jarPath",jarPath);

        return mav;
    }

    @RequestMapping("/getAllAppInfo")
    @ResponseBody
    public String getAllAppInfo(){
        return sparkAppInfoService.getAllAppInfo();
    }

}
