package pro.fennex.gateway.controller.session;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.fennex.gateway.common.model.LoginStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
public class SessionRestController {

	private final boolean isLocal;

	@GetMapping
	public Mono<ResponseEntity<LoginStatus>> session () {
		 return ReactiveSecurityContextHolder.getContext()
                .map(ctx -> {
                	Authentication authentication = ctx.getAuthentication();
                	if(authentication.isAuthenticated()) {
                		OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
            			log.info("oidcUser: {}", oidcUser);

                    	LoginStatus loginInfo = new LoginStatus();
            			loginInfo.setStatus(authentication.isAuthenticated());
            			loginInfo.setId(oidcUser.getSubject().trim());
            			loginInfo.setNickName(oidcUser.getNickName().trim());

            	        if(isLocal) {
            	        	loginInfo.setToken(oidcUser.getIdToken().getTokenValue());
            	    	}
            	        return ResponseEntity.ok(loginInfo);
                	}
        	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginStatus());

                }).onErrorReturn(ResponseEntity.ok(new LoginStatus()))
                ;
	}
}