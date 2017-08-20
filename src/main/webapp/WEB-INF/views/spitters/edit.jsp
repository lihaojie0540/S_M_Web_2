<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false" %>
<div>
    <h2>Update your information.</h2>
    <s:url value="/spitters/${username}/update" var="spitterform_url" />
    <sf:form method="POST" action="${spitterform_url}" modelAttribute="spitter" enctype="multipart/form-data">
        <sf:input path="id" size="19" type="hidden"/>
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
                    <td><sf:input path="password" size="19"
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
                    <td><input name="image" type="file" /></td>
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
                               value="Update my account." /></td>
                </tr>
            </table>
        </fieldset>
    </sf:form>
</div>