package pro.fennex.gateway.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginStatus {
	private boolean status = false;
	private String id;
	private String nickName;
	private String token;
}