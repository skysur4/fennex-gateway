package io.cooltime.gateway.service.score;

import java.util.List;

import org.springframework.stereotype.Service;

import io.cooltime.gateway.entity.score.Boss;
import io.cooltime.gateway.repository.score.BossRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BossService {

	private final BossRepository bossRepository;


	public Mono<Boss> get(String level) {
	       return bossRepository.findById(level);
	}

	public Mono<List<Boss>> list() {
	       return bossRepository.findAll().collectList();
	}

	public Mono<Boss> save(Boss score) {
    	return bossRepository.save(score);
	}

	public Mono<Void> removeAll() {
		return bossRepository.deleteAll();
	}
}
