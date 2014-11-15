import com.company.demo.*

class BootStrap {

    def grailsApplication
    
    def init = { servletContext ->
        log.info "BootStrap demo"       

        def t1 = System.currentTimeMillis()
        def input
        try {
            input = servletContext.getResourceAsStream("/WEB-INF/resources/data.csv")
            input.eachLine { line ->
                def a = new Anagraphic(firstName: line.split(";")[0], surname: line.split(";")[1])
                if (!a.save(flush: true)) {
                    log.warn a.errors
                }
            }
        } finally {
            if (input)
                input.close()
        }
        def t2 = System.currentTimeMillis()
        def delay = t2 - t1
        log.info "delay = ${delay} [mills]"
    }
    def destroy = {
    }
}
