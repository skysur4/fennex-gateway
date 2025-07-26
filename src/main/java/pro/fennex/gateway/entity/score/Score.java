package pro.fennex.gateway.entity.score;


import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name= "score")
public class Score implements Persistable<String> {

	@Id
	private String userId; // 사용자 아이디",

	private String nickname; // 닉네임",

	private long boss1; // 1보스 99856279200

	private long boss2; // 2보스 99856279200

	private long boss3; // 3보스 150841813600

	private long boss4; // 4보스 99856279200

	private long boss5; // 5보스 150841813600

	private String deck1; // 1덱

	private String deck2; // 2덱

	private String deck3; // 3덱

	private String deck4; // 4덱

	private String deck5; // 5덱

	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedAt;

	@Override
	public String getId() {
		return this.userId;
	}

	@Override
	public boolean isNew() {
		return this.updatedAt == null;
	}


}
