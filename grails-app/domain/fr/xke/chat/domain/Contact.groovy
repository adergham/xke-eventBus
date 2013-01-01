package fr.xke.chat.domain

class Contact {

    String ip
    String name

    static constraints = {
        ip unique: true
        name display: false
    }
}
