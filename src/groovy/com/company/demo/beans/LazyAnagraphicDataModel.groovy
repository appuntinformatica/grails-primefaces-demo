package com.company.demo.beans


import org.apache.log4j.Logger
import com.company.demo.*
import org.primefaces.model.LazyDataModel
import org.primefaces.model.SortOrder

public class LazyAnagraphicDataModel extends LazyDataModel<Anagraphic> {
    Logger log = Logger.getLogger(LazyAnagraphicDataModel.class)
            
    AnagraphicService anagraphicService
    
    public LazyAnagraphicDataModel(AnagraphicService anagraphicService) {
        this.anagraphicService = anagraphicService
    }
    
    @Override
    public List<Anagraphic> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) { 
        log.info("first = " + first + ", pageSize = " + pageSize + ", sortField = " + sortField + ", sortOrder = " + sortOrder + ", filters = " + filters)
        def anagraphics
        if (!sortField) {
            if (filters.size() == 0)
                anagraphics = anagraphicService.list(pageSize, first)
            else
                anagraphics = anagraphicService.filter(filters, pageSize, first)
        } else {
            String order = sortOrder == SortOrder.ASCENDING ? "asc" : "desc"
            if (filters.size() == 0)
                anagraphics = anagraphicService.list(pageSize, first, sortField, order)
            else
                anagraphics = anagraphicService.filter(filters, pageSize, first, sortField, order)
        }
        this.setRowCount(anagraphics.totalCount)
        return anagraphics.list
    }
}

