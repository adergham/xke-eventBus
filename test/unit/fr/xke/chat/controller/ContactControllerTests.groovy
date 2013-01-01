package fr.xke.chat.controller

import fr.xke.chat.service.ContactService
import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ContactController)
class ContactControllerTests {

    void testNotifyContact() {
        controller.params.ip="128.0.0.1"
        controller.params.port="8080"
        controller.params.name="Paul Posichon"

        def mockService = mockFor(ContactService)
        mockService.demand.addContact(1..1){String ip, String port, String name -> }

        controller.contactService = mockService.createMock()

        controller.notifyContact()

        mockService.verify()

        assert "{\"contactName\":\"Jean-Raoul Ducable\"}" == controller.response.text
    }
}
