
function initData()
{
    $.ajax(
        {
       type:'GET',
        dataType: "json",
        url:"/user/userinfo",
        success:function(res) {
            alert("加载数据");
            if (res.status == "0") {
                console.log(res);
                alert(res.data.userName);
                var info = res.data;
                console.log(res.data.data.userName)
            } else
                console.log("error")

        }
    })
}
$(window).load( function(){
   initData()
} );