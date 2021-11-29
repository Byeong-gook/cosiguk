package com.coronacommunity.CoronaCommunity.web;

import com.coronacommunity.CoronaCommunity.repository.BoardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//@SpringBootTest : 기본적인 테스트 통합환경을 제공
//실제 운영과 비슷하게 모든 빈일 로드되어 주입 애플리케이션이 설정된다.
//다만 모든 bean을 로드하기때문에 구동시간이 오래걸린다.
//@wemMvcTest 는 스캔하는대상이 webApplication 관련된 빈으로 한정되어있다. 그렇기에 테스트는 빠르지만 Repository는 주입되지않는다.
//이 부분은 mock을통해 대체해서 사용하면 간결한 테스트가 가능하다.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//webEnvironment = WebEnvironment.RANDOM_PORT : 임의의 포트를 설정한다. 실제 내장 톰캣을 사용

@RunWith(SpringRunner.class)
public class PostControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private BoardRepository boardRepository;

    //실제 네트워크를 이용하지않고 API 테스트가 가능하게 됨.
    //MockMvc는 DispatcherServlet를 호출하는데 여기서 사용되는 DispatcherServlet은 TestDispatcherServlet이다.
    //TestDispatcherServlet은 매핑정보를 확인하여 적절한 컨트롤러를 호출해줌.
    //MockMvcBuilder를 통해 파라미터를 설정해줘야함
    private MockMvc mvc;

    private ObjectMapper mapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    //테스트가 진행되기전 실행됨
    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mapper = new ObjectMapper();
    }

    @After
    public void tearDown() throws Exception {
        boardRepository.deleteAll();
    }

}
