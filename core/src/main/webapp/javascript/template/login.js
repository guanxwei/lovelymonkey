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
             window.location.href = "index.html";
        } else {
             alert("Wrong username or password, please retry!");
        } 
    }

    var singUpSubmitListener = function(response) {
        console.debug(response);
        if (response == "success") {
            window.location.href = "index.html";
        } else {
            alert("The system is busy, please try again later");
        }
    }

    var passWordResetListener = function(response) {
        console.debug(response);
        if (response == "success") {
        }
    }
    var passReset = function(response) {
        console.debug(response);
        if (response == "true") {

        }
    }

    var canBeSubmited = false;
    var signUpValidator = function() {
        canBeSubmited = emailEligible && userNameEligible && ($("#signup input[name='passWord']").val() == $("#signup input[name='passWordRetry']").val());
    }

    function registerFormSubmitListener(identity, dataType, successCallBack, validate) {
        $(identity).click(function(event) {
            event.preventDefault();
            var url = $(this).parent().attr('action');
            var data = $(this).parent().serialize();
            console.debug(url);
            console.debug(validate);
            if (validate != undefined) {
                var isReady = validate();
                if (!isReady) {
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
});