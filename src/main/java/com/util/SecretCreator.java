package com.util;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entity.Message;
import com.repository.MessageRepository;

@Component
public class SecretCreator {
	
	String text = "qwkejhjgbxjchxbsegreyxmcnooapwdfilmtuvzqouewioryzxnsfhkerhgwecbmfngehjrg";
	
	private final MessageRepository messageRepo;

	public SecretCreator(MessageRepository messageRepo) {
	    this.messageRepo = messageRepo;
	}
	
	public String create() {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		
		for(int i= 0; i<30; i++) {
			int index = random.nextInt(text.length());
			sb.append(text.charAt(index));
		}
		
		Optional<Message> msg = messageRepo.findBySecret(sb.toString());
		
		if(!msg.isPresent()) {
			return sb.toString();
		}
		
		else {
			return create();
		}
		//return sb.toString();
	}

}
