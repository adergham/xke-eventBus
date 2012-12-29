package fr.xke.chat.controller

class ChatController {

    def index() {

        render(view: "myChat")
    }


    def addMessage() {
        def message  = params.message
        def author = params.author

        render("$author : '$message'")
    }
}
