<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>
<div>
    <h2>Spittle for ${modelsp.username}.</h2>
    <table cellspacing="15" class="horizontalRuled">
        <c:forEach items="${modelsl}" var="spittle">
            <s:url value="/spitters/${spittle.spitter.username}" var="spitter_url" />
            <tr>
                <td>
                    <img src="<s:url value="/resources/images/${modelsp.userportrait}"/>"
                         width="48"
                         border="0"
                         align="middle"
                         onError="this.src='<s:url value="/resources/images"/>/spitter_avatar.png';"/></td>
                <td><a href="${spitter_url}">${spittle.spitter.username}</a>
                    <small><c:out value="${spittle.text}" /><br/><small>
                        <c:out value="${spittle.whens}" />
                    </small></small>
                    <s:url value="/spittles/${spittle.id}" var="spittle_url" />
                    <sf:form method="delete" action="${spittle_url}"
                             name="deleteSpittle_${spittle.id}">
                    <div class="btn-group" data-toggle="buttons">
                        <label class="btn btn-primary" onclick="showComment11(${spittle.id})" >
                            <input type="radio" name="options" id="option1" >Comment
                        </label>
                        <label class="btn btn-primary" onclick="editSpittle(${spittle.id})">
                            <input type="radio" name="options" id="option2">Edit
                        </label>
                        <label class="btn btn-primary" onclick="deleteSpittle(${spittle.id})">
                            <input type="radio" name="options" id="option3">Delete
                        </label>
                    </div>
                    </sf:form>
                </td>
            </tr>
            <tr id="contentsss">
                <td>
                </td>
                <td id="fordetail${spittle.id}">
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<script type="text/javascript">
    function deleteSpittle(i) {
        if(confirm("Are you sure you want to delete this spittle?")) {
            document["deleteSpittle_" + i].submit();
        }
    }

    function showComment11(i){
        var content = "";
        <c:forEach items="${modelcom}" var="comments" >
        if(i == ${comments.spittleid})
            content += "<h4>"+"${comments.name}"+"</h4>"+"${comments.comment}";
        </c:forEach>
        document.getElementById("fordetail" + i).innerHTML = content;
    }

</script>










