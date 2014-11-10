package com.company.demo.beans

import javax.annotation.PostConstruct;
import org.apache.log4j.Logger
import org.primefaces.model.LazyDataModel
import org.primefaces.model.SortOrder
import com.company.demo.*
import org.primefaces.event.SelectEvent;
import grails.plugins.primefaces.GrailsService
import grails.plugins.primefaces.MessageSourceBean
import javax.faces.application.FacesMessage
import javax.faces.bean.ManagedBean
import javax.faces.bean.ManagedProperty
import javax.faces.bean.ViewScoped
import javax.faces.bean.SessionScoped
import javax.faces.context.FacesContext
import org.springframework.validation.FieldError

@ManagedBean(name = "anagraphicMB")
@ViewScoped
public class AnagraphicManagedBean implements Serializable {
    Logger log = Logger.getLogger(AnagraphicManagedBean.class)
        
    @PostConstruct
    public void init() {
        anagraphics = new LazyAnagraphicDataModel(anagraphicService)
        anagraphic = new Anagraphic()
    }
    
    @ManagedProperty(value = "#{message}")
    MessageSourceBean message
    
    @GrailsService(name = "anagraphicService")
    AnagraphicService anagraphicService
    
    Anagraphic anagraphic
    LazyDataModel<Anagraphic> anagraphics

    public void save() {
        //anagraphic.id = null
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
    
      public void onRowSelect(SelectEvent event) {
        Long id =((Anagraphic) event.getObject()).getId();
        log.info("" + id);
        anagraphic = anagraphicService.get(id)
    }
}

