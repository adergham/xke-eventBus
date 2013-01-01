package fr.xke.chat.domain

class Contact {

    String ip
    String port
    String name

    static constraints = {
        ip unique: "port"
        name display: false
    }
}
