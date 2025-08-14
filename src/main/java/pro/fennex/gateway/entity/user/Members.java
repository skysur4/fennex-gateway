package pro.fennex.gateway.entity.user;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.domain.Persistable;
import pro.fennex.gateway.common.controller.BaseV1Controller;
import pro.fennex.gateway.controller.GatewayController;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name= "members")
@NoArgsConstructor
public class Members implements Persistable<String> {

	public Members(String nickname){
		this.nickname = nickname;
		this.unionName = BaseV1Controller.getUnionName(nickname);
	}

	@Id
	private String nickname; // 닉네임

	private String unionName; // 유니온 이름

	private boolean teamUsed1; // 1군투입

	private boolean teamUsed2; // 2군투입

	private boolean teamUsed3; // 3군투입

	@ReadOnlyProperty
	private boolean simulatedLevel1; // 3레벨 모의전

	@ReadOnlyProperty
	private boolean simulatedLevel2; // 2레벨 모의전

	@ReadOnlyProperty
	private boolean simulatedLevel3; // 3레벨 모의전

	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedAt;

	public boolean isSimulated() {
		return this.simulatedLevel1 || this.simulatedLevel2 || this.simulatedLevel3;
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
