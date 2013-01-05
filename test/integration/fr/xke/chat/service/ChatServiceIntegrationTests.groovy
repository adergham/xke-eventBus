package fr.xke.chat.service

import fr.xke.chat.domain.Contact
import fr.xke.chat.domain.Message


class ChatServiceIntegrationTests  extends GroovyTestCase {

    def grailsEvents
    def chatService


    public void testInputMessageFromUIToDatabase(){
        grailsEvents.event("*", "message.input", ["text":"I'm still here"])
        sleep(1000l)
        def expectedMsg = Message.findByAuthorName("Jean-Raoul Ducable")
        assert expectedMsg.message == "I'm still here"


    }

    public void testInputMessageFromUIToBroadcastAndDisplay(){
        Contact friend =  new Contact(ip: "127.0.0.1", port: "8080", name: "Edouard Bracame", isValid: true)
        friend.save()

        def hasFiredDisplayEvent = false
        chatService.on("display.message"){
            hasFiredDisplayEvent = true

        }
        grailsEvents.event("*", "message.input", ["text":"I'm still here"])
        sleep(1000l)


        assert hasFiredDisplayEvent == true


    }
}
