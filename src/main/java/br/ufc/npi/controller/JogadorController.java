package br.ufc.npi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.npi.service.JogadorService;

@Controller
@RequestMapping("/jogadores/")
public class JogadorController {
	
	@Autowired
	JogadorService serv;
	
	@RequestMapping("/")
	public ModelAndView index(){
		ModelAndView model = new ModelAndView("gerenciarJogadores");
		model.addObject("jogadores", serv.getAllJogadores());
		
		return model;
	}
	
	@RequestMapping(path="/salvar", method=RequestMethod.POST)
	public String salvar(@RequestParam String Nome, @RequestParam int Idade){
		serv.saveJogador(Nome, Idade);
		
		return "redirect:/jogadores/";
	}
}
