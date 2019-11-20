package com.zty.signature.dao;

import com.zty.signature.entity.Background;

import java.util.List;
import java.util.Map;

public interface BackgroundDao {
    //增加签名照
    int add_background(Map map);

    //查询所有签名照
    List<Background> find();
}
