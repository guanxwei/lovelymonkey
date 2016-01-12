/**
 * @author guanxwei
 */
 function loadMenuByLevel() {
     var url = "../system/loadMenuBar.htm";
     var content;
     $.ajax({
         
     });
     return content;
 }

 function Menu(name, url) {
    this.name = name;
    this.url = url;
    click = function() {
    }
    return click;
 }

 function renderContentToDistrict(id, content) {
    $("." + id).contents(content);
 }