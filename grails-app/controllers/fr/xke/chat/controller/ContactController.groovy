package fr.xke.chat.controller

import fr.xke.chat.domain.Contact
import grails.converters.JSON



class ContactController {

    def scaffold = Contact
    def contactService
    def grailsApplication


    def notifyContact = {
        // we had the new contact in database
        contactService.addContact(params.ip, params.port, params.name)

        // we answer the request with the userName
        def response = ["contactName" : grailsApplication.config.chat.username]
        render response as JSON
    }
}
