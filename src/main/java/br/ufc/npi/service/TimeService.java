package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.bean.Jogador;
import br.ufc.npi.bean.Time;
import br.ufc.npi.repositorio.JogadorRepositorio;
import br.ufc.npi.repositorio.TimeRepositorio;

@Service
public class TimeService {
	
	@Autowired
	TimeRepositorio repoTime;
	
	@Autowired
	JogadorRepositorio repoJogador;
	
	public Time saveTime(String nome){
		Time time = new Time();
		time.setNome(nome);
		repoTime.save(time);
		
		return time;
	}
	
	public List<Time> getAllTimes(){
		return repoTime.findAll();
	}
	
	public Time getTimeById(int id){
		
		return repoTime.findOne(id);
	}
	
	public boolean addJogadorNoTime(int timeId, int jogadorId){
		
		Time time = repoTime.getOne(timeId);
		if(time.getJogadores().size()==7){
		    return false;
		}else {

		Jogador jogador = repoJogador.getOne(jogadorId);
		
		time.getJogadores().add(jogador);
		jogador.setTime(time);
		
		repoTime.save(time);
		repoJogador.save(jogador);
		return true;
		}
	}
	
	public void rmJogadorDoTime(int timeId, int jogadorId){
		
		Time time = repoTime.getOne(timeId);
		Jogador jogador = repoJogador.getOne(jogadorId);
		
		time.getJogadores().remove(jogador);
		jogador.setTime(null);
		
		repoTime.save(time);
		repoJogador.save(jogador);
		
	}
	
}
