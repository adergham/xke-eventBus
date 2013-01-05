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

}
