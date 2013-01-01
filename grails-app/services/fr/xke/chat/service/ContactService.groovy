package fr.xke.chat.service

import grails.events.Listener
import fr.xke.chat.domain.Contact


class ContactService {

    def grailsApplication


    @Listener(namespace = "gorm")
    def afterInsert(Contact contact) {
        if (!contact.isValid){
            // we ask the new contact for validation
            def query = [:]
            query["ip"] = grailsApplication.config.chat.my.ip
            query["port"] = grailsApplication.config.chat.my.port
            query["name"] = grailsApplication.config.chat.username
            def targetIp = contact.ip
            def targetPort = contact.port

            def uri = "http://${targetIp}:${targetPort}"
            def path = grailsApplication.config.chat.contact.notify.path
            withHttp(uri: uri){
                def response  = post(path:path, query:query)
                contact.name = response["contactName"]
                contact.isValid = true
            }
        }
    }

    def addContact(ip, port, name){
        def contact  = new Contact(ip: ip, port: port, name : name, isValid: true)
        contact.save()
    }
}
