package pro.fennex.gateway.service.score;

import java.util.List;

import org.springframework.stereotype.Service;

import pro.fennex.gateway.entity.score.Boss;
import pro.fennex.gateway.repository.score.BossRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BossService {

	private final BossRepository bossRepository;


	public Mono<Boss> get(String level) {
	       return bossRepository.findById(level);
	}

	public Mono<Boss> save(Boss score) {
    	return bossRepository.save(score);
	}

	public Mono<Void> removeAtLevel(String level) {
		return bossRepository.deleteById(level);
	}

	public Mono<Void> removeAll() {
		return bossRepository.deleteAll();
	}
}
