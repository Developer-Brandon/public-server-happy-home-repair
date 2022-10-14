package com.dev.webApp.controller.repair;

import com.dev.webApp.config.controller.BaseConfigController;
import com.dev.webApp.domain.vo.RepairApplyVO;
import com.dev.webApp.util.NumberUtil;
import com.google.gson.Gson;
import junit.framework.TestCase;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Transactional
@Log4j
@RunWith(SpringJUnit4ClassRunner.class) // test for using junit4
@ContextConfiguration({
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
        ,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration // Test for controller
public class RepairControllerTests extends TestCase {

    // todo: 추후 작성 예정....

//    @Autowired
//    private WebApplicationContext ctx;
//
//    @Autowired
//    private NumberUtil numberUtil;
//
//    private MockMvc mockMvc;
//
//    private static final Logger logger = LoggerFactory.getLogger(RepairControllerTests.class);
//
//    @Before
//    public void init() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
//    }
//
//    @Test
//    public void insertRepairAndSelectRepairListTest() throws Exception {
//
//        // repair list가 제대로 조회되는지 테스트 하기
//
//        // 1. 먼저, repair를 삽입하기
//
//        // given
//        String url = "/repair";
//
//        int randomInteger = numberUtil.getRandomNumber();
//
//        RepairApplyVO repairVO = RepairApplyVO.builder()
//                .title("테스트" + randomInteger + "_자주하는질문_제목")
//                .content("테스트" + randomInteger + "_자주하는질문_내용")
//                .build();
//
//        String insertRepairDTO = new Gson().toJson(repairVO);
//
//        // when
//        ResultActions resultActions = mockMvc
//                .perform(
//                        post(url)
//                                .contentType(BaseConfigController.JSON_FORMAT)
//                                .content(insertRepairDTO)
//                )
//                .andDo(print());
//
//        // then
//        resultActions
//                .andExpect(status().is(200))
//                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT));
//
//        /////////////////////////////////////////////////////////
//
//        // 2. 그 후, repair list 조회해서 방금 전 삽입한 repair가 제대로 삽입되었는지 확인하기
//        // (단일 조회는 아래에서 테스트 예정입니다)
//
//        // given
//        String url2 = "/repair/list";
//
//        // when
//        ResultActions resultActions2 = mockMvc
//                .perform(
//                        get(url2)
//                        .contentType(BaseConfigController.JSON_FORMAT)
//                )
//                .andDo(print());
//
//        // then
//        resultActions2
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT))
//                .andExpect(jsonPath("$.[0].title").exists())
//                .andExpect(jsonPath("$.[0].title").isString())
//                .andExpect(jsonPath("$.[0].title", is(containsString("테스트" + randomInteger))))
//                .andExpect(jsonPath("$.[0].content").exists())
//                .andExpect(jsonPath("$.[0].content").isString())
//                .andExpect(jsonPath("$.[0].content", is(containsString("테스트" + randomInteger))));
//    }
//
//    @Test
//    public void insertAndSelectRepairTest() throws Exception {
//
//        // 단일 repair가 조회되는지 확인하기
//
//        // 1. 먼저, repair를 삽입하기
//
//        // given
//        String url = "/repair";
//
//        int randomInteger = numberUtil.getRandomNumber();
//
//        RepairVO repairVO = RepairVO.builder()
//                .title("테스트" + randomInteger + "_자주하는질문_제목")
//                .content("테스트" + randomInteger + "_자주하는질문_내용")
//                .build();
//
//        String insertRepairDTO = new Gson().toJson(repairVO);
//
//        // when
//        ResultActions resultActions = mockMvc
//                .perform(
//                        post(url)
//                                .contentType(BaseConfigController.JSON_FORMAT)
//                                .content(insertRepairDTO)
//                )
//                .andDo(print());
//
//        // then
//        resultActions
//                .andExpect(status().is(200))
//                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT));
//
//        MvcResult mvcResult = resultActions.andReturn();
//
//        String mvcResultToString = mvcResult.getResponse().getContentAsString();
//
//        logger.info("mvcResultToString: {}", mvcResultToString);
//
//        /////////////////////////////////////////////////////////
//
//        // 2. 단일 repair 조회로 검증
//
//        // given
//        String repairNo = mvcResultToString;
//
//        String url2 = "/repair?repairNo=" + repairNo;
//
//        // when
//        ResultActions resultActions2 = mockMvc
//                .perform(get(url2))
//                .andDo(print());
//
//        // then
//        resultActions2
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT))
//                .andExpect(jsonPath("$.title").exists())
//                .andExpect(jsonPath("$.title").isString())
//                .andExpect(jsonPath("$.title", is(containsString("테스트" + randomInteger))))
//                .andExpect(jsonPath("$.content").exists())
//                .andExpect(jsonPath("$.content").isString())
//                .andExpect(jsonPath("$.content", is(containsString("테스트" + randomInteger))));
//    }
//
//    @Test
//    public void insertRepair() throws Exception {
//
//       // 위의 테스트에서 검증했으므로 추가 검증은 하지 않도록 하겠습니다.
//    }
//
//    @Test
//    public void updateRepair() throws Exception {
//
//        // 삽입한 repair를 수정한 후, 제대로 조회가 되는지 확인
//
//        // 1. 먼저, repair를 삽입하기
//
//        // given
//        String url = "/repair";
//
//        int randomInteger = numberUtil.getRandomNumber();
//
//        RepairVO repairVO = RepairVO.builder()
//                .title("테스트" + randomInteger + "_자주하는질문_제목")
//                .content("테스트" + randomInteger + "_자주하는질문_내용")
//                .build();
//
//        String insertRepairDTO = new Gson().toJson(repairVO);
//
//        // when
//        ResultActions resultActions = mockMvc
//                .perform(
//                        post(url)
//                                .contentType(BaseConfigController.JSON_FORMAT)
//                                .content(insertRepairDTO)
//                )
//                .andDo(print());
//
//        // then
//        resultActions
//                .andExpect(status().is(200))
//                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT));
//
//        MvcResult mvcResult = resultActions.andReturn();
//
//        String mvcResultToString = mvcResult.getResponse().getContentAsString();
//
//        logger.info("mvcResultToString: {}", mvcResultToString);
//
//        /////////////////////////////////////////////////////////
//
//        // 2. 수정하기
//
//        RepairVO updateRepairVO = RepairVO.builder()
//                .repairNo(Integer.valueOf(mvcResultToString))
//                .title("테스트" + randomInteger + "_자주하는질문_제목")
//                .content("테스트" + randomInteger + "_자주하는질문_내용")
//                .useYnEnum(RepairUseYnEnum.Y)
//                .build();
//
//        String updateRepairDTO = new Gson().toJson(updateRepairVO);
//
//        ResultActions resultActions2 = mockMvc
//                .perform(
//                        put(url)
//                            .contentType(BaseConfigController.JSON_FORMAT)
//                            .content(updateRepairDTO)
//                )
//                .andDo(print());
//
//        resultActions2
//                .andExpect(status().is(200));
//
//        /////////////////////////////////////////////////////////
//
//        // 3. 단일 조회로 수정된 데이터 검증
//
//        // given
//        String url3 = "/repair?repairNo=" + mvcResultToString;
//
//        // when
//        ResultActions resultActions3 = mockMvc
//                .perform(get(url3))
//                .andDo(print());
//
//        // then
//        resultActions3
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT))
//                .andExpect(jsonPath("$.title").exists())
//                .andExpect(jsonPath("$.title").isString())
//                .andExpect(jsonPath("$.title", is(containsString("테스트" + randomInteger))))
//                .andExpect(jsonPath("$.content").exists())
//                .andExpect(jsonPath("$.content").isString())
//                .andExpect(jsonPath("$.content", is(containsString("테스트" + randomInteger))));
//    }
//
//    @Test
//    public void updateRepairState() throws Exception {
//
//        // 삽입한 repair의 상태를 수정한 후, 제대로 조회가 되는지 확인
//
//        // 1. 먼저, repair를 삽입하기
//
//        // given
//        String url = "/repair";
//
//        int randomInteger = numberUtil.getRandomNumber();
//
//        RepairVO repairVO = RepairVO.builder()
//                .title("테스트" + randomInteger + "_자주하는질문_제목")
//                .content("테스트" + randomInteger + "_자주하는질문_내용")
//                .build();
//
//        String insertRepairDTO = new Gson().toJson(repairVO);
//
//        // when
//        ResultActions resultActions = mockMvc
//                .perform(
//                        post(url)
//                            .contentType(BaseConfigController.JSON_FORMAT)
//                            .content(insertRepairDTO)
//                )
//                .andDo(print());
//
//        // then
//        resultActions
//                .andExpect(status().is(200))
//                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT));
//
//        MvcResult mvcResult = resultActions.andReturn();
//
//        String mvcResultToString = mvcResult.getResponse().getContentAsString();
//
//        logger.info("mvcResultToString: {}", mvcResultToString);
//
//        /////////////////////////////////////////////////////////
//
//        // 2. 상태 수정하기
//
//        // given
//        String url2 = "/repair/state";
//
//        RepairVO updateStateRepairVO = RepairVO.builder()
//                .repairNo(Integer.valueOf(mvcResultToString))
//                .useYnEnum(RepairUseYnEnum.N)
//                .build();
//
//        String updateRepairStateDTO = new Gson().toJson(updateStateRepairVO);
//
//        // when
//        ResultActions resultActions2 = mockMvc
//                .perform(
//                        put(url2)
//                                .contentType(BaseConfigController.JSON_FORMAT)
//                                .content(updateRepairStateDTO)
//                )
//                .andDo(print());
//
//        // then
//        resultActions2
//                .andExpect(status().is(200));
//
//        /////////////////////////////////////////////////////////
//
//        // 3. 단일 조회로 수정된 데이터 검증
//
//        // given
//        String url3 = "/repair?repairNo=" + mvcResultToString;
//
//        // when
//        ResultActions resultActions3 = mockMvc
//                .perform(get(url3))
//                .andDo(print());
//
//        // then
//        resultActions3
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT))
//                .andExpect(jsonPath("$.useYnEnum").exists())
//                .andExpect(jsonPath("$.useYnEnum").isString())
//                .andExpect(jsonPath("$.useYnEnum", is("N")));
//
//    }
//
//    @Test
//    public void deleteAndSelectRepair() throws Exception {
//
//
//        // 삽입한 repair의 상태를 수정한 후, 제대로 조회가 되는지 확인
//
//        // 1. 먼저, repair를 삽입하기
//
//        // given
//        String url = "/repair";
//
//        int randomInteger = numberUtil.getRandomNumber();
//
//        RepairVO repairVO = RepairVO.builder()
//                .title("테스트" + randomInteger + "_자주하는질문_제목")
//                .content("테스트" + randomInteger + "_자주하는질문_내용")
//                .build();
//
//        String insertRepairDTO = new Gson().toJson(repairVO);
//
//        // when
//        ResultActions resultActions = mockMvc
//                .perform(
//                        post(url)
//                                .contentType(BaseConfigController.JSON_FORMAT)
//                                .content(insertRepairDTO)
//                )
//                .andDo(print());
//
//        // then
//        resultActions
//                .andExpect(status().is(200))
//                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT));
//
//        MvcResult mvcResult = resultActions.andReturn();
//
//        String mvcResultToString = mvcResult.getResponse().getContentAsString();
//
//        logger.info("mvcResultToString: {}", mvcResultToString);
//
//        /////////////////////////////////////////////////////
//
//        // 2. 그 후 삭제합니다
//
//        String url2 = "/repair?repairNo=" + mvcResultToString;
//
//        ResultActions resultActions2 = mockMvc
//                .perform(
//                        delete(url2)
//                            .contentType(BaseConfigController.JSON_FORMAT)
//                )
//                .andDo(print());
//
//        resultActions2
//                .andExpect(status().is(200));
//
//        /////////////////////////////////////////////////////
//
//        // 3. 삭제가 제대로 되었는지 단일 조회로 검증합니다.
//
//        // given
//        String url3 = "/repair?repairNo=" + mvcResultToString;
//
//        // when
//        ResultActions resultActions3 = mockMvc
//                .perform(get(url3))
//                .andDo(print());
//
//        // then
//        resultActions3
//                .andExpect(status().is(200))
//                .andExpect(result -> assertNotNull(result.getResolvedException()));
//    }
}
