$(function() {
    signOut();
    handleAjaxError();
});

// 动作延时
var delay = (function(){
    var timer = 0;
    return function(callback, ms){
        clearTimeout (timer);
        timer = setTimeout(callback, ms);
    };
})();

// 注销登陆
function signOut() {
    $("#signOut").click(function() {
        $.ajax({
            type : "GET",
            url : "logout",
            dataType : "json",
            contentType : "application/json",
            success:function(response){
                window.location.reload(true);
            },
            error:function(response){

            }
        })
    })
}

// 处理 Ajax 错误响应
function handleAjaxError(responseStatus, cur){
    var type = 'error';
    var msg  = '';
    var append = '';
    if (responseStatus === 403) {
        msg = '未授权操作';
        append = '对不起，您未授权执行此操作，请联系管理员授权';
        cur.siblings('.tip-error').text(type + msg + append).show();
        // 刷新重新登陆
        delay(function(){
            window.location.reload(true);
        }, 4000);
    } else if (responseStatus === 404) {
        msg = '不存在的操作';
        cur.siblings('.tip-error').text(type + msg + append).show();
    } else if (responseStatus === 430){
        msg = '您的账号在其他地方登陆';
        append = '请确认是否为您本人的操作。若否请及时更换密码';
        cur.siblings('.tip-error').text(type + msg + append).show();
        // 刷新重新登陆
        delay(function(){
            window.location.reload(true);
        }, 4000);
    } else if (responseStatus === 500) {
        msg = '服务器错误';
        append = '对不起，服务器发生了错误，我们将尽快解决，请稍候重试';
        cur.siblings('.tip-error').text(type + msg + append).show();
    } else {
        msg = '遇到未知的错误';
        cur.siblings('.tip-error').text(type + msg + append).show();
    };
}