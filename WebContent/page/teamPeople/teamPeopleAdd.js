layui.use(['form','layer'],function(){
    var form = layui.form
        layer =layui.layer ,
        $ = layui.jquery;


    var orderIdList = [];
    $.ajax({
        url:getRootPath()+'/order/findAll.action',
        dataType:'json',
        type:'post',
        success:function(data){
            console.log(data.data);
            if(data.data){
                for(var i=0; i<data.data.length;i++){
                    orderIdList.push(data.data[i].orderId);
                }
                console.log(orderIdList);
                /* layer.msg('该编号已经添加，请不要重复添加', {icon: 1,ime:1000,});*/
            }
        }
    })

    form.verify({
        orderName: function(value, item){ //value：表单的值、item：表单的DOM对象
            if(value == ""){
                return '班组名称不能为空！';
            }
            if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
                return '班组名称不能有特殊字符';
            }
            if(/(^\_)|(\__)|(\_+$)/.test(value)){
                return '班组名称首尾不能出现下划线\'_\'';
            }
            if(/^\+?[1-9][0-9]*$/.test(value)){
                return '班组名称不能全为数字';
            }
            if(!new RegExp("^[\u4e00-\u9fffa-zA-Z0-9]{1,12}$").test(value)){
                return '长度不能超过12个字符';
            }
        },
        orderId:function(value,item){
            if(value == ""){
                return '编号不能为空！';
            }
            if(!new RegExp("^([0-9]([0-9]+)?|8)$").test(value)){
                return '请填纯数字，且长度不超过8！';
            }
            if(isInArray(orderIdList,value)){
                return '该编号已经添加，请不要重复添加！';
            }
        }

       /* //我们既支持上述函数式的方式，也支持下述数组的形式
        //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
        ,pass: [
            /^[\S]{6,12}$/
            ,'密码必须6到12位，且不能出现空格'
        ]*/
    });

    form.on("submit(addUser)",function(data){
        //弹出loading


            console.log("$$$$$$$$$$"+$(".userGrade").attr("select","selected").val());
            var index = layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
            // 实际使用时的提交信息
            $.post(getRootPath()+"/order/add.action",{
                orderId : $(".orderId").val(),  //班组编号
                orderName : $(".orderName").val()  //班组名称
            },function(res){
                //
            })
            setTimeout(function(){
                layer.close(index);
                layer.msg("用户添加成功！");
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



    function isInArray(arr,value){
        for(var i = 0; i < arr.length; i++){
            if(value == arr[i]){
                return true;
            }
        }
        return false;
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
