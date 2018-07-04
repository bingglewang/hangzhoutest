layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;


    var orderIds = [];
    $.ajax({
        url:getRootPath()+'/user/getUserOrderIds.action',
        dataType:'json',
        type:'post',
        success:function(data){
            console.log(data.data);
            if(data.data){
                for(var i=0; i<data.data.length;i++){
                    orderIds.push(data.data[i].orderId);
                }
                console.log(orderIds);
                /* layer.msg('该编号已经添加，请不要重复添加', {icon: 1,ime:1000,});*/
            }
        }
    })


    //班组列表
    var tableIns = table.render({
        elem: '#userList',
        url : getRootPath()+'/order/findAllList.action',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [5,10,15,20],
        limit : 5,
        id : "userListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'orderId', title: '班组编号', minWidth:100, align:"center"},
            {field: 'orderName', title: '班组名称', align:'center'},
            {title: '操作', minWidth:175, templet:'#userListBar',fixed:"right",align:"center"}
        ]]
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
        console.log("++++++++++"+getRootPath());
        //if($(".searchVal").val() != ''){
            //console.log("**"+$(".searchVal").val());
            table.reload("userListTable",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: 'orderName',
                    value:encodeURI($(".searchVal").val())//搜索的关键字
                }
            })
    });


    $('.searchVal').bind('keydown', function (e) {
        if (e.keyCode == 13) {
           // alert($(".searchVal").val());
            // 取输入框数据 和123456 比较
            table.reload("userListTable",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: 'orderName',
                    value:encodeURI($(".searchVal").val())//搜索的关键字
                }
            })
            return false;
        }
    });


    //添加用户
    function addUser(edit){
        var index = layui.layer.open({
            title : "添加班组",
            type : 2,
            area: ['700px', '450px'],
            fixed: false, //不固定
            maxmin: false,
            content : "teamPeopleAdd.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".userName").val(edit.userName);  //登录名
                    body.find(".userEmail").val(edit.userEmail);  //邮箱
                    body.find(".userSex input[value="+edit.userSex+"]").prop("checked","checked");  //性别
                    body.find(".userGrade").val(edit.userGrade);  //会员等级
                    body.find(".userStatus").val(edit.userStatus);    //用户状态
                    body.find(".userDesc").text(edit.userDesc);    //用户简介
                    form.render();
                }
               /* setTimeout(function(){
                    layui.layer.tips('点击此处返回班组列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)*/
            }
        })
        //layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }
    $(".addNews_btn").click(function(){
        addUser();
    })

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('userListTable'),
            data = checkStatus.data,
            orderId = [];
        if(data.length > 0) {
            for (var i in data) {
                orderId.push(data[i].orderId);
            }
            layer.confirm('确定删除选中的班组？', {icon: 3, title: '提示信息'}, function (index) {
                 var count = 0;
                 for(var i = 0;i<orderId.length;i++){
                    if(isInArray(orderIds,orderId[i])){
                        count++;
                    }
                 }
                 if(count > 0 ){
                     layer.msg("有班组正在被使用，请重新选择",{icon:2});
                 }else{
                     $.post(getRootPath()+"/order/delAll.action",{
                         orderIds : JSON.stringify(orderId)  //将需要删除的userId作为参数传入
                     },function(data){
                         tableIns.reload();
                         layer.close(index);
                     })
                 }
            })
        }else{
            layer.msg("请选择需要删除的班组");
        }
    })

    function editUser(data){
        /*console.log("#####"+data.userName);
        console.log("#####"+data.orderId);*/
        var index = layui.layer.open({
            title : "编辑班组",
            type : 2,
            area: ['700px', '450px'],
            fixed: false, //不固定
            maxmin: false,
            content : "teamPeopleEdit.html?",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(data){
                    body.find(".orderName").val(data.orderName);  //用户名
                    body.find(".orderId").val(data.orderId);
                    form.render();
                }
               /* setTimeout(function(){
                    layui.layer.tips('点击此处返回班组列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)*/
            }
        })
        //layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }

    //列表操作
    table.on('tool(userList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            editUser(data);
        }else if(layEvent === 'usable'){ //启用禁用
            var _this = $(this),
                usableText = "是否确定禁用此用户？",
                btnText = "已禁用";
            if(_this.text()=="已禁用"){
                usableText = "是否确定启用此用户？",
                btnText = "已启用";
            }
            layer.confirm(usableText,{
                icon: 3,
                title:'系统提示',
                cancel : function(index){
                    layer.close(index);
                }
            },function(index){
                _this.text(btnText);
                layer.close(index);
            },function(index){
                layer.close(index);
            });
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此班组？',{icon:3, title:'提示信息'},function(index){
                 if(isInArray(orderIds,data.orderId)){
                     layer.msg("该班组正在被使用，不能删除",{icon:2});
                 }else{
                     $.get(getRootPath()+"/order/del.action",{
                         orderId : data.orderId  //将需要删除的newsId作为参数传入
                     },function(data){
                         tableIns.reload();
                         layer.close(index);
                     })
                 }

            });
        }
    });

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

function isInArray(arr,value){
    for(var i = 0; i < arr.length; i++){
        if(value === arr[i]){
            return true;
        }
    }
    return false;
}