package com.company.demo.beans

import org.apache.log4j.Logger
import javax.annotation.PostConstruct

import com.company.demo.Anagraphic
import com.company.demo.AnagraphicService

import grails.plugins.primefaces.GrailsService
import grails.plugins.primefaces.MessageSourceBean

import javax.faces.bean.ManagedBean
import javax.faces.bean.ManagedProperty
import javax.faces.bean.SessionScoped
import javax.faces.context.FacesContext

import org.primefaces.event.SelectEvent
import org.primefaces.model.LazyDataModel

import org.springframework.validation.FieldError

@ManagedBean(name = "anagraphicMB")
@SessionScoped
public class AnagraphicManagedBean implements Serializable {
    Logger log = Logger.getLogger(AnagraphicManagedBean.class)
        
    @PostConstruct
    public void init() {
        anagraphics = new LazyAnagraphicDataModel(anagraphicService)
    }
    
    @ManagedProperty(value = "#{message}")
    MessageSourceBean message
    
    @GrailsService(name = "anagraphicService")
    AnagraphicService anagraphicService
    
    Anagraphic anagraphic
    LazyDataModel<Anagraphic> anagraphics

    public void save() {
        log.info(anagraphic)        
        boolean updated = (anagraphic.id != null)
        List<FieldError> errors = getAnagraphicService().save(anagraphic)
        if (errors == null) {
            if (updated == true) {
                message.infoPF("com.company.demo.Anagraphic.updated.message", null)
            } else {
                message.infoPF("com.company.demo.Anagraphic.created.message", null)
            }
            reset()
        } else {
            for (FieldError error : errors) {
                message.errorMessagePF("", message.getErrorMessage(error))
            }
        }
    }
    
    public void reset() {
        anagraphic = new Anagraphic()
    }
    
    public void delete(Long id) {
        getAnagraphicService().delete(id)
        message.infoPF("com.company.demo.Anagraphic.deleted.message", null)
    }

    public void onRowSelect(SelectEvent event) {
        Long id =((Anagraphic) event.getObject()).getId()
        log.info("id = " + id)
        anagraphic = anagraphicService.get(id)
    }

}

