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
@Table(name= "boss")
public class Boss implements Persistable<String> {

	@Id
	private String level; // 레벨",

	private String name1; // 이름1

	private String name2; // 이름2

	private String name3; // 이름3

	private String name4; // 이름4

	private String name5; // 이름5

	private long hp1; // 체력1 99856279200

	private long hp2; // 체력2 99856279200

	private long hp3; // 체력3 150841813600

	private long hp4; // 체력4 99856279200

	private long hp5; // 체력5 150841813600



	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedAt;

	@Override
	public String getId() {
		return this.level;
	}

	@Override
	public boolean isNew() {
		return this.updatedAt == null;
	}


}
