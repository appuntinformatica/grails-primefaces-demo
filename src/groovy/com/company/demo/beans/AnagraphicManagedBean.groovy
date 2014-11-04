package com.company.demo.beans

import com.company.demo.*
import grails.plugins.primefaces.GrailsService
import grails.plugins.primefaces.MessageSourceBean
import javax.faces.application.FacesMessage
import javax.faces.bean.ManagedBean
import javax.faces.bean.ManagedProperty
import javax.faces.context.FacesContext
import org.springframework.validation.FieldError

@ManagedBean(name = "anagraphicMB")
public class AnagraphicManagedBean {

    @ManagedProperty(value = "#{message}")
    MessageSourceBean message
    
    @GrailsService(name = "anagraphicService")
    AnagraphicService anagraphicService
    
    Anagraphic anagraphic
    Anagraphic getAnagraphic() {
        if (!anagraphic)
            anagraphic = new Anagraphic()
        anagraphic 
    }
    
    List<Anagraphic> anagraphics
    List<Anagraphic> getAnagraphics() {
        anagraphics = anagraphicService.list()
        return anagraphics
    }
      
    public void add() {
        anagraphic.id = null
        List<FieldError> errors = getAnagraphicService().save(anagraphic)
        if (errors == null) {
            addInfoMessage(message.i18n("com.company.demo.Anagraphic.created.message"))
            reset()
        } else {
            for (FieldError error : errors) {
                addErrorMessage(message.getErrorMessage(error))
            }
        }
    }
    
    public void reset() {
	anagraphic = null
    }
    
    public void delete(Long id) {
        getAnagraphicService().delete(id)
    }
	
    def addInfoMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", summary)
        FacesContext.getCurrentInstance().addMessage(null, message)
    }
    def addWarningMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", summary)
        FacesContext.getCurrentInstance().addMessage(null, message)
    }
    def addErrorMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", summary)
        FacesContext.getCurrentInstance().addMessage(null, message)
    }
    
}

