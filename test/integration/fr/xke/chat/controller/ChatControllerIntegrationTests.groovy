package fr.xke.chat.controller

import fr.xke.chat.domain.Message


class ChatControllerIntegrationTests  extends GroovyTestCase {

    void testReceiveMessage(){
        def controller =  new ChatController()
        controller.params.message = "helloworld!"
        controller.params.author = "Agathe Zeblouse"

        controller.receiveMessage()

        sleep(1000l)

        def messages  = Message.list()
        assert messages.size() == 1
        assert messages[0].authorName == "Agathe Zeblouse"
        assert messages[0].message == "helloworld!"

    }
}
