package pro.fennex.gateway.common.model;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class LoginStatus {
	private static final List<String> ADMINISTRATORS = Lists.newArrayList("꿍디빵디", "띨띨한지휘관", "존자르헌호", "헨젤◆", "그레텔◆", "은화영♧", "아우우아우웅♧", "삼백억test");

	private boolean status = false;
	private String id;
	private String nickName;
	private String token;
	public boolean isAdmin(){
		return ADMINISTRATORS.contains(this.nickName);
	}
}