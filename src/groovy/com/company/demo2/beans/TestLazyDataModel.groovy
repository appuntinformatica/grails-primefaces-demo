package com.company.demo2.beans

import org.apache.log4j.Logger

import com.company.demo2.Test
import com.company.demo2.TestService

import org.primefaces.model.LazyDataModel
import org.primefaces.model.SortOrder

public class TestLazyDataModel extends LazyDataModel<Test> {
    Logger log = Logger.getLogger(TestLazyDataModel.class)
            
    TestService testService
    
    public TestLazyDataModel(TestService testService) {
        this.testService = testService
    }
    
    @Override
    public List<Test> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) { 
        log.debug("first = " + first + ", pageSize = " + pageSize + ", sortField = " + sortField + ", sortOrder = " + sortOrder + ", filters = " + filters)
        def tests
        if (!sortField) {
            if (filters.size() == 0)
                tests = testService.list(pageSize, first)
            else
                tests = testService.filter(filters, pageSize, first)
        } else {
            String order = sortOrder == SortOrder.ASCENDING ? "asc" : "desc"
            if (filters.size() == 0)
                tests = testService.list(pageSize, first, sortField, order)
            else
                tests = testService.filter(filters, pageSize, first, sortField, order)
        }
        this.setRowCount(tests.totalCount)
        return tests.list
    }
}

