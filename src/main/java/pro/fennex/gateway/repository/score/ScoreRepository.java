package pro.fennex.gateway.repository.score;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pro.fennex.gateway.entity.score.Score;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Repository
public interface ScoreRepository extends R2dbcRepository<Score, String> {
	Flux<Score> findAllByUnionName(String union);

	Mono<Void> deleteAllByUnionName(String union);

}

