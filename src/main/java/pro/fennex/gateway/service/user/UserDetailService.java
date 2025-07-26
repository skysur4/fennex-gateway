package pro.fennex.gateway.service.user;

import org.springframework.stereotype.Service;

import pro.fennex.gateway.entity.user.UserDetail;
import pro.fennex.gateway.repository.user.UserDetailRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserDetailService {

	//private final UserDetailMapper userDetailMapper;
	private final UserDetailRepository userDetailRepository;


	 public Mono<UserDetail> getOne(UserDetail userDetail) {
	        return userDetailRepository.findById(userDetail.getId());
	 }


	public Mono<UserDetail> save(UserDetail userDetail) {
		return userDetailRepository.existsById(userDetail.getId()).flatMap(exist -> {
			if(exist) {
				return userDetailRepository.update(userDetail);
			} else {
				return userDetailRepository.insert(userDetail);
			}
		});
	}
}
