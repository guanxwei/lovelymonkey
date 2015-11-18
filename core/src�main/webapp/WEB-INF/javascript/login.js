/**
 * @author guanxwei
 */
function login() {
    $.ajax({ ache: true,
        type: "POST",
        url:ajaxCallUrl,
        async: false,
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
            if ("success" == data) {
                
            } else {
                
            }
        }
    });
}
