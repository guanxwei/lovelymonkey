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

    $(".btn").click(function(event) {
        event.preventDefault();
        var url = $(this).parent().attr('action');
        var data = $(this).parent().serialize();
        console.debug(url);
        console.debug(data);
        $.ajax({
            url: url,
            type: 'POST',
            dataType: 'text',
            data: $(this).parent().serialize(),
        })
        .done(function(response) {
            console.debug("success");
            console.debug(response);
            if (response === 'failed') {
                alert("Wrong username or password, please retry!");
            }
        })
        .fail(function(error) {
            console.debug("error");
        })
        .always(function() {
            console.debug("complete");
        });
        
    });

});