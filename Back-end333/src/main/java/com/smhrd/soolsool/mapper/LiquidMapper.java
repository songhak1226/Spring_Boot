package com.smhrd.soolsool.mapper;

import com.smhrd.soolsool.domain.Liquid;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LiquidMapper {
    List<Liquid> findAll();
}
