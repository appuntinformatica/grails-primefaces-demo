package com.company.demo2

import grails.transaction.Transactional
import org.springframework.validation.FieldError

@Transactional
class TestService {

    Test get(Long id) {
        Test.get(id)
    }

    def list() {
        Test.list()
    }

    def list(int max, int offset) {
        log.debug "[list] max = ${max}, offset = ${offset}"
        Test.list(max: max, offset: offset)
    }

    def list(int max, int offset, String sort, String order) {
        log.debug "[list] max = ${max}, offset = ${offset}, sort = ${sort}, order = ${order}"
        Test.list(max: max, offset: offset, sort: sort, order: order)
    }

    boolean delete(Long id) {
        log.debug "[delete] id = ${id}"
        def test = Test.get(id)
        test.delete(flush: true)
    }
	    
    List<FieldError> save(Test test) {
        log.debug "[save] id = ${test?.id}"
        def errors = null
        if (test.save(flush: true)) {
            return null
        } else {
            test.errors.allErrors
        }
    }
    
    def filter(Map filters, int max, int offset) {
        def c = Test.createCriteria()
        c.list (max: max, offset: offset) {
            and {
                filters.each { k, v ->
                    ilike(k, "%${v}%")
                }
            }
        }
    }
    
    def filter(Map filters, int max, int offset, String sort, String order) {
        def c = Test.createCriteria()
        c.list (max: max, offset: offset) {
            and {
                filters.each { k, v ->
                    ilike(k, "%${v}%")
                }
            }
            order(sort, order)
        }
    }

}