<%--
  Created by IntelliJ IDEA.
  User: adergham
  Date: 28/12/12
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>mon chat</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'chat.css')}" type="text/css">
    <r:require module="grailsEvents"/>

    <r:layoutResources />
</head>
<body>
<div class="myChat">
    <div>
        <div id="messageLog" class="messageLog"></div>
        <div class="users">
            <g:each in="${contacts}">
                <div>${it.name}</div>
            </g:each>
        </div>
    </div>
    <div>
        <form id="chatInput">
            <span>
                <input id="inputField" type="text" class="inputText"/>
            </span>
        </form>
    </div>

</div>
<div id="uri" style="visibility:hidden">${createLink(uri: '')}</div>
<r:layoutResources />
<g:javascript src="chat.js" />
</body>
</html>