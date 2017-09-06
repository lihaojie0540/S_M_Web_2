<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page isELIgnored="false" %>

<h2 >Hello, ${sessionSpitter.username}</h2>
<div>
    <img src="/spitter/resources/images/${sessionSpitter.userportrait}" width="256" border="0" align="middle"/>
</div>
<div>
    <span class="label label-primary">
        <a href="" >profiles</a>
    </span>
    <span class="label label-success">
        <a href="" >arts</a>
    </span>
    <span class="label label-info">
        <a href="" >update information</a>
    </span>
</div>
</br>
<div>
    <span class="label label-warning">
        <a href="../home/${sessionSpitter.username}" >home page</a>
    </span>
    <span class="label label-danger">
        <a href="/spitter/home">login out</a>
    </span>
</div>

<script>

</script>

