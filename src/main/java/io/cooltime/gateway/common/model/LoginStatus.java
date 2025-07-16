package io.cooltime.gateway.common.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginStatus {
	private boolean status = false;
	private String id;
	private String nickName;
	private String token;
}