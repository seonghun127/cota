package com.cota.posts;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cota.domain.Posts;
import com.cota.dto.posts.*;
import com.cota.dto.users.UsersSaveDto;
import com.cota.repository.PostsRepository;
import com.cota.repository.UsersRepository;
import com.cota.service.PostsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    UsersRepository usersRepository;
    
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
        usersRepository.deleteAll();
    	postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given

        UsersSaveDto usersDto = UsersSaveDto.builder()
                .uName("홍길동")
                .uEmail("email@email.com")
                .build();

        usersRepository.save(usersDto.toEntity());
    	
        PostsSaveDto postsDto = PostsSaveDto.builder()
    			.pTitle("게시글 제목")
                .pContent("게시글 내용")
                .pUno(1l)
                .pThumbnail("썸네일 주소")
                .build();
    	
        postsService.save(postsDto);
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("check", "all_post_details");
        param.put("rowNum", 0);

        //when
        List<PostsListDto> postsList = postsService.findPost(param);

        //then
        PostsListDto posts = postsList.get(0);
        assertThat(posts.getPTitle(), is("게시글 제목"));
        assertThat(posts.getPContent(), is("게시글 내용"));
    }
	
}
