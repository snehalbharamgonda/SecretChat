package com.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.entity.Message;
import com.repository.MessageRepository;
import com.util.SecretCreator;

@Controller
public class MessageController {
	
	@Autowired
	private SecretCreator secretCreator;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@PostMapping("/encrypt")
	public ModelAndView encryptMessage(@RequestParam String actual, @RequestParam String password) {
		Message m = new Message();
		m.setActual(actual);
		m.setPassword(password);
		
		String secret = secretCreator.create();
		m.setSecret(secret);
		
		messageRepository.save(m);
		ModelAndView mv = new ModelAndView("response.html");
		
		mv.addObject("title", "Your encrypted message is:");
		mv.addObject("msg", secret);
		
		return mv;  
	}
	
	@PostMapping("/decrypt")
	public ModelAndView decryptMessage(@RequestParam String secret, @RequestParam String password) {
		ModelAndView mv = new ModelAndView("response.html");
		
		Optional<Message> msg = messageRepository.findBySecretAndPassword(secret, password);
		
		if(msg.isPresent()) {
			mv.addObject("title", "Your Decoded Message is:");
			mv.addObject("msg" , msg.get().getActual());
		}
		
		else {
			mv.addObject("msg", "Something went wrong...");
			mv.addObject("title", "Either Wrong secret message or wrong password");
			
		}
		
		return mv;
	}
}
