package pro.fennex.gateway.service.score;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.fennex.gateway.entity.score.Score;
import pro.fennex.gateway.repository.score.ScoreRepository;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreService {

	private final ScoreRepository scoreRepository;

	public Mono<Score> get(String id) {
	       return scoreRepository.findById(id);
	}

	public Mono<List<Score>> list() {
	       return scoreRepository.findAll().collectList();
	}

	public Mono<List<Score>> listByLevel(String level) {
       return scoreRepository.findAllByLevel(level).collectList();
	}

	public Mono<Score> save(Score score) {
    	return scoreRepository.save(score);
	}

	public Mono<Void> remove(String id) {
		return scoreRepository.deleteById(id);
	}

	public Mono<Void> removeAll(String union) {
		return scoreRepository.deleteAllByUnionName(union);
	}
}
