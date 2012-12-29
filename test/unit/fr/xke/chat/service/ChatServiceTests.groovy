package fr.xke.chat.service

import fr.xke.chat.domain.Message
import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ChatService)
@Mock(Message)
class ChatServiceTests {

    @Test
    void testLogMessage() {
        service.logMessage("Jean-Raoul Ducable", "kawazaki forever!!")

        def messages = Message.findAllByAuthorName("Jean-Raoul Ducable")
        assert messages.size() == 1
        assert messages[0].message == "kawazaki forever!!"
    }
}
