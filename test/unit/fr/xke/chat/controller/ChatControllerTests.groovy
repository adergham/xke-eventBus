package fr.xke.chat.controller

import fr.xke.chat.domain.Contact
import fr.xke.chat.service.ChatService
import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ChatController)
@Mock(Contact)
class ChatControllerTests {

    @Test
    void testIndex() {
       controller.index()
       assert view == "/chat/myChat"
       assert controller.modelAndView.model == [contacts: []]
    }

    void testReceiveMessageAndFireEvent(){
        def mockService = mockFor(ChatService)
        mockService.demand.logMessage(0..0){ /*this method should be called*/ }

        controller.chatService = mockService.createMock()

        def hasFiredEvent = false
        controller.metaClass.event = {String topic, Map data ->
            hasFiredEvent=true
            return}

        controller.params.message = "lololol..."
        controller.params.author = "Mr Trollolo"
        controller.receiveMessage()
        sleep(500l)

        mockService.verify()

        assert hasFiredEvent == true

    }
}
