/*构造函数*/
var Index = function (param) {
    return this;
}

/*初始化函数*/
Index.prototype.init = function () {
    this.attachEvent();
    return this;
}

/*注册函数*/
Index.prototype.attachEvent = function () {
    /*登陆操作*/
    $("#Index").on("click",function () {
        var num = $("#num").val().trim();
        var password = $("#password").val().trim();

        var jsjson = {};
        jsjson['num'] = num;
        jsjson['password'] = password;

        $.ajax({
            url: bashPath + "/rest/api/innovation/user/numpwd/",
            type: "post",
            data: JSON.stringify(jsjson),
            dataType: 'json',
            contentType:'application/json',
            success: function (data) {
                console.log(data)
                if(data.result == '0000') {
                    // window.location.href = bashPath + '/rest/index'
                    window.open(bashPath + '/rest/index','_parent');
                } else {

                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("访问后台发生错误:" + XMLHttpRequest.status)
            }
        });
    })
}
