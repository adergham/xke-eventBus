package fr.xke.chat.domain

class Contact {

    String ip
    String port
    String name
    boolean isValid

    static constraints = {
        ip unique: "port"
        name display: false
    }
}
