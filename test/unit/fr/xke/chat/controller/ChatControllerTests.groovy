package fr.xke.chat.controller



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ChatController)
class ChatControllerTests {

    @Test
    void testIndex() {
       controller.index()
       assert view == "/chat/myChat"
    }


    void testAddMessage(){
        controller.params.message="helloworld"
        controller.params.author="Edouard Bracame"
        controller.addMessage()

        assert response.text ==   "Edouard Bracame : 'helloworld'"
    }
}
