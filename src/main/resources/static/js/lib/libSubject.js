var GET_TABLE_URL = '/lib/subject';
var EDIT_DATA_URL = 'http://localhost:8080/lib/add.html?libId=';
var DELETE_DATA_URL = "http://localhost:8080/lib/delete";

var TABLE_LIST_NAME = 'subjectList';

layui.use(['table','form'], function () {
    var table = layui.table;
    // 菜单表格渲染
    table.render({
        elem: '#tableList',
//        height: 312,
        limit: 5,
        url: GET_TABLE_URL,
        parseData: function (res) { //res 即为原始返回的数据
            return {
                "code": res.code, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.data.total, //解析数据长度
                "data": res.data.libList //解析数据列表
            }
        },
        page: true,
        cols: [
            [
                { field: 'id',title: 'ID',sort: true,fixed: 'left'},
                { field: 'name', title: '实验室名称'},
                { field: 'number', title: '实验室编号'},
                { field: 'place', title: '地点'},
                { field: 'manager', title: '管理人'},
                { field: 'quantity', title: '容纳人数'},
                { field: 'remark', title: '备注'},
                { title: '操作', fixed: 'right',align: 'center', toolbar: '#barTool'}
            ]
        ]
    });


    // 编辑当前行数据
    function editCurrentLine (id) {
        layer.open({
            title :'编辑菜单',
            type: 2,
            content: EDIT_DATA_URL+id,
            area: ['600px', '400px'],
            cancel: function(){
                table.reload('tableList', {
                    url: GET_TABLE_URL,
                    where: {'page': 1, 'limit': 5},
                });
            }
        })
    }

    // 删除当前行数据
    function deleteCurrentLine(obj) {
        $.post(DELETE_DATA_URL, obj, function (data) {
            if (data.code === 200) {
                layer.msg('删除成功');
            } else {
                layer.msg('删除成功');
            }
        });
    }
    //工具条事件
    table.on('tool(tableList)', function (obj) {
        var data = obj.data; //获得当前行数据
        var id = data.id;
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

        if (layEvent === 'detail') { //查看
            //viewChildMenu(menuId);
        } else if (layEvent === 'del') { //删除
            layer.confirm('真的删除行么', function (index) {
                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                layer.close(index);
                //向服务端发送删除指令
                deleteCurrentLine(data)
            });
        } else if (layEvent === 'edit') { //编辑
            //do something
            editCurrentLine(id);
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