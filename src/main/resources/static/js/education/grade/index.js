layui.use('table', function () {
    var table = layui.table;
    // 菜单表格渲染
    table.render({
        elem: '#gradeList',
//        height: 312,
        url: '/education/grade',
        parseData: function (res) { //res 即为原始返回的数据
            return {
                "code": res.code, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.data.total, //解析数据长度
                "data": res.data.menuList //解析数据列表
            }
        },
        page: true,
        cols: [
            [
                { field: 'id',title: 'ID',sort: true,fixed: 'left'},
                { field: 'name', title: '班级名称'},
                { title: '操作', fixed: 'right',align: 'center', toolbar: '#barTool'},
            ]
        ]
    });

    // 编辑当前班级
    function editCurrentGrade(gradeId) {
        layer.open({
            title :'编辑班级',
            type: 2,
            content: 'http://localhost:8080/education/grade/add.html?gradeId='+gradeId,
            area: ['600px', '400px'],
            cancel: function(){
                table.reload('gradeList', {
                    url: '/education/grade',
                    where: {'page': 1, 'limit': 10},
                });
            }
        })
    }
    // 查看当前班级下的所有学生
    function viewStudentList(gradeId) {
        layer.open({
            title :'编辑班级',
            type: 2,
            content: 'http://localhost:8080/education/grade/studentList.html?gradeId='+gradeId,
            area: ['600px', '400px'],
            cancel: function(){
                table.reload('gradeList', {
                    url: '/education/grade',
                    where: {'page': 1, 'limit': 10},
                });
            }
        })
    }
    // 删除当前班级
    function deleteCurrentGrade(grade) {
        $.post("http://localhost:8080/education/grade/delete", grade, function (data) {
            if (data.code === 200) {
                layer.msg('删除成功');
            } else {
                layer.msg('删除成功');
            }
        });
    }
    //工具条事件
    table.on('tool(gradeList)', function (obj) {
        var data = obj.data; //获得当前行数据
        var gradeId = data.id;
        console.log(data)
        console.log(obj)
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

        if (layEvent === 'detail') { //查看
            viewStudentList(gradeId);
        } else if (layEvent === 'del') { //删除
            layer.confirm('真的删除行么', function (index) {
                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                layer.close(index);
                //向服务端发送删除指令
                deleteCurrentGrade(data)
            });
        } else if (layEvent === 'edit') { //编辑
            //do something
            editCurrentGrade(gradeId);
            //同步更新缓存对应的值
            obj.update({
                username: '123',
                title: 'xxx'
            });
        } else if (layEvent === 'LAYTABLE_TIPS') {
            layer.alert('Hi，头部工具栏扩展的右侧图标。');
        }
    });
});