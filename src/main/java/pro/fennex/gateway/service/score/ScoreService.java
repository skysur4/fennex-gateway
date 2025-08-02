package pro.fennex.gateway.service.score;

import java.util.List;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import pro.fennex.gateway.entity.score.Score;
import pro.fennex.gateway.repository.score.ScoreRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ScoreService {

	private final ScoreRepository scoreRepository;

	public Mono<Score> get(String userId) {
	       return scoreRepository.findById(userId);
	}

	public Mono<List<Score>> list() {
	       return scoreRepository.findAll().collectList();
	}

	public Mono<List<Score>> list(String union) {
       return scoreRepository.findAllByUnionName(union).collectList();
	}

	public Mono<Score> save(Score score) {
    	return scoreRepository.save(score);
	}

	public Mono<Void> remove(String userId) {
		return scoreRepository.deleteById(userId);
	}

	public Mono<Void> removeAll(String union) {
		return scoreRepository.deleteAllByUnionName(union);
	}
}
