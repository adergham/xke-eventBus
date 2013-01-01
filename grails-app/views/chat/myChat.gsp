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
    <title>mon chat</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'chat.css')}" type="text/css">

</head>
<body>
<div class="myChat">
    <div>
        <div class="messageLog"></div>
        <div class="users">
            <g:each in="${contacts}">
                <div>${it.name}</div>
            </g:each>
        </div>
    </div>
    <div>
        <span>
            <input type="text" class="inputText"/>
        </span>
        <span class="sendButton">
            <input type="button" id="enter" value="entrer">
        </span>
    </div>

</div>

</body>
</html>