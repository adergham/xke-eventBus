package fr.xke.chat.controller

import fr.xke.chat.domain.Message


class ChatControllerIntegrationTests  extends GroovyTestCase {

    void testReceiveMessageAndLogIt(){
        def controller =  new ChatController()
        controller.params.message = "helloworld!"
        controller.params.author = "Agathe Zeblouse"

        controller.receiveMessage()

        sleep(1000l)

        def expectedMsg  = Message.findByAuthorName("Agathe Zeblouse")
        assert expectedMsg.message == "helloworld!"
    }

    void testReceiveMessageAndDisplayIt(){
        def controller =  new ChatController()
        controller.params.message = "vous ne passerez pas!"
        controller.params.author = "Gandalf l'aigri"

        def hasFiredDisplayEvent = false
        controller.on("message.received"){
            hasFiredDisplayEvent = true
        }

        controller.receiveMessage()

        sleep(1000l)

        assert hasFiredDisplayEvent == true
    }
}
