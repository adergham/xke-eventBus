package fr.xke.chat.service

import fr.xke.chat.domain.Contact

/**
 * Created with IntelliJ IDEA.
 * User: adergham
 * Date: 03/01/13
 * Time: 16:44
 * To change this template use File | Settings | File Templates.
 */
class ContactServiceIntegrationTests extends GroovyTestCase {

    def contactService

    void testAfterInsertContactListener(){

        def hasCalledWS = false
        contactService.metaClass.withHttp = {Map args, Closure closure ->
            hasCalledWS=true
            Exception.metaClass.toString = {->""}
        return}

        def contact  = new Contact(ip: "127.0.0.1", port: "8080", name : "jack", isValid: false)
        contact.save()

        assert hasCalledWS == true
    }
}
