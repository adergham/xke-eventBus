package fr.xke.chat.controller

import fr.xke.chat.domain.Contact
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


    void testAddMessage(){
        controller.params.message="helloworld"
        controller.params.author="Edouard Bracame"
        controller.addMessage()

        assert response.text ==   "Edouard Bracame : 'helloworld'"
    }
}
