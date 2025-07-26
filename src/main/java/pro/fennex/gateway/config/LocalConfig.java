package pro.fennex.gateway.config;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.google.common.collect.Lists;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

/**
 * 패스워드 암보호화 테스트를 위한 로컬 전용 설정
 * TODO 템플릿 복사 후 원하는 패스워트 암호화 완료 시 필히 삭제!!!
 */
@Slf4j
@Configuration
@Profile("local")
@Deprecated
public class LocalConfig {
	@Value("${jasypt.encryptor.password}")
	public String password;

	@Autowired
	public StringEncryptor jasyptStringEncryptor;

    @PostConstruct
	private void JasyptTest() {
    	// 일반텍스트 문자 암호화
    	List<String> plainText = Lists.newArrayList(

    		);

    	Decoder decoder = Base64.getDecoder();
    	// BASE64 텍스트 문자 암호화
    	List<String> base64EncodedText = Lists.newArrayList(

    		);

		log.info("###### jasypt test start ######");
		log.warn("!!비번 적용 후 반드시 삭제할 것!!");
		log.warn("JASYPT PASSWORD: {}", password);

		for(String target : base64EncodedText) {
			String decoded = new String(decoder.decode(target)).trim();
			log.info("base64Encoded: {} -> {}", decoded, jasyptStringEncryptor.encrypt(decoded));
		}

		for(String newtarget : plainText) {
			log.info("plain: {} -> {}", newtarget, jasyptStringEncryptor.encrypt(newtarget));
		}

		log.info("###### jasypt test end ########");
	}
}
