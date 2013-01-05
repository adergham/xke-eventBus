package fr.xke.chat.controller

import fr.xke.chat.domain.Message


class ChatControllerIntegrationTests  extends GroovyTestCase {

    void testReceiveMessage(){
        def controller =  new ChatController()
        controller.params.message = "helloworld!"
        controller.params.author = "Agathe Zeblouse"

        controller.receiveMessage()

        sleep(1000l)

        def expectedMsg  = Message.findByAuthorName("Agathe Zeblouse")
        assert expectedMsg.message == "helloworld!"

    }
}
