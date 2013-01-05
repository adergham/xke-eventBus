package fr.xke.chat.service

import fr.xke.chat.domain.Contact


class ContactServiceIntegrationTests extends GroovyTestCase {

    def contactService

    void testAfterInsertValidContactListener(){

        def hasFiredDisplayEvent = false
        contactService.on("addContactToList"){
            hasFiredDisplayEvent = true

        }

        def contact  = new Contact(ip: "127.0.0.1", port: "8080", name : "jack", isValid: true)
        contact.save()
        sleep(500l)

        assert hasFiredDisplayEvent == true
    }
}
