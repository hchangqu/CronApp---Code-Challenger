package com.cronappcc.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

	@RequestMapping("/")
	String home(ModelMap modal) {
		modal.addAttribute("titulo","Cadastro de Funcionários");
		return "index";
	}

	@RequestMapping("/partials/{pagina}")
	String partialHandler(@PathVariable("pagina") final String pagina) {
		return pagina;
	}

}
