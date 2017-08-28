<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>
<div>
    <h2>A global community of friends and strangers spitting out their
        inner-most and personal thoughts on the web for everyone else to
        see.</h2>
    <h3>Look at what these people are spitting right now...</h3>
    <ol class="spittle-list">
        <c:forEach var="spittle" items="${modelsl}">
            <s:url value="/spitters/{spitterName}" var="spitter_url" >
                <s:param name="spitterName" value="${spittle.spitter.username}" />
            </s:url>
            <li>
                <span class="spittleListImage">
                    <img src="/spitter/resources/images/${spittle.spitter.userportrait}"
                            width="48"
                            border="0"
                            align="middle"
                            onError="this.src='<s:url value="/resources/images"/>/spitter_avatar.png';"/>
                </span>
                <span class="spittleListText">
                    <a href="${spitter_url}">
                        <c:out value="${spittle.spitter.username}" /></a>
                         - <c:out value="${spittle.text}" /><br/>
                    <small>
                        <c:out value="${spittle.whens}" />
                    </small>
                </span>
            </li>
        </c:forEach>
    </ol>
</div>