<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<link href="<s:url value="/resources/css/bootstrap.css.map"/>" rel="stylesheet">
<link href="<s:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
<script src="<s:url value="/resources/js/jquery-3.2.1.min.js"/>"></script>
<script src="<s:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<s:url value="/resources/js/jquerySession.js"/>"></script>
<html>
<head>
    <title>Spitter</title>
    <link href="<s:url value="/resources" />/css/spitter.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="container">
    <div id="logal">
        <a href="<s:url value="/home" />">
            <img src="<s:url value="/resources" />/images/spitter_logo_50.png" border="0"/></a>
    </div>
    <div id="top">
        <t:insertAttribute name="top" />
    </div>
    <div id="content">
        <t:insertAttribute name="content" />
    </div>
    <div id="side">
        <t:insertAttribute name="side" />
    </div>
</div>
</body>
</html>
