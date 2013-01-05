package fr.xke.chat.service

import grails.events.Listener
import fr.xke.chat.domain.Contact


class ContactService {

    def grailsApplication


    @Listener(namespace = "gorm")
    def afterInsert(Contact contact) {
        if (contact.isValid){
            refreshContactList(contact)
        } else {
            requestContactValidation(contact)
        }
    }

    @Listener(namespace = "gorm")
    def afterDelete(Contact contact) {
        // we send an event to the UI to refresh the contact list
        def deletedContact = [ip: contact.ip, port: contact.port]
        event("removeContactFromList", deletedContact)
    }

    def requestContactValidation(Contact contact){
        // we ask the new contact for validation
        def query = [:]
        query["ip"] = grailsApplication.config.chat.my.ip
        query["port"] = grailsApplication.config.chat.my.port
        query["name"] = grailsApplication.config.chat.username
        def targetIp = contact.ip
        def targetPort = contact.port

        def uri = "http://${targetIp}:${targetPort}"
        def path = grailsApplication.config.chat.contact.notify.path
        try{
            withHttp(uri: uri){
                def response  = post(path:path, query:query)
                contact.name = response["contactName"]
                contact.isValid = true
            }
        } catch (e){
            log.warn("Contact validation timeout!!!")
        }
    }

    def refreshContactList(Contact contact){
        // we send an event to the UI to refresh the contact list
        def newContact = [ip: contact.ip, port: contact.port, name : contact.name]
        event("addContactToList", newContact)
    }

    def addContact(ip, port, name){
        def contact  = new Contact(ip: ip, port: port, name : name, isValid: true)
        contact.save()
    }
}
