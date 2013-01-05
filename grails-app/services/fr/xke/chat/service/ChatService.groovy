package fr.xke.chat.service

import fr.xke.chat.domain.Contact
import fr.xke.chat.domain.Message
import grails.events.Listener
import groovyx.gpars.ParallelEnhancer

class ChatService {

    def grailsApplication

    @Listener(namespace='*', topic="message.*")
    def logMessage(msg) {
        def text = msg.text
        def author = msg.author? msg.author : grailsApplication.config.chat.username

        log.info("log message to database : '${author}: ${text}'" )
        def message = new Message(message: text, authorName: author)
        message.save()
    }

    @Listener(namespace='browser', topic="message.input")
    def inputMessage(msg) {
        def text = msg.text
        def authorName = grailsApplication.config.chat.username

        broadcastMessage(authorName, text)

        def messageLine = [author: authorName, text:text]
        event("display.message", messageLine)
    }


    def broadcastMessage(authorName, inputMessage){
        log.info("broadcast message : ${inputMessage}")
        // we retrieve the list of contacts
        def contacts = Contact.list()

        // parallelify the list before broadcasting
        ParallelEnhancer.enhanceInstance(contacts)

        // broadcast the message
        def path = grailsApplication.config.chat.message.addMessage.path
        def query = [author: authorName, message: inputMessage]

        contacts.eachParallel {
            def uri = "http://${it.ip}:${it.port}"

            withHttp(uri: uri){
                post(path:path, query:query)
            }
        }
    }

}
