package myproject.ekampus.business.abstracts;

import java.util.List;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.Result;
import myproject.ekampus.entities.concretes.Message;

public interface MessageService {

	Result sendMessage(Message message);
	
	DataResult<List<Message>> getAllMessages();
}
