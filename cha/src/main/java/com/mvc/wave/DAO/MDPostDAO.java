package com.mvc.wave.DAO;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.wave.DTO.MDPostDTO;

@Repository
public class MDPostDAO {

	@Autowired
	SqlSessionTemplate mdPostSST;

	
	public List<MDPostDTO> MDAll() {
		return mdPostSST.selectList("MD.PostsAll");
	}

	public MDPostDTO getMDPostById(int id) {
		return mdPostSST.selectOne("MD.GetPostsById", id);
	}

	
	public int MDPostinsert(MDPostDTO mdPostDTO) {
		return mdPostSST.insert("MD.PostsMake", mdPostDTO);
	}

	
	public int MDPostUpdate(MDPostDTO mdPostDTO) {
		return mdPostSST.update("MD.PostUpdate", mdPostDTO);
	}
	
	
	public int MDPostDelete(MDPostDTO mdPostDTO) {
		return mdPostSST.delete("MD.PostDelete", mdPostDTO);
	}
}
