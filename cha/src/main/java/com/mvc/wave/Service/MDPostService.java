package com.mvc.wave.Service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MDPostService {
	
	@Autowired
	SqlSessionTemplate mdCommentSST;
	
	
}
