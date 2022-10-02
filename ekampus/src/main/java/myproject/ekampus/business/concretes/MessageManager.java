package myproject.ekampus.business.concretes;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import myproject.ekampus.business.abstracts.MessageService;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.Result;
import myproject.ekampus.core.utilites.results.SuccessDataResult;
import myproject.ekampus.core.utilites.results.SuccessResult;
import myproject.ekampus.dataAccess.abstracts.MessageDao;
import myproject.ekampus.entities.concretes.Message;

@Service
public class MessageManager implements MessageService{
	
	private MessageDao messageDao;
	
	@Autowired
	public MessageManager(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	@Override
	public Result sendMessage(Message message) {
		this.messageDao.save(message);
		return new SuccessResult("message is send");
	}

	@Override
	public DataResult<List<Message>> getAllMessages() {
		return new SuccessDataResult<List<Message>>
		(this.messageDao.findAll(), "messages are listed...");
	}

}
