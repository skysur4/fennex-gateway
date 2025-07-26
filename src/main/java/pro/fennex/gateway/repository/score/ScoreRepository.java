package pro.fennex.gateway.repository.score;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import pro.fennex.gateway.entity.score.Score;
import reactor.core.publisher.Flux;


@Repository
public interface ScoreRepository extends R2dbcRepository<Score, String> {
	Flux<Score> findAllByNicknameContaining(String postfix);

	Flux<Score> findAllByNicknameNotContaining(String postfix1, String postfix2);
}

