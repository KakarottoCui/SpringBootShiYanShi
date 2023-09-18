layui.use(['layer', 'jquery', 'form'], function () {
    var layer = layui.layer,
        $ = layui.jquery,
        form = layui.form;
    //监听提交
    form.on('submit(formSubmit)', function(data){
        layer.msg(JSON.stringify(data.field));
        $.post("http://localhost:8080/reservation/teacher", data.field,function(data){
            if (data.code === 200) {
                layer.msg('添加成功');
            } else {
                layer.msg('添加失败');
            }
        });
        return false;
    });

    form.on('select(part)', function (data) {
        console.log(data);
        var startWeek = $('#startWeek option:selected').val();
        var day = $('#day option:selected').val();
        var part = $('#part option:selected').val();

        console.log(startWeek);
        console.log(day);
        console.log(part);
        var url = '?startWeek='+startWeek+'&'+'day='+day+'&'+'part='+part;

        var libOptions = '';
        $.get("http://localhost:8080/reservation/lib"+url,function(data){
            if (data.code === 200) {
                for (var lib of data.data) {
                    libOptions += '<option value="'+lib.id+'">'+lib.name+'</option>';
                }
                $("#lib").html(libOptions);
                form.render('select');
            } else {
                layer.msg('查询实验室失败');
            }
        });


        var teacherId =  getCookie('cookie_username');
        console.log(teacherId);
        url += '&teacherId='+teacherId;

        var gradeOptions = '';
        $.get("http://localhost:8080/reservation/grade"+url,function(data){
            if (data.code === 200) {
                for (var grade of data.data) {
                    gradeOptions += '<option value="'+grade.id+'">'+grade.name+'</option>';
                }
                $("#grade").html(gradeOptions);
                form.render('select');
            } else {
                layer.msg('查询实验室失败');
            }
        });


    });

    function getCookie(name){
        var strcookie = document.cookie;//获取cookie字符串
        var arrcookie = strcookie.split("; ");//分割
        //遍历匹配
        for ( var i = 0; i < arrcookie.length; i++) {
            var arr = arrcookie[i].split("=");
            if (arr[0] == name){
                return arr[1];
            }
        }
        return "";
    };

});