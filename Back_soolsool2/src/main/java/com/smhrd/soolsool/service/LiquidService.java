package com.smhrd.soolsool.service;

import com.smhrd.soolsool.domain.Liquid;
import com.smhrd.soolsool.mapper.LiquidMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiquidService {
    @Autowired
    private LiquidMapper liquidMapper;

    public List<Liquid> findAll() {
        return liquidMapper.findAll();
    }
}
