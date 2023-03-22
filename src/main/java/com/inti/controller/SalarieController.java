package com.inti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.inti.model.Salarie;
import com.inti.repository.ISalarieRepository;

// Classe controller
//commentaire
@Controller
public class SalarieController {
	
	@Autowired
	ISalarieRepository isr;
	
	@GetMapping("inscription")
	public String inscription()
	{
		return "inscription";
	}
	
	@PostMapping("inscription")
	public String inscription(@ModelAttribute("salarie") Salarie s)
	{
		isr.save(s);
		
		return "redirect:/inscription";
	}
	
	@GetMapping("listeSalarie")
	public String listeSalarie(Model m)
	{
		m.addAttribute("listeS", isr.findAll());
		
		return "listeSalarie";
	}
	
	@GetMapping("deleteSalarie/{id}")
	public String deleteSalarie(@PathVariable("id") int id)
	{
		isr.deleteById(id);
		
		return "redirect:/listeSalarie";
	}

}
