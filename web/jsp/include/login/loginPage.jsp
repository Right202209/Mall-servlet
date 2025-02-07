
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    var msg = "${msg}";
    if(msg !== ""){
        alert(msg);
    }
    $(function () {
        $("#refer").val(document.referrer);
        $("#form").submit(function () {
            if($("#password").val() == "" || $("#name").val() == ""){
                alert("用户名或密码为空");
                return false;
            }
            return true;
        });
    });
</script>
<main class="login">
    <div class="login-content">
        <form method="post" action="loginIn" id="form">
        <div class="login-frame">
            <div class="login-tip">密码登录</div>
            <div class="login-input">
        <span class="login-input-icon">
                    <span class=" glyphicon glyphicon-user"></span>
                </span>
                <input type="text" placeholder="手机/会员名/邮箱" name="name" id="name" autofocus>
            </div>
            <div class="login-input">
        <span class="login-input-icon ">
                    <span class=" glyphicon glyphicon-lock"></span>
                </span>
                <input type="password" placeholder="密码" name="password" id="password">
                <input type="hidden" name="refer" id="refer">
            </div>
            <div class="login-button">
                <button type="submit" class="login-button">登 录</button>
            </div>
            <div class="login-bottom">
                <a href="#nowhere">忘记登录密码</a>
                <a class="" href="register">免费注册</a>
            </div>

        </div>
        </form>
    </div>
</main>