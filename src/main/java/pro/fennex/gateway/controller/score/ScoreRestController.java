package pro.fennex.gateway.controller.score;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.fennex.gateway.common.controller.BaseV1Controller;
import pro.fennex.gateway.entity.score.Score;
import pro.fennex.gateway.entity.user.Members;
import pro.fennex.gateway.service.score.ScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import pro.fennex.gateway.service.user.MembersService;
import reactor.core.publisher.Mono;

@Tag(name = "점수 관리 API", description = "점수 CRUD")
@RestController
@RequestMapping("/score")
@RequiredArgsConstructor
public class ScoreRestController extends BaseV1Controller {

	private final ScoreService scoreService;

	private final MembersService membersService;

	@Operation(summary = "등록/수정", description = "등록/수정 API")
	@PostMapping
    public Mono<ResponseEntity<Score>> save(@Parameter(hidden = true) @AuthenticationPrincipal OidcUser oidcUser, @RequestBody Score score) throws Exception {
    	score.setUserId(oidcUser.getSubject().trim());
		score.setNickname(oidcUser.getNickName().trim());
		score.setUnionName(getUnionName(oidcUser.getNickName()));

		return scoreService.save(score)
				.map(ResponseEntity::ok);
    }

	@Operation(summary = "목록 조회", description = "목록 조회 API")
	@GetMapping("/{level}")
	public Mono<ResponseEntity<List<Score>>> list(@PathVariable("level") String level) throws Exception {
		return scoreService.listByLevel(level)
	            .map(ResponseEntity::ok);
    }

	@Operation(summary = "상세 조회", description = "상세 조회 API")
	@GetMapping("/{level}/{index}")
	public Mono<ResponseEntity<Score>> get(
			@Parameter(hidden = true) @AuthenticationPrincipal OidcUser oidcUser
			, @PathVariable("level") String level
			, @PathVariable("index") String index) throws Exception {
		return scoreService.get(getId(oidcUser.getSubject(), level, index))
	            .map(ResponseEntity::ok);
    }

	@Operation(summary = "삭제", description = "삭제 API")
	@DeleteMapping("/{level}/{index}")
    public Mono<ResponseEntity<Void>> remove(
			@Parameter(hidden = true) @AuthenticationPrincipal OidcUser oidcUser
			, @PathVariable("level") String level
			, @PathVariable("index") String index) throws Exception {
		return scoreService.remove(getId(oidcUser.getSubject(), level, index))
				.map(ResponseEntity::ok);
    }

	@Operation(summary = "모두삭제", description = "모두삭제 API")
	@DeleteMapping("/reset")
	public Mono<ResponseEntity<Void>> removeAll(@Parameter(hidden = true) @AuthenticationPrincipal OidcUser oidcUser) throws Exception {
		String union = getUnionName(oidcUser.getNickName());

		return scoreService.removeAll(union)
				.map(ResponseEntity::ok);
	}

	private String getId(String userId, String level, String index){
		return userId + '-' + level + '-' + index;
	}
}



