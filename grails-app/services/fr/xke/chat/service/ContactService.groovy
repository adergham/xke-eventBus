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
            query["myIp"] = grailsApplication.config.chat.my.ip
            query["myPort"] = grailsApplication.config.chat.my.port
            query["targetIp"] = contact.ip
            query["targetPort"] = contact.port

            def uri = "http://${query["targetIp"]}:${query["targetPort"]}"

            withHttp(uri: uri){
                post(path:"/contact/notify", query:query)
            }
        }
    }
}
