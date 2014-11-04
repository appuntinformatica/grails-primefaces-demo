class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

		"/"(uri:"/faces/pages/list.xhtml")
        //"/"(view:"/index")
        "500"(view:'/error')
	}
}
