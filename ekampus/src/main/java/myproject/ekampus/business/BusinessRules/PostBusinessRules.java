package myproject.ekampus.business.BusinessRules;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import myproject.ekampus.entities.concretes.Post;
import myproject.ekampus.entities.dtos.PostWithStudentDto;

public class PostBusinessRules {

	public static Post existPostControl(List<Post> posts, int postId) {
		for(Post post2 : posts) {
			if(post2.getId() == postId) {
				return post2;
			}
		}
		return null;
	}
	
	public static List<PostWithStudentDto> getAllPostBySorted(List<PostWithStudentDto> sortedPost){
		 Collections.sort(sortedPost,new Comparator<PostWithStudentDto>() {
			@Override
			public int compare(PostWithStudentDto o1, PostWithStudentDto o2) {
				LocalDateTime loadDate = o1.getLoadDate();
				LocalDateTime loadDate2 = o1.getLoadDate();
				
				return loadDate.compareTo(loadDate2);
			}
		 });
		 
		 return sortedPost;
	}
	
	public static List<PostWithStudentDto> getAllPostByReverse(List<PostWithStudentDto> sortedPost){
		Collections.reverse(sortedPost);
		return sortedPost;
	}
}
