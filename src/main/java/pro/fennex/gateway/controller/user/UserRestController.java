package pro.fennex.gateway.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.fennex.gateway.entity.user.UserDetail;
import pro.fennex.gateway.service.user.UserDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Tag(name = "사용자 정보 API", description = "조회,수정")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController { //extends BaseV1Controller { (현재 사용 안함)

	private final UserDetailService userDetailService;

	@Operation(summary = "상세 조회", description = "상세 조회 API")
	@GetMapping
	public Mono<ResponseEntity<UserDetail>> getUser(@Parameter(hidden = true) @AuthenticationPrincipal OidcUser oidcUser) throws Exception {

		UserDetail userDetail = new UserDetail();
		userDetail.setId(oidcUser.getSubject());
		userDetail.setGivenName(null);
		userDetail.setFamilyName(null);
		userDetail.setEmail(null);
		userDetail.setBirthDate(null);
		userDetail.setGender(null);
		userDetail.setNickname(oidcUser.getNickName());
		userDetail.setPhoneNumber(null);
		userDetail.setPhoneNumberVerified(null);

		return userDetailService.getOne(userDetail)
	            .defaultIfEmpty(userDetail)
	            .map(ResponseEntity::ok);
    }

	@Operation(summary = "생성", description = "생성 API, PK 존재 시 오류")
	@PostMapping("/save")
    public ResponseEntity<Mono<UserDetail>> save(@RequestBody UserDetail userDetail,
    		@Parameter(hidden = true) @AuthenticationPrincipal OidcUser oidcUser) throws Exception {
		userDetail.setId(oidcUser.getSubject());
		Mono<UserDetail> result = userDetailService.save(userDetail);

		return ResponseEntity.ok(result.log());
    }
}



