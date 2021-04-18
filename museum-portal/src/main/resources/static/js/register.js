let app = new Vue({
    el:'#app',
    data:{
        inviteCode:'',
        phone:'',
        nickname:'',
        password:'',
        confirm:'',
        message: '',
        hasError: false
    },
    methods:{
        //当用户点击注册时运行这个方法
        register:function () {
            console.log('Submit');
            //为了将表单中的信息发送给java的Controller
            //我们需要定义这个局部变量data
            // 真正和实体类属性必须一致的是这个data中的属性名
            let data = {
                inviteCode: this.inviteCode,
                phone: this.phone,
                nickname: this.nickname,
                password: this.password,
                confirm: this.confirm
            }
            console.log(data);
            if(data.password !== data.confirm){
                this.message = "确认密码不一致！";
                this.hasError = true;
                return;
            }
            //这里的this指的是vue对象app
            //因为进入ajax方法后再使用this就是ajax对象了
            //如果想在ajax方法中使用vue绑定的变量
            //就需要事先声明一个变量引用当前的this对象
            let _this = this;
            $.ajax({
                url:"/register",
                method: "POST",
                data: data,
                //function(r)中的r就是java控制器返回的R类对象的json格式
                //例如{"code":200,"message":"OK","data":null}
                success: function (r) {
                    console.log(r);
                    //CREATED是常量数值201来自utils.js
                    if(r.code == CREATED){
                        console.log("注册成功");
                        console.log(r.message);
                        _this.hasError = false;
                        location.href = '/login.html?register';
                    }else{
                        console.log(r.message);
                        _this.hasError = true;
                        _this.message = r.message;
                    }
                }
            });
        }
    }
});