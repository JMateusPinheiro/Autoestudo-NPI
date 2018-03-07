package br.ufc.npi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PrincipalController {

	@RequestMapping("/")
	public String index(){
		return "index";
	}
}
