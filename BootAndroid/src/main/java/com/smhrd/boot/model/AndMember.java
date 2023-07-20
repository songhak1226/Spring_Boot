package com.smhrd.boot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity // JPA 관리
@Table(name="andmember2")
@Data
public class AndMember {
	@Id // primarykey
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="m_idx") // 컬럼이름
	private int mIdx;
	
	@Column(name="id", length=50)
	private String id;
	@Column(name="pw", length=50)
	private String pw;
	@Column(name="tel", length=50)
	private String tel;
	@Column(name="birth", length=50)
	private String birth;
	
}
