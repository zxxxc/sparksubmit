$(function () {
    $("#driverMemory").val("1");
    $("#executorMemory").val("1");
    $("#executorInstances").val("1");
    $("#executorCores").val("1");
    $("#defaultParallelism").val("1");

    $("#submitBtn").click(function(){
        var param = {};
        param.mainClass = $("#mainClass").val();
        param.jarPath = $("#jarPath").val();
        param.master = $("input[name='radio-button1']:checked").val();
        param.deployMode = $("input[name='radio-button2']:checked").val();
        param.driverMemory = $("#driverMemory").val();
        param.executorMemory = $("#executorMemory").val();
        param.executorInstances = $("#executorInstances").val();
        param.executorCores = $("#executorCores").val();
        param.defaultParallelism = $("#defaultParallelism").val();
        param.driverExtraJavaOptions = $("#driverExtraJavaOptions").val();
        param.otherConf = $("#otherConf").val();

        console.info(param);
        //打开遮罩层
        $.mask_fullscreen();

        $.ajax({
            url:host+"/submit",
            data:JSON.stringify(param),
            type:"POST",　　//数据传输方式
            contentType: "application/json;charset=utf-8",　　//数据返回的类型
            success: function(data)
            {
                window.location.href=host+'/result?resultJson='+ encodeURIComponent(data);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                //关闭遮罩层
                $.mask_close_all();

                /*alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
                alert(errorThrown);*/

                alert("错误码："+XMLHttpRequest.status+"，任务提交失败！");
            }
        });
    });
})