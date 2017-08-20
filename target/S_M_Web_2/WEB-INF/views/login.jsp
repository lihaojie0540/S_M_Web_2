<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false" %>
<div>
    <h2>Sign in to Spitter</h2>
    <p> If you've been using Spitter from your phone,
        then that's amazing...we don't support IM yet. </p>
    <s:url var="authUrl" value="/j_spring_security_check" />
    <sf:form method="POST" action="${authUrl}" modelAttribute="spitter">
        <fieldset>
            <div>
                <input id="username_or_email" name="j_username"
                       width="160px"          type="text"
                       class="form-control"   placeholder="Username or Email"/>
            </div>
            <div>
                <input id="password"        name="j_password"
                       width="160px"        type="password"
                       class="form-control" placeholder="Password"/>
            </div>
            <div>
                <input id="sin" class="bn-submit" type="submit" value="Sign In" />
                <a id="reg" class="lnk-reg" href="/spitter/register">Register</a>
            </div>
            <div>
                <span id="remember_melabs" class="input-group-addon"><input name="_spring_security_remember_me" type="checkbox">Remember me</span>
                <a id="for" href="/account/resend_password">Forgot?</a>
            </div>
        </fieldset>
</sf:form>
<script type="text/javascript">
    document.getElementById('username_or_email').focus();
</script>
</div>
