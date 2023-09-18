layui.use(['form', 'layer'], function(){
    var form = layui.form;
    var layer = layui.layer;
    //监听提交
    form.on('submit(formSubmit)', function(data){
    layer.msg(JSON.stringify(data.field));
    $.post("http://localhost:8080/education/grade", data.field,function(data){
        if (data.code === 200) {
            layer.msg('添加成功');
        } else {
            layer.msg('添加失败');
        }
    });
    return false;
    });
});