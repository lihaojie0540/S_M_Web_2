<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page isELIgnored="false" %>

<h2>Hello, ${spitter.username}</h2>

<div>
    <img src="/spitter/resources/images/${spitter.userportrait}"
         width="256"
         border="0"
         align="middle"/>
</div>

<div>
    <span class="label label-primary">
        <a href="../${spitter.username}/profiles">profiles</a>
    </span>
    <span class="label label-success">
        <a href="../${spitter.username}/arts">arts</a>
    </span>
    <span class="label label-info">
        <a href="../${spitter.username}/edit">update information</a>
    </span>
</div>
</br>
<div>
    <span class="label label-warning">
        <a href="../home/${spitter.username}">home page</a>
    </span>
    <span class="label label-danger">
        <a href="/spitter/home">login out</a>
    </span>
</div>

