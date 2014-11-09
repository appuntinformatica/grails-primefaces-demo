import com.company.demo.*

class BootStrap {

    def init = { servletContext ->
        log.info "BootStrap demo"

        (1..100).each { index ->
            def a = new Anagraphic(firstName: "First Name ${index}", surname: "Surname ${index}")
            if (!a.save(flush: true)) {
                log.warn a.errors
            }
        }
        
    }
    def destroy = {
    }
}
