<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>
<div>
    <h2 >Spittle for ${spitter.username}.</h2>
    <table class="horizontalRuled" cellspacing="15">
        <c:forEach var="spittle" items="${modelsl}" >
            <s:url var="spitter_url" value="/spitters/${spittle.spitter.username}"  />
            <tr id="showSpittle">
                <td><img src="/spitter/resources/images/${spitter.userportrait}" width="48" border="0" align="middle" onError="this.src='<s:url value="/resources/images"/>/spitter_avatar.png';"/></td>
                <td><a href="${spitter_url}">${spittle.spitter.username}</a>
                    <small><c:out value="${spittle.text}" /></small><br/>
                    <small><c:out value="${spittle.whens}" /></small>
                    <s:url var="spittle_url" value="/spittles/${spittle.id}" />
                    <sf:form method="delete" action="${spittle_url}" name="deleteSpittle_${spittle.id}">
                        <div class="btn-group" data-toggle="buttons">
                            <label class="btn btn-primary" onclick="commentsShow(${spittle.id})">
                                <input type="radio" name="options" id="option1" >Comment</label>
                            <label class="btn btn-primary" onclick="spittleEdit(${spittle.id})">
                                <input type="radio" name="options" id="option2">Edit</label>
                            <label class="btn btn-primary" onclick="spittleDelete(${spittle.id})">
                                <input type="radio" name="options" id="option3">Delete</label>
                        </div>
                    </sf:form></td></tr>
            <tr id="showComments">
                <td></td>
                <td id="forDetail${spittle.id}"></td></tr>
            <tr id="newComments">
                <td></td>
                <td id="spitComment${spittle.id}" style="display: none">
                    <div class="input-group" >
                        <input id="spitCommentIn" type="text" class="form-control" onfocus="x12">
                        <span id="spitComment" class="input-group-addon" onclick="commentsUp(${spittle.id})">coment it.</span>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<script type="text/javascript">
    function commentsDelete(d,u) {
        $(function () {
            alert("d="+d+" u="+u)
            var formData = new FormData();
            formData.append("commentid",d);
            $.ajax({
                url: "${pageContext.request.contextPath}/updateComment",
                type: 'post',
                data: formData,
                processData: false,
                contentType: false,
                success: function(Data){
                    var content = "";
                    console.info("update");
                    console.info(Data);
                    $.each(JSON.parse(Data),function (idx,obj) {
                        content += "<a>" + obj.name
                                +"</a><br>" + obj.comment
                                +"<br><button class=\"btn btn-primary\" onclick=\"commentsDelete(" + obj.id + "," + obj.spittleid
                                +")\">delete</button><br>";
                    });
                    document.getElementById("forDetail" + u).innerHTML = content;
                    $('#spitCommentIn').val("");
                },error: function(Data){
                    alert("error");
                }
            })
        });
    }

    function commentsShow(i){
        var content = "";
        <c:forEach items="${modelcom}" var="comments" >
        if(i == ${comments.spittleid})
            content += "<a>${comments.name}</a><br>${comments.comment}<br><button class=\"btn btn-primary\" onclick=\"commentsDelete(${comments.id})\">delete</button><br>";
        </c:forEach>
        document.getElementById("forDetail" + i).innerHTML = content;
        document.getElementById("spitComment"+i).style.display="";
    }

    function commentsUp(u) {
        $(function () {
            var formData = new FormData();
            formData.append("spittleId",u);
            formData.append("spitterId",${sessionSpitter.id});
            formData.append("comment",$('#spitCommentIn').val());
            $.ajax({
                url: "${pageContext.request.contextPath}/uploadComment",
                type: 'post',
                data: formData,
                processData: false,
                contentType: false,
                success: function(Data){
                    var content = "";
                    console.info("upload");
                    console.info(Data);
                    $.each(JSON.parse(Data),function (idx,obj) {
                        content += "<a>" + obj.name
                                  +"</a><br>" + obj.comment
                                  +"<br><button class=\"btn btn-primary\" onclick=\"commentsDelete(" + obj.id + "," + obj.spittleid
                                  +")\">delete</button><br>";
                    });
                    document.getElementById("forDetail" + u).innerHTML = content;
                    $('#spitCommentIn').val("");
                },error: function(Data){
                    alert("error");
                }
            });
        });
    }

    function spittleDelete(i) {
        if(confirm("Are you sure you want to delete this spittle?")) {
            document["deleteSpittle_" + i].submit();
        }
    }

    function spittleEdit(){
    }
</script>










