/*
显示当前用户的问题
 */
let announcementApp = new Vue({
    el:'#announcementApp',
    data: {
        announcements:[],
        pageInfo:{},
    },
    methods: {
        loadQuestions:function (pageNum) {
            if(! pageNum){
                pageNum = 1;
            }
            $.ajax({
                url: '/v1/announcement',
                method: "GET",
                data:{
                    pageNum:pageNum
                },
                success: function (r) {
                    console.log("成功加载数据");
                    console.log(r);
                    // if(r.code === OK){
                        announcementApp.announcements = r.data.list;
                        announcementApp.pageInfo = r.data;
                        //为question对象添加持续时间属性
                        announcementApp.updateDuration();
                        announcementApp.updateTagImage();
                    // }
                }
            });
        },
        updateTagImage:function(){
            let announcements = this.announcements;
            for(let i=0; i<announcements.length; i++){
                let tags = announcements[i].tags;
                //js中if([变量])这种写法
                // 就是在判断这个变量存在不存在,如果存在返回true
                if(tags){
                    //              /img/tags/1.jpg
                    let tagImage = '/img/tags/'+tags[0].id+'.jpg';
                    console.log(tagImage);
                    announcements[i].tagImage = tagImage;
                }
            }
        },
        updateDuration:function () {
            let announcements = this.announcements;
            //遍历所有问题
            for(let i=0; i<announcements.length; i++){
                //创建问题时候的时间毫秒数
                let createtime = new Date(announcements[i].createtime).getTime();
                //当前时间毫秒数
                let now = new Date().getTime();
                //计算两个事件的毫秒差
                let duration = now - createtime;
                if (duration < 1000*60){ //一分钟以内
                    announcements[i].duration = "刚刚";
                }else if(duration < 1000*60*60){ //一小时以内
                    announcements[i].duration =
                        (duration/1000/60).toFixed(0)+"分钟以前";
                }else if (duration < 1000*60*60*24){
                    announcements[i].duration =
                        (duration/1000/60/60).toFixed(0)+"小时以前";
                }else {
                    announcements[i].duration =
                        (duration/1000/60/60/24).toFixed(0)+"天以前";
                }
            }
        }
    },
    created:function () {
        console.log("执行了方法");
        this.loadQuestions(1);
    }
});










