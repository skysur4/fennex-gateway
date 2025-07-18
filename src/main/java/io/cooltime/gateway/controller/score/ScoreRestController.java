package io.cooltime.gateway.controller.score;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.cooltime.gateway.common.controller.BaseV1Controller;
import io.cooltime.gateway.entity.score.Score;
import io.cooltime.gateway.service.score.ScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Tag(name = "점수 관리 API", description = "점수 CRUD")
@RestController
@RequestMapping("/score")
@RequiredArgsConstructor
public class ScoreRestController extends BaseV1Controller {

	private final ScoreService scoreService;

	@Operation(summary = "등록/수정", description = "등록/수정 API")
	@PostMapping
    public Mono<ResponseEntity<Score>> save(@RequestBody Score score, @Parameter(hidden = true) @AuthenticationPrincipal OidcUser oidcUser) throws Exception {
    	score.setUserId(oidcUser.getSubject().trim());
		score.setNickname(oidcUser.getNickName().trim());

//        return scoreService.insert(score)
//        		.onErrorResume(e -> {
//        			return scoreService.update(score);
//        		})
//        		.map(ResponseEntity::ok);

		return scoreService.save(score)
				.map(ResponseEntity::ok);

    }

	@Operation(summary = "목록 조회", description = "목록 조회 API")
	@GetMapping
	public Mono<ResponseEntity<List<Score>>> list(@Parameter(hidden = true) @AuthenticationPrincipal OidcUser oidcUser) throws Exception {
		return scoreService.list()
	            .map(ResponseEntity::ok);
    }

	@Operation(summary = "상세 조회", description = "상세 조회 API")
	@GetMapping("/{userId}")
	public Mono<ResponseEntity<Score>> get(@PathVariable("userId") String userId, @Parameter(hidden = true) @AuthenticationPrincipal OidcUser oidcUser) throws Exception {
		return scoreService.get(oidcUser.getSubject())
	            .map(ResponseEntity::ok);
    }

	@Operation(summary = "삭제", description = "삭제 API")
	@DeleteMapping
    public Mono<ResponseEntity<Void>> remove(@Parameter(hidden = true) @AuthenticationPrincipal OidcUser oidcUser) throws Exception {
		return scoreService.remove(oidcUser.getSubject())
				.map(ResponseEntity::ok);
    }
}



