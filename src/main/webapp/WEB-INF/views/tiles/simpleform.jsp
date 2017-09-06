<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false" %>

<div>
    <s:url value="/spittles/adds" var="spitterform_url" />
    <form:form method="POST" action="${spitterform_url}" modelAttribute="spittle">
        Spittle:
        <form:input path="text" size="15" />
        <form:input path="spitter.id" type="hidden"  value="${sessionSpitter.id}"/>
        <form:input path="spitter.username" type="hidden"  value="${sessionSpitter.username}"/>
        <input name="commit" type="submit"
               value="Spit it." />
    </form:form>
</div>