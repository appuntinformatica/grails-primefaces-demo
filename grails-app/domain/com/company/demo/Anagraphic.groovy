package com.company.demo

class Anagraphic {

    String firstName
    String surname

    static constraints = {
        firstName nullable: false, blank: false
        surname nullable: false, blank: false
    }

}
