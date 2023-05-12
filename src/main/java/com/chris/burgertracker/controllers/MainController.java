package com.chris.burgertracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chris.burgertracker.models.Burger;
import com.chris.burgertracker.services.BurgerService;

import jakarta.validation.Valid;

@Controller
public class MainController {
	
	@Autowired
	BurgerService burgerService;
	
	@RequestMapping("/")
	public String home(@ModelAttribute("burger") Burger burger, Model model) {
		
		List<Burger> burgers = burgerService.allBurgers();
		model.addAttribute("burgers", burgers);
		return "index.jsp";
	}
	
	@PostMapping("/addBurger")
	public String addBurger(@Valid @ModelAttribute("burger") Burger burger, BindingResult result, Model model) {
		if(result.hasErrors()) {
			List<Burger> burgers = burgerService.allBurgers();
			model.addAttribute("burgers", burgers);
			return "index.jsp";
		} else {
			burgerService.addBurger(burger);
			return "redirect:/";
		}
	}

}
