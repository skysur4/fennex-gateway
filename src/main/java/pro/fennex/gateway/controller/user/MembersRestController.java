package pro.fennex.gateway.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;
import pro.fennex.gateway.common.controller.BaseV1Controller;
import pro.fennex.gateway.entity.user.Members;
import pro.fennex.gateway.service.user.MembersService;
import reactor.core.publisher.Mono;

import java.util.List;

@Tag(name = "멤버 관리 API", description = "멤버 CRUD")
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MembersRestController extends BaseV1Controller {

	private final MembersService membersService;

	@Operation(summary = "등록/수정", description = "등록/수정 API")
	@PostMapping
    public Mono<ResponseEntity<Members>> save(@RequestBody Members members) throws Exception {
		members.setUnionName(getUnionName(members.getNickname()));

		return membersService.save(members)
				.map(ResponseEntity::ok);

    }

	@Operation(summary = "목록 조회", description = "목록 조회 API")
	@GetMapping
	public Mono<ResponseEntity<List<Members>>> list() throws Exception {
		return membersService.list()
	            .map(ResponseEntity::ok);
    }

	@Operation(summary = "삭제", description = "삭제 API")
	@DeleteMapping("/{nickname}")
    public Mono<ResponseEntity<Void>> remove(@PathVariable("nickname") String nickname) throws Exception {
		return membersService.remove(nickname)
				.map(ResponseEntity::ok);
    }

	@Operation(summary = "모두삭제", description = "모두삭제 API")
	@DeleteMapping("/reset")
	public Mono<ResponseEntity<Void>> removeAll(@Parameter(hidden = true) @AuthenticationPrincipal OidcUser oidcUser) throws Exception {
		String union = getUnionName(oidcUser.getNickName());

		return membersService.removeAll(union)
				.map(ResponseEntity::ok);
	}
}



