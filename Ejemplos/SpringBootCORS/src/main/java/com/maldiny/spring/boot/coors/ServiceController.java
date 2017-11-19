package com.maldiny.spring.boot.coors;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @CrossOrigin // Descomentar para habilitar el CORS a nivel de todos los servicios del controlador.
public class ServiceController {
	
	@CrossOrigin
	@RequestMapping(value="/cross_origin_enabled", method = RequestMethod.GET)
    public String enabled(ModelMap model) {
        return "enabled";
    }
	
	@RequestMapping(value="/cross_origin_disabled", method = RequestMethod.GET)
    public String disabled(ModelMap model) {
        return "disabled";
    }

}
