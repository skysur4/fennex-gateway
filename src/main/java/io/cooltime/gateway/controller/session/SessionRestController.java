package io.cooltime.gateway.controller.session;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.cooltime.gateway.common.model.LoginStatus;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
public class SessionRestController {

	private final boolean isLocal;

	@GetMapping
	public Mono<LoginStatus> session () {
		 return ReactiveSecurityContextHolder.getContext()
                .map(ctx -> {
                	Authentication authentication = ctx.getAuthentication();
                	LoginStatus loginInfo = new LoginStatus();
        			loginInfo.setStatus(authentication.isAuthenticated());
        			OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
        			loginInfo.setId(oidcUser.getPreferredUsername().trim());
        			loginInfo.setNickName(oidcUser.getNickName().trim());

        	        if(isLocal) {
        	        	loginInfo.setToken(oidcUser.getIdToken().getTokenValue());
        	    	}

        	        return loginInfo;
                });
	}
}