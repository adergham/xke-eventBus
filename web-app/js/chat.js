var uri = $("#uri").text()
window.grailsEvents = new grails.Events(uri, {transport:'sse'});


grailsEvents.on("displayMessage", function(data){
    $('<div><span class="author">'+data.author+'</span> : <span class="message">' + data.text + '</span></div>').appendTo("#messageLog")
});



grailsEvents.on("addContactToList", function(data){
    $('<div id="'+data.ip+'-'+data.port+'">'+data.name+'</div>').appendTo("#users")
});

grailsEvents.on("removeContactFromList", function(data){
    var id =  '#' + data.ip.replace(/(:|\.)/g,'\\$1') +"-"+data.port
    $(id).remove()
});

$("#chatInput").submit(function(e){
    e.preventDefault()
    var input  =  $("#inputField").val()

    // we send the input to the event bus :
    grailsEvents.send('messageInput', input)
    $("#inputField").val("")
})

