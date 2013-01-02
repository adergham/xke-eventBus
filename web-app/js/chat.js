var uri = $("#uri").text()
window.grailsEvents = new grails.Events(uri, {transport:'sse'});


grailsEvents.on("displayMessage", function(data){
    $('<div><span class="author">'+data.author+'</span> : <span class="message">' + data.text + '</span></div>').appendTo("#messageLog")
});

$("#chatInput").submit(function(e){
    e.preventDefault()
    var input  =  $("#inputField").val()

    // we send the input to the event bus :
    grailsEvents.send('messageInput', input)
    $("#inputField").val("")
})

