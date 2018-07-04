/**
 * Created by Administrator on 2018/7/2 0002.
 */
layui.use(['form','layer'],function(){
    var form = layui.form
        layer =layui.layer ,
        $ = layui.jquery;

    $("#cancle_btn").click(function(){
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);//关闭当前页
       /* parent.location.reload();*/
    });

    form.verify({
        orderName: function(value, item){ //value：表单的值、item：表单的DOM对象
            if(value == ""){
                return '用户名不能为空！';
            }
            if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
                return '用户名不能有特殊字符';
            }
            if(/(^\_)|(\__)|(\_+$)/.test(value)){
                return '用户名首尾不能出现下划线\'_\'';
            }
            if(/^\d+\d+\d$/.test(value)){
                return '用户名不能全为数字';
            }
            if(!new RegExp("^[\u4e00-\u9fffa-zA-Z0-9]{1,12}$").test(value)){
                return '长度不能超过12个字符';
            }
        }

        //我们既支持上述函数式的方式，也支持下述数组的形式
        //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
        ,pass: [
            /^[\S]{6,12}$/
            ,'密码必须6到12位，且不能出现空格'
        ]
    });


    form.on("submit(addUser)",function(data){
        var index = layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
        $.post(getRootPath()+"/order/upd.action",{
            orderId : $(".orderId").val(),  //用户名
            orderName :$(".orderName").val()
        },function(res){
            //
        })
        setTimeout(function(){
            layer.close(index);
            layer.msg("用户编辑成功！");
            //layer.closeAll("iframe");
            //刷新父页面
            parent.location.reload();
        },1000);

        return false;
    })

    //格式化时间
    function filterTime(val){
        if(val < 10){
            return "0" + val;
        }else{
            return val;
        }
    }
    //定时发布
    var time = new Date();
    var submitTime = time.getFullYear()+'-'+filterTime(time.getMonth()+1)+'-'+filterTime(time.getDate())+' '+filterTime(time.getHours())+':'+filterTime(time.getMinutes())+':'+filterTime(time.getSeconds());

})


function getRootPath(){
    //获取当前网址，如： http://localhost:8088/test/test.jsp
    var curPath=window.document.location.href;
    //获取主机地址之后的目录，如： test/test.jsp
    var pathName=window.document.location.pathname;
    var pos=curPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8088
    var localhostPaht=curPath.substring(0,pos);

    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return(localhostPaht + projectName);
}

function getQueryVariable(variable)
{
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
    }
    return(false);
}