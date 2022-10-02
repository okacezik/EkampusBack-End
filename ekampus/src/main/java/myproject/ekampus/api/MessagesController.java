package myproject.ekampus.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import myproject.ekampus.business.abstracts.MessageService;
import myproject.ekampus.core.utilites.results.Result;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.entities.concretes.Message;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin
public class MessagesController {

	private MessageService messageService;
	
	@Autowired
	public MessagesController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@PostMapping("/sendMessage")
	public Result sendMessage(@RequestBody Message message) {
		return this.messageService.sendMessage(message);
	}
	
	@GetMapping("/getAllMessages")
	public DataResult<List<Message>> getAllMessages(){
		return this.messageService.getAllMessages();
	}
	
}
