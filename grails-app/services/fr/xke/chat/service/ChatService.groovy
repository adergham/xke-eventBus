package fr.xke.chat.service

import fr.xke.chat.domain.Message

class ChatService {

    def logMessage(author, text) {

        def message = new Message(message: text, authorName: author)
        message.save()
    }
}
