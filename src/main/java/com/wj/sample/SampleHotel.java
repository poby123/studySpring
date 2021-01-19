package com.wj.sample;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Component
@ToString
@AllArgsConstructor
@Getter
public class SampleHotel {
	@NonNull
	private Chef chef;
	
	/* 생성자를 통해 의존성을 주입받는 경우 Autowired를 명시하지 않아도 된다. */
	/*public SampleHotel(Chef chef) {
		this.chef = chef;
	}*/
}
