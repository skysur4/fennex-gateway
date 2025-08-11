package pro.fennex.gateway.repository.score;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pro.fennex.gateway.entity.score.Score;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface ScoreRepository extends R2dbcRepository<Score, String> {
	Mono<Score> findByIdAndLevelAndIndex(String userId, String level, String index);

	Flux<Score> findAllByLevel(String level);

	Mono<Void> deleteAllByUnionName(String union);

	Mono<Void> deleteByIdAndLevelAndIndex(String userId, String level, String index);

}

