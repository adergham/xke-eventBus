package fr.xke.chat.controller

import fr.xke.chat.domain.Contact

class ChatController {

    def index() {
        def contacts = Contact.list()
        render(view: "myChat", model:[contacts:contacts])
    }


    def addMessage() {
        def message  = params.message
        def author = params.author

        render("$author : '$message'")
    }
}
