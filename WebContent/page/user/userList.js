layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;


    //用户列表
    var tableIns = table.render({
        elem: '#userList',
        url: getRootPath() + '/user/findAll.action',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [5,10,15,20],
        limit: 5,
        id: "userListTable",
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {field: 'userId', title: '用户编号', minWidth: 100, align: "center"},
            {field: 'userName', title: '用户姓名', align: 'center'},
            {field: 'orderId', title: '班组编号', align: 'center', minWidth: 150},
            {title: '操作', minWidth: 175, templet: '#userListBar', fixed: "right", align: "center"}
        ]],
        done: function (res, curr, count) {
            /*var $mylist = $("#userList").next('.layui-table-view').find('table.layui-table');
            $mylist.dblclick(function(event){
                alert($(event.target).closest("tr")[0].outerHTML);
            });*/

           /* $('#div').find('.layui-table-body').find("table" ).find("tbody").children("tr").on('dblclick',function() {
                var id = JSON.stringify($('#div').find('.layui-table-body').find("table").find("tbody").find(".layui-table-hover").data('index'));
                var obj = res.data[id];
                alert(obj);
            });*/
            $(document).on("dblclick",".layui-table-body table.layui-table tbody tr", function () {
                var index = $(this).attr('data-index');
                var obj = res.data[index];
                /*alert(obj.userId);*/
                userInfo(obj);
            });
        }
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
                    key: 'userName',
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
                    key: 'userName',
                    value:encodeURI($(".searchVal").val())//搜索的关键字
                }
            })
            return false;
        }
    });


    //查看用户信息
    function userInfo(data){
        var index = layui.layer.open({
            title : "用户详情",
            type : 2,
            area: ['700px', '450px'],
            fixed: false, //不固定
            maxmin: false,
            content : "userInfo.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(data){
                    $.ajax({
                        url:getRootPath()+'/user/getallInfo.action?userId='+data.userId,
                        dataType:'json',
                        type:'post',
                        success:function(data1){
                            $.each(data1.data,function(index,item) {
                                body.find("#userId").val(item.userId);  //用户id
                                body.find("#userName").val(item.userName);  //用户名
                                body.find("#orderId").val(item.orderId);  //班组id
                                body.find("#orderName").val(item.orderName); //班组名称
                                form.render();
                            })
                        }
                    });
                }
               /* setTimeout(function(){
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
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

    //添加用户
    function addUser(edit){
        var index = layui.layer.open({
            title : "添加用户",
            type : 2,
            area: ['700px', '450px'],
            fixed: false, //不固定
            maxmin: false,
            content : "userAdd.html",
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
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
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
            userId = [];
        if(data.length > 0) {
            for (var i in data) {
                userId.push(data[i].userId);
            }
            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
                 $.post(getRootPath()+"/user/delAll.action",{
                     userIds : JSON.stringify(userId)  //将需要删除的userId作为参数传入
                 },function(data){
                tableIns.reload();
                layer.close(index);
                 })
            })
        }else{
            layer.msg("请选择需要删除的用户");
        }
    })

    function editUser(data){
        /*console.log("#####"+data.userName);
        console.log("#####"+data.orderId);*/
        var index = layui.layer.open({
            title : "编辑用户",
            type : 2,
            area: ['700px', '450px'],
            fixed: false, //不固定
            maxmin: false,
            content : "userEdit.html?orderId="+data.orderId,
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(data){
                    body.find(".userName").val(data.userName);  //用户名
                    body.find("#userId").val(data.userId);
                    form.render();
                }
               /* setTimeout(function(){
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
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
            layer.confirm('确定删除此用户？',{icon:3, title:'提示信息'},function(index){
                 $.get(getRootPath()+"/user/del.action",{
                     userId : data.userId  //将需要删除的newsId作为参数传入
                 },function(data){
                    tableIns.reload();
                    layer.close(index);
                 })
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