package com.smhrd.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
	private String ptype;
	private String pcode;
	private String pname;
	private int price;
	private String color;
	private String img;
}
