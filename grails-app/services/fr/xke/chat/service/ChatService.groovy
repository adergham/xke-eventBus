package fr.xke.chat.service

import fr.xke.chat.domain.Message
import grails.events.Listener

class ChatService {

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

        // parallelify the list before broadcasting

        // broadcast the message
    }
}
