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
    <r:script>
        window.grailsEvents = new grails.Events('${createLink(uri: '')}', {transport:'sse'});

        //grailsEvents.on("afterInsert", function(data){
        //        console.log("goal")
        //});


        grailsEvents.on("displayMessage", function(data){
            $('<div><span class="author">'+data.author+'</span> : <span class="message">' + data.text + '</span></div>').appendTo("#messageLog")
        }); //will listen for server events on 'savedTodo' topic
    </r:script>
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
        <span>
            <input type="text" class="inputText"/>
        </span>
        <span class="sendButton">
            <input type="button" id="enter" value="entrer">
        </span>
    </div>

</div>
<r:layoutResources />
</body>
</html>