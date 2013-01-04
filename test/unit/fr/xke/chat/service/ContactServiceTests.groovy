package fr.xke.chat.service

import fr.xke.chat.domain.Contact
import grails.test.mixin.Mock
import grails.test.mixin.TestFor


@TestFor(ContactService)
@Mock(Contact)
class ContactServiceTests {

    void testAfterInsertInvalidContact(){
        def contact  = new Contact(ip: "127.0.0.1", port: "8080", name : "joe", isValid: false)

        def hasCalledWS = false
        service.metaClass.withHttp = {Map args, Closure closure ->
           hasCalledWS=true
            Exception.metaClass.toString = {->""}
        return}

        service.afterInsert(contact)

        assert hasCalledWS == true
    }


}
