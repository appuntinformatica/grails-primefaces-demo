package com.company.demo2.beans

import org.apache.log4j.Logger
import javax.annotation.PostConstruct

import com.company.demo2.Test
import com.company.demo2.TestService

import grails.plugins.primefaces.GrailsService
import grails.plugins.primefaces.MessageSourceBean

import javax.faces.bean.ManagedBean
import javax.faces.bean.ManagedProperty
import javax.faces.bean.SessionScoped
import javax.faces.context.FacesContext

import org.primefaces.event.SelectEvent
import org.primefaces.model.LazyDataModel

import org.springframework.validation.FieldError

@ManagedBean(name = "testMB")
@SessionScoped
class TestManagedBean implements Serializable {
    Logger log = Logger.getLogger(TestManagedBean.class)
        
    @PostConstruct
    public void init() {
        tests = new TestLazyDataModel(testService)
    }
    
    @ManagedProperty(value = "#{message}")
    MessageSourceBean message
    
    @GrailsService(name = "testService")
    TestService testService
    
    Test test
    LazyDataModel<Test> tests

    public void save() {
        log.debug(test)        
        boolean updated = (test.id != null)
        List<FieldError> errors = getTestService().save(test)
        if (errors == null) {
            if (updated == true) {
                message.infoPF("pf.default.updated.message", null, "Test")
            } else {
                message.infoPF("pf.default.created.message", null, "Test")
            }
            reset()
        } else {
            for (FieldError error : errors) {
                message.errorMessagePF("", message.getErrorMessage(error))
            }
        }
    }
    
    public void reset() {
        test = new Test()
    }
    
    public void delete(Long id) {
        getTestService().delete(id)
        message.infoPF("pf.default.deleted.message", null, "Test")
    }

    public void onRowSelect(SelectEvent event) {
        Long id = ((Test) event.getObject()).getId()
        log.info("id = " + id)
        test = getTestService().get(id)
    }

}

