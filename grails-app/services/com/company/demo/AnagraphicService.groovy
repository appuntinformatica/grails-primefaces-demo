package com.company.demo

import grails.transaction.Transactional
import org.springframework.validation.FieldError

@Transactional
class AnagraphicService {

    List<Anagraphic> list() {
        Anagraphic.list()
    }

    def delete(Long id) {
        def anagraphic = Anagraphic.get(id)
        anagraphic.delete(flush: true)
    }
    
    List<FieldError> save(Anagraphic anagraphic) {
        def errors = null
        if (anagraphic.save(flush: true)) {
            return null
        } else {
            anagraphic.errors.allErrors
        }
    }
}
