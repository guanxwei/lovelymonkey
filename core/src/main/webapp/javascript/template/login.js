function validate(url, successResponse) {
    $.ajaxSetup({  
        async : false  
    });
    var result = false;
    $.get(url, function(response) {
        console.log(typeof response);
        console.debug(successResponse);
        console.debug(response);
        if (successResponse == response) {
            result = true;
        }
    });
    $.ajaxSetup({  
        async : true 
    });
    return result;
}

$(function () {   
    $('.list-inline li > a').click(function () {
        var activeForm = $(this).attr('href') + ' > form';
        //console.log(activeForm);
        $(activeForm).addClass('magictime swap');
        //set timer to 1 seconds, after that, unload the magic animation
        setTimeout(function () {
            $(activeForm).removeClass('magictime swap');
        }, 1000);
    });

    var loginSubmitListener = function(response) {
        console.debug(response);
        if (response == "success") {
            window.location.href = "dashboard.html";
        } else {
            loginFails = response.split(":")[1];
            alert("Wrong username or password, please retry!");
        } 
    }

    var singUpSubmitListener = function(response) {
        console.debug(response);
        if (response == "success") {
            window.location.href = "dashboard.html";
        } else {
            alert("The system is busy, please try again later");
        }
    }

    var passWordResetListener = function(response) {
        console.debug(response);
        $("#forgot button").before("<p style='color:red'>Password reset email has been sent to your mail, please go to check</p>");
        $("#forgot button").remove();

    }

    var canBeSubmited = false;
    var loginFails = 0;

    var signUpValidator = function() {
        let userNameEligible = validate('user/judge.htm?userName=' + $("#signup input[name='userName']").val(), "false");
        let emailEligible = validate('user/verifymail.htm?email=' + $("#signup input[name='email']").val(), "false");
        canBeSubmited = userNameEligible && emailEligible && ($("#signup input[name='passWord']").val() == $("#signup input[name='passWordRetry']").val());
    }

    var loginValidator = function() {
        if (loginFails > 5) {
            alert("Retry times has exceeded the limited, take a rest!");
            cabBeSubmited = false;
        }
        cabBeSubmited = true;
    }

    function registerFormSubmitListener(identity, dataType, successCallBack, validator) {
        $(identity).click(function(event) {
            event.preventDefault();
            var url = $(this).parent().attr('action');
            var data = $(this).parent().serialize();
            console.debug(url);
            console.debug(validate);
            if (validator != undefined) {
                validator();
                if (!canBeSubmited) {
                    console.debug("Theis is something wrong in the form, please check again");
                    alert("Theis is something wrong in the form, please check again");
                    return;
                }
            }
            $.ajax({
                url: url,
                type: 'POST',
                dataType: dataType,
                data: data,
            })
            .done(function(response) {
                successCallBack(response);
            })
            .fail(function() {
                console.log("error");
            })
        })
    }

    //register submit event listener for the forms
    registerFormSubmitListener("#login button", "text", loginSubmitListener);
    registerFormSubmitListener("#signup button", "text", singUpSubmitListener, signUpValidator);
    registerFormSubmitListener("#forgot button", "text", passWordResetListener);
});