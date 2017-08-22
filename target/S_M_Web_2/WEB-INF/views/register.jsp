<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false" %>

<div>
    <h2>Welcome Join In Spitter </h2>
    <s:url value="/spitters/adds" var="spitterform_url" />
    <sf:form method="POST" action="${spitterform_url}" modelAttribute="spitter" enctype="multipart/form-data">
        <fieldset>
            <table cellspacing="0" id="register11">
                <tr>
                    <th><sf:label path="fullname">Full name:</sf:label></th>
                    <td><sf:input path="fullname" size="19" /><br/>
                        <sf:errors path="fullname" cssClass="error" />
                    </td>
                </tr>
                <tr>
                    <th><sf:label path="username">Username:</sf:label></th>
                    <td><sf:input path="username" size="19" maxlength="15" />
                        <small id="username_msg">No spaces, please.</small><br/>
                        <sf:errors path="username" cssClass="error" />
                    </td>
                </tr>
                <tr>
                    <th><sf:label path="password">Password:</sf:label></th>
                    <td><sf:password path="password" size="19"
                                     showPassword="true"/>
                        <small>6 characters or more (be tricky!)</small><br/>
                        <sf:errors path="password" cssClass="error" />
                    </td>
                </tr>
                <tr>
                    <th><sf:label path="email">Email Address:</sf:label></th>
                    <td><sf:input path="email" size="19"/>
                        <small>In case you forget something</small><br/>
                        <sf:errors path="email" cssClass="error" />
                    </td>
                </tr>
                <tr>
                    <th><label for="image">Profile image:</label></th>
                    <td><input id="file" name="image" type="file" /></td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <div class="progress progress-striped active" style="display: none">
                            <div id="progressBar" class="progress-bar progress-bar-info" role="progressbar"
                                 aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
                                 style="width: 30%;">
                                <span class="sr-only">30% 完成（信息）</span>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>
                    </th>
                    <td>
                        <div class="form-group">
                            <button id="UploadBtn" type="button" class="btn btn-success">upload</button>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <sf:checkbox path="updatebyemail"/>
                        <sf:label path="updatebyemail">
                            Send me email updates!</sf:label>
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td><input name="commit" type="submit"
                               value="I accept. Create my account." /></td>
                </tr>
            </table>
        </fieldset>
    </sf:form>
    <button id="hide1111" type="button">hide</button>
    <button id="show1111" type="button">show</button>
    <div class="form-group">
        <img id="imageUpload" alt="uploaded picture" src="" with="100%" >
    </div>
</div>
<script>

    $(function () {
        $("#hide1111").click(function(){
            $("#progressBar").width("0%");
            $("#hide1111").text("hiding");
            $("#progressBar").parent().hide();
        });
        $("#show1111").click(function(){
            $("#progressBar").parent().show();
            $("#progressBar").width("100%");
            $("#hide1111").text("showing");
        });
    });

    $(function () {
        //上传按钮
        //点击上传按钮
        $("#UploadBtn").click(function(){
            $("#progressBar").width("0%");
            $("#progressBar").attr("disable",true);
            $("#progressBar").parent().show();
            $("#progressBar").parent().addClass("active");
            $("#UploadBtn").text("uploadingggggggggggggggggggg");
            upload("the file upload with progress");
            //upload
        });
        function refresh() {
            setTimeout(function () {
                $("#UploadBtn").text("upload");
                $("#progressBar").removeAttr("disabled");
            },1500);
        };
        function upload(name){
            var formData = new FormData();
            formData.append('file',$('#file')[0].files[0]);
            formData.append('name',name);
            function onprogress(evt){
                var progressBar = $("#progressBar");
                alert("evt");
                alert(evt.lengthComputable.toString());
                if(evt.lengthComputable) {
                    var completePercent = Math.round(evt.loaded / evt.total * 100);
                    progressBar.width(completePercent + "%");
                    $("#progressBar").text(completePercent + "%");
                }
            }
            var xhr_provider = function() {
                var xhr = jQuery.ajaxSettings.xhr();
                if(onprogress && xhr.upload){
                   xhr.upload.addEventListener('progerss', onprogress, false);
                    $("#progressBar").width(100 + "%");
                }else {
                    alert("not in");
                }
                return xhr;
            };
            $.ajax({
                url: "${pageContext.request.contextPath}/upload",
                type: 'post',
                data:formData,
                processData:false,
                contentType: false,
                xhr:xhr_provider(),
                success: function(data){
//                    result = $.parseJSON(data);
//                    if (result.code == "0"){
                        $("#UploadBtn").text("upload success");
//                    } else if(result.code == "-4"){
//                        $("#uploadBtn").text("不支持文件上传类型");
//                    } else {
//                        $("#uploadBtn").text(result.data);
//                    }
//                    $("#progressBar").parent().hide();
                    refresh();
                },error: function(data){
                    $("#UploadBtn").text("unsupport filetype");
                    refresh();
                }
            });
        }
    });
</script>