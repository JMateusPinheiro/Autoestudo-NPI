package br.ufc.npi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.npi.bean.Time;
import br.ufc.npi.service.JogadorService;
import br.ufc.npi.service.TimeService;

@Repository
@RequestMapping(path="/times/")
public class TimeController {
	
	@Autowired
	TimeService serviceTime;
	
	@Autowired
	JogadorService serviceJogador;

	@RequestMapping(path="/")
	public ModelAndView index(){
		ModelAndView model = new ModelAndView("gerenciarTimes");
		List<Time> times = serviceTime.getAllTimes();
		model.addObject("times", times);
		
		return model;
	}
	
	@RequestMapping(path="/{id}")
	public ModelAndView detalhesTime(@PathVariable("id") Integer id, @RequestParam(required=false) String erro){
		ModelAndView model = new ModelAndView("detalhesTime");
		Time time = serviceTime.getTimeById(id);
		
		model.addObject("time", time);
		model.addObject("jogadoresSemTime", serviceJogador.getJogadoresSemTime());
		model.addObject("erro", erro);		
		
		return model;
	}
	
	@RequestMapping(path="/salvar", method = RequestMethod.POST)
	public String save(@RequestParam String nomeTime){
		serviceTime.saveTime(nomeTime);
		
		return "redirect:/times/";
	}
	
	@RequestMapping(path="/{timeId}/adicionarjogador", method=RequestMethod.POST)
	public ModelAndView addJogadorNoTime(@PathVariable("timeId") Integer timeId, @RequestParam Integer jogadorSemTimeId){
		
		ModelAndView model = new ModelAndView("redirect:/times/" + timeId);
		
		if(serviceTime.addJogadorNoTime(timeId, jogadorSemTimeId) == false){
		    String erro = "O time já está completo.";
		    model.addObject("erro", erro);
		  }
		
		return model;
	}
	
	@RequestMapping(path="/{timeId}/removerjogador/{jogadorId}")
	public String rmJogadorNoTime(@PathVariable("timeId") Integer timeId, @PathVariable("jogadorId") Integer jogadorId){
		serviceTime.rmJogadorDoTime(timeId, jogadorId);
				
		return "redirect:/times/" + timeId;
	}
}
