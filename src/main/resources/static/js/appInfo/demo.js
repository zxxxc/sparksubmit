$(function () {

function f(json) {


    var person = [
        {
            mainClass: '江哲',
            src: 'css/img/1.png',
            note: '须交有道之人，莫结无义之友。饮清静之茶，莫贪花色之酒。开方便之门，闲是非之口。',
            catagory: 'boys'
        },
        {
            mainClass: '欧阳锋',
            src: 'css/img/4.png',
            note: '“我欲”是贫穷的标志。事能常足，心常惬，人到无求品自高。',
            catagory: 'boys'
        },
        {
            mainClass: '江小白',
            src: 'css/img/3.png',
            note: '势不可使尽，福不可享尽，便宜不可占尽，聪明不可用尽。',
            catagory: 'boys'
        },
        {
            mainClass: '程浩然',
            src: 'css/img/2.png',
            note: '做事不必与俗同，亦不宜与俗异。做事不必令人喜，亦不可令人憎。',
            catagory: 'boys'
        },
        {
            mainClass: '李志成',
            src: 'css/img/5.png',
            note: '人之心胸，多欲则窄，寡欲则宽。',
            catagory: 'boys'
        },
        {
            mainClass: '王嫣然',
            src: 'css/img/6.png',
            note: '人的一生也可以是那一杯香醇的酒，慢慢地享受，细细地品味，自然也可以韵出生命的味道。',
            catagory: 'girls'
        },
        {
            mainClass: '程美',
            src: 'css/img/7.png',
            note: '大千世界，人生百态。如何面对人生，是快乐，是悲伤？不能让你的人生去决定，也不是任由命运摆布着你，应该自己把握！',
            catagory: 'girls'
        },
        {
            mainClass: '李梅芳',
            src: 'css/img/8.png',
            note: '人的一生是由色彩交织而成的，有善良的白，沉静的蓝，热情的红，希望的绿和温柔的紫。它们散发出的光彩，则是我们的生命。！',
            catagory: 'girls'
        },
        {
            mainClass: '南宫小婉',
            src: 'css/img/9.png',
            note: '生活就是一块调色板，你选择了你喜欢的色彩，那么其色就更加美丽，人生就似一碗汤，你选择了你喜欢的味道，你才感觉有滋有味……',
            catagory: 'girls'
        },
        {
            mainClass: '江苏颖',
            src: 'css/img/10.png',
            note: '势不可使尽，福不可享尽，便宜不可占尽，聪明不可用尽。',
            catagory: 'girls'
        }
    ]


    var inp = document.getElementsByClassName('inp')[0],
        btnList = document.getElementsByClassName('btn-list')[0],
        prompt = document.getElementsByClassName('prompt')[0],
        user = document.getElementsByClassName('user')[0];


    function createList(list) {
        var str = '';
        list.forEach(function (ele, index) {
            str += '<li>\
                <div class="head-img">\
                <img src='+ ele.src + ' alt="">\
                </div>\
                <div class="tit">\
                <h1 class="mainClass">'+ ele.mainClass + '</h1>\
                <p class="jarPath">'+ ele.note + '</p>\
                </div>\
                </li>'
        })

        user.innerHTML = str
    }

    createList(json);
    // createList(person);

    var state = createStore({ text: '', catagory: 'all' })

    state.addFn(function () {
        createList(perArr());
        anti(show());
    })

    inp.oninput = anti(getInput, 1000)

    function getInput() {
        state.change({ type: 'text', value: this.value });
    }

    function anti(hander, delay) {
        var timer = null;
        return function () {
             var  _self = this,
                args = arguments;
            clearTimeout(timer)
            timer = setTimeout(function () {
                hander.apply(_self, args)
            }, delay)
        }
    }

    function show() {
        if (user.children.length == 0) {
            prompt.style.display = 'block'
        } else {
            prompt.style.display = 'none'
        }
    }

    function addFn(obj, arr) {
        return function () {
            var lastArr = arr;
            for (prop in obj) {
                lastArr = obj[prop](state.getState()[prop], lastArr)
            }
            return lastArr
        }
    }

    var perArr = addFn({ text: filterText, catagory: filterSex }, json)
    // var perArr = addFn({ text: filterText, catagory: filterSex }, person)

    btnList.onclick = function (e) {
        var tar = e.target;
        if (tar.tagName == 'LI') {
            document.getElementsByClassName('active')[0].className = '';
            tar.className = 'active';
            state.change({ type: 'catagory', value: tar.getAttribute('catagory') })
        }
    }

    function filterText(text, arr) {
        return arr.filter(function (ele, index) {
            if (ele.mainClass.indexOf(text) != -1 || ele.note.indexOf(text) != -1) {
                return true
            }
        })
    }

    function filterSex(sex, arr) {
        if (sex == 'all') {
            return arr
        } else {
            return arr.filter(function (ele, index) {
                if (ele.catagory == sex) {
                    return true
                }
            })
        }
    }
}


    $.ajax({
        url:host+"/getAllAppInfo",
        // data:JSON.stringify(d),
        type:"POST",　　//数据传输方式
        contentType: "application/json;charset=utf-8",　　//数据返回的类型
        success: function(data)
        {
            var appInfo = JSON.parse(data);

            //按模板要求组装结果json
            for (var i=0;i<appInfo.length;i++){
                appInfo[i].src="css/img/appInfo/"+(i+1)+".png";
                appInfo[i].catagory="boys";
            }

            //调用模板方法
            f(appInfo);

            //给每一条记录绑定点击事件，使能够带参数跳转到App提交页面
            $(".user").children().each(function(){
                $(this).click(function(){
                    var num = $(this).index();
                    window.location.href=host+'/submitApp?mainClass='+appInfo[num].mainClass+'&jarPath='+appInfo[num].jarPath
                })
            });
        }
    });
})