package myproject.ekampus.business.BusinessRules;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import myproject.ekampus.business.dtos.responses.GetAllPostsResponse;
import myproject.ekampus.entities.concretes.Post;

public class PostBusinessRules {

	public static Post existPostControl(List<Post> posts, int postId) {
		for(Post post2 : posts) {
			if(post2.getId() == postId) {
				return post2;
			}
		}
		return null;
	}
	
	public static List<GetAllPostsResponse> getAllPostBySorted(List<GetAllPostsResponse> sortedPost){
		 Collections.sort(sortedPost,new Comparator<GetAllPostsResponse>() {
			@Override
			public int compare(GetAllPostsResponse o1, GetAllPostsResponse o2) {
				LocalDateTime loadDate = o1.getLoadDate();
				LocalDateTime loadDate2 = o1.getLoadDate();
				
				return loadDate.compareTo(loadDate2);
			}
		 });
		 
		 return sortedPost;
	}
	
	public static List<GetAllPostsResponse> getAllPostByReverse(List<GetAllPostsResponse> sortedPost){
		Collections.reverse(sortedPost);
		return sortedPost;
	}
}
