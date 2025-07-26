package pro.fennex.gateway.controller.score;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.fennex.gateway.common.controller.BaseV1Controller;
import pro.fennex.gateway.entity.score.Boss;
import pro.fennex.gateway.service.score.BossService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Tag(name = "보스 관리 API", description = "보스 CRUD")
@RestController
@RequestMapping("/boss")
@RequiredArgsConstructor
public class BossRestController extends BaseV1Controller {

	private final BossService bossService;

	@Operation(summary = "등록/수정", description = "등록/수정 API")
	@PostMapping
    public Mono<ResponseEntity<Boss>> save(@RequestBody Boss boss) throws Exception {
		return bossService.save(boss)
				.map(ResponseEntity::ok);

    }

	@Operation(summary = "목록 조회", description = "목록 조회 API")
	@GetMapping
	public Mono<ResponseEntity<List<Boss>>> list() throws Exception {
		return bossService.list()
	            .map(ResponseEntity::ok);

    }

	@Operation(summary = "상세 조회", description = "상세 조회 API")
	@GetMapping("/{level}")
	public Mono<ResponseEntity<Boss>> get(@PathVariable("level") String level) throws Exception {
		return bossService.get(level)
	            .map(ResponseEntity::ok);
    }

	@Operation(summary = "삭제", description = "삭제 API")
	@DeleteMapping
    public Mono<ResponseEntity<Void>> remove() throws Exception {
		return bossService.removeAll()
				.map(ResponseEntity::ok);
    }
}



