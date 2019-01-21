package com.cota.posts;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cota.domain.Posts;
import com.cota.dto.PostsSaveDto;
import com.cota.repository.PostsRepository;
import com.cota.service.PostsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsService postsService;
    
    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        /** 
        이후 테스트 코드에 영향을 끼치지 않기 위해 
        테스트 메소드가 끝날때 마다 respository 전체 비우는 코드
        **/
    	postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
    	
    	PostsSaveDto dto = PostsSaveDto.builder()
    			.pTitle("게시글 제목")
                .pContent("게시글 내용")
                .pUno(1234l)
                .pThumbnail("썸네일 주소")
                .build();
    	
    	postsService.save(dto);

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getPTitle(), is("게시글 제목"));
        assertThat(posts.getPContent(), is("게시글 내용"));
    }
	
}
