package fr.xke.chat.service

import fr.xke.chat.domain.Contact
import fr.xke.chat.domain.Message
import grails.events.Listener
import groovyx.gpars.ParallelEnhancer

class ChatService {

    def grailsApplication

    def logMessage(author, text) {

        def message = new Message(message: text, authorName: author)
        message.save()

        // we broadcast the event to the UI
        def messageLine = [author: author, text:text]
        event("displayMessage", messageLine)
    }

    @Listener(namespace='browser', topic="messageInput")
    def broadcastMessage(inputMessage){
        // we retrieve the list of contacts
        def contacts = Contact.list()

        // parallelify the list before broadcasting
        ParallelEnhancer.enhanceInstance(contacts)

        // broadcast the message
        def authorName = grailsApplication.config.chat.username
        def path = grailsApplication.config.chat.message.addMessage.path
        def query = [author: authorName, message: inputMessage]

        contacts.eachParallel {
            def uri = "http://${it.ip}:${it.port}"

            withHttp(uri: uri){
                post(path:path, query:query)
            }
        }

        // add the message into log
        logMessage(authorName, inputMessage)
    }

}
