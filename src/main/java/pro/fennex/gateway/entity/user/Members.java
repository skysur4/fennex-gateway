package pro.fennex.gateway.entity.user;


import java.time.LocalDateTime;

import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name= "members")
public class Members implements Persistable<String> {

	@Id
	private String nickname; // 닉네임

	private String unionName; // 유니온 이름

	@Formula("(select count(*) FROM score s where s.nickname = nickname and s.level = '1')")
	private boolean simulatedBoss1; // 3레벨 모의전

	@Formula("(select count(*) FROM score s where s.nickname = nickname and s.level = '2')")
	private boolean simulatedBoss2; // 2레벨 모의전

	@Formula("(select count(*) FROM score s where s.nickname = nickname and s.level = '3')")
	private boolean simulatedBoss3; // 3레벨 모의전

	private boolean teamUsed1; // 1군투입

	private boolean teamUsed2; // 2군투입

	private boolean teamUsed3; // 3군투입

	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedAt;

	public boolean isSimulated() {
		return this.simulatedBoss1 && this.simulatedBoss2 && this.simulatedBoss3;
	}

	@Override
	public String getId() {
		return this.nickname;
	}

	@Override
	public boolean isNew() {
		return this.updatedAt == null;
	}

}
