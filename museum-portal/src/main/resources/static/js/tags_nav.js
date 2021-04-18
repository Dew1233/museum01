
let tagsApp = new Vue({
    el:'#categsApp',
    data:{
        tags:[]
    },
    methods:{
        loadTags:function () {
            console.log('执行了 loadTags');
            $.ajax({
                url:'/v1/category',
                method:'GET',
                success:function (r) {
                    console.log(r);
                        console.log('成功获取tags');
                        tagsApp.tags = r.data;
                }
            });
        }
    },
    //这个方法会在页面加载完毕之后立即执行
    created:function () {
        //调用上面编写的显示所有标签的方法
        this.loadTags();
    }
});