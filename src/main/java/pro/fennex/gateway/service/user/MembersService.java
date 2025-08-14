package pro.fennex.gateway.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.fennex.gateway.entity.user.Members;
import pro.fennex.gateway.repository.user.MembersRepository;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MembersService {

	private final MembersRepository membersRepository;

	public Mono<List<Members>> list() {
	       return membersRepository.findAllMembers().collectList();
	}

	public Mono<Void> saveAuto() {
		return membersRepository.saveAuto();
	}

	public Mono<Members> save(Members member) {
    	return membersRepository.save(member);
	}

	public Mono<Void> remove(String nickname) {
		return membersRepository.deleteByNickname(nickname);
	}

	public Mono<Void> removeAll(String union) {
		return membersRepository.deleteAllByUnionName(union);
	}
}
