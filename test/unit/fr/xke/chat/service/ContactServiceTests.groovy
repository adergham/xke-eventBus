package fr.xke.chat.service

import fr.xke.chat.domain.Contact
import grails.test.mixin.Mock
import grails.test.mixin.TestFor

/**
 * Created with IntelliJ IDEA.
 * User: adergham
 * Date: 03/01/13
 * Time: 15:47
 * To change this template use File | Settings | File Templates.
 */
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

    void testAfterInsertValidContact(){
        def contact  = new Contact(ip: "127.0.0.1", port: "8080", name : "joe", isValid: true)

        def hasCalledWS = false
        service.metaClass.withHttp = {Map args, Closure closure ->
            hasCalledWS=true
            Exception.metaClass.toString = {->""}
            return}

        service.afterInsert(contact)

        assert hasCalledWS == false
    }
}
