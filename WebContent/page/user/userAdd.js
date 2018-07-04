layui.use(['form','layer'],function(){
    var form = layui.form
        layer =layui.layer ,
        $ = layui.jquery;



    var orderNames = [];
    $.ajax({
        url:getRootPath()+'/order/findAll.action',
        dataType:'json',
        type:'post',
        success:function(data){
            console.log(data);
            //$('.userGrade').append("<option value="+">"+"请选择"+"</option>");
            $.each(data.data,function(index,item){
                orderNames.push(item.orderName);
                console.log(index+"----"+item+"-----"+orderNames.length);
                $('.userGrade').append("<option value="+item.orderId+">"+item.orderId+"</option>");// 下拉菜单里添加元素
            });
            form.render();//下拉菜单渲染 把内容加载进去
        }
    });


    form.verify({
        userName: function(value, item){ //value：表单的值、item：表单的DOM对象
            if(value == ""){
                return '用户名不能为空!'
            }
            if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
                return '用户名不能有特殊字符';
            }
            if(/(^\_)|(\__)|(\_+$)/.test(value)){
                return '用户名首尾不能出现下划线\'_\'';
            }
            if(/^\+?[1-9][0-9]*$/.test(value)){
                return '用户名不能全为数字';
            }
            if(!new RegExp("^[\u4e00-\u9fffa-zA-Z0-9]{1,12}$").test(value)){
                return '长度不能超过12个字符';
            }
        },
        orderId:function (value,item) {
            if(orderNames.length == 0){
                return '请选择所属班组';
            }
        }
    });


    form.on("submit(addUser)",function(data){
        //弹出loading
        console.log("$$$$$$$$$$"+$(".userGrade").attr("select","selected").val());
        var index = layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
         $.post(getRootPath()+"/user/add.action",{
               userName : $(".userName").val(),  //用户名
               orderId :$(".userGrade").attr("select","selected").val()
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