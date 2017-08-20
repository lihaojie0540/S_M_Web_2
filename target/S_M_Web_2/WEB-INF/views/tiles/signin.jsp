<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page isELIgnored="false" %>

    <h2>Hello, ${modelsp.username}${spitter.username}</h2>
    <h2>modelsp=${modelsp.userportrait}</h2>
    <h2>spitter=${spitter.userportrait}</h2>
    <div>
        <img src="<s:url value="/resources/images/${modelsp.userportrait}"/>"
             width="256"
             border="0"
             align="middle"
             onError="this.src='<s:url value="/resources/images"/>/${spitter.userportrait}';"/>
    </div>
    <div>
        <span class="label label-warning">
            <a href="../${modelsp.username}${spitter.username}/profiles">create a profile</a>
        </span>
        <span class="label label-primary">
            <a href="../${modelsp.username}${spitter.username}/edit">update information</a>
        </span>
        <span class="label label-success">
            <a href="/spitter/home">login out</a>
        </span>
    </div>

