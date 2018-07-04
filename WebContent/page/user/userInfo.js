/**
 * Created by Administrator on 2018/7/2 0002.
 */
layui.use(['form','layer'],function(){
    var form = layui.form
    layer =layui.layer ,
        $ = layui.jquery;



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