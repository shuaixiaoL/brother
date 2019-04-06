/*构造函数*/
var Login = function (param) {
    return this;
}

/*初始化函数*/
Login.prototype.init = function () {
    this.attachEvent();
    return this;
}

/*注册函数*/
Login.prototype.attachEvent = function () {
    /*登陆操作*/
    $("#login").on("click",function () {
        var num = $("#num").val().trim();
        var password = $("#password").val().trim();
        var roleId = $("#roleId").val().trim();

        $.ajax({
            url: contextPath + "/rest/login2",
            type: "get",
            data: /*JSON.stringify(jsjson)*/{
                usercode : num,
                password : password,
                roleId: roleId
            },
            timeout: 20000,
            success: function (data) {
                console.log(data)
                if(data.result == '0000') {
                    // window.location.href = bashPath + '/rest/index'
                    window.open(bashPath + '/rest/index','_parent');
                } else {
                    swal("账号密码错误", "", "error")
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("访问后台发生错误:" + XMLHttpRequest.status)
            }
        });
    });
    /*注册操作*/
    $("#regist").on("click",function () {
        var name = $("#name").val().trim();
        if(!name) {
            swal("昵称不能为空", "", "error");
            return;
        }
        var usercode = $("#usercode").val().trim();
        if(!usercode) {
            swal("用户名不能为空", "", "error");
            return;
        }
        var pwd = $("#pwd").val().trim();
        var rePwd = $("#rePwd").val().trim();
        if(pwd != rePwd) {
            swal("密码不一致", "", "error")
            return;
        }

        $.ajax({
            url: contextPath + "/rest/regist2",
            type: "get",
            data: /*JSON.stringify(jsjson)*/{
                usercode: usercode,
                username: name,
                password: pwd
            },
            timeout: 20000,
            success: function (data) {
                console.log(data)
                if(data.result == '0000') {
                    swal(data.msg, "", "success")
                } else {
                    swal(data.msg, "", "error")
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("访问后台发生错误:" + XMLHttpRequest.status)
            }
        });

    });
}
