/**
 * @author guanxwei
 * @ @since 2015.11.11
 */

 /**
  * @return {[type]}
  */
 function login() {
    var userName = $(".userName").val();
    var pass = $(".passWord").val();
    $.ajax({
        url: '../user/doLogin.html',
        type: 'POST',
        dataType: 'json',
        data: {
            userName: userName,
            passWord: passWord
        },
        success: function(data) {
            if (data == "success") {
                window.location.url = "../home/dashboard.htm";
            } else {
                alert("用户名或密码错误，请重试！");
            }
        }
    })
 }

 /** 
  * @return {[type]} 
 */
 function checkUserName() {
    var userName = $(".userName").val();
    $.$.ajax({
        url: '../user/judge.htm?userName=' + userName,
        type: 'GET',
        data: {param1: 'value1'},
    })
    .done(function(response) {
        if (response == "true") {
            //通过验证，当前用户输入的用户名可以使用
        } else {
            //用户名没有通过验证，当前用户名已经被别人使用
            $(".userNameErrorInfo").removeClass("display_error");
            $(".userNameErrorInfo").addClass("display_error");
        }
    });
 }