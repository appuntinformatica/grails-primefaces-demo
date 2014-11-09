package com.company.demo

import grails.transaction.Transactional
import org.springframework.validation.FieldError

@Transactional
class AnagraphicService {

    List<Anagraphic> list() {
        Anagraphic.list()
    }
    List<Anagraphic> list(int max, int offset) {
        log.info "[list] max = ${max}, offset = ${offset}"
        Anagraphic.list(max: max, offset: offset)
    }
    List<Anagraphic> list(int max, int offset, String sort, String order) {
        log.info "[list] max = ${max}, offset = ${offset}, sort = ${sort}, order = ${order}"
        Anagraphic.list(max: max, offset: offset, sort: sort, order: order)
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
