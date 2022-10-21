package com.dev.webApp.controller.repair;

import com.dev.webApp.config.controller.BaseConfigController;
import com.dev.webApp.domain.dto.InsertRepairApplyDTO;
import com.dev.webApp.domain.dto.UpdateRepairApplyDTO;
import com.dev.webApp.util.AgreeOrNotEnum;
import com.dev.webApp.util.NumberUtil;
import com.dev.webApp.util.UserTypeEnum;
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

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private NumberUtil numberUtil;

    private MockMvc mockMvc;

    private static final Logger logger = LoggerFactory.getLogger(RepairControllerTests.class);

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void insertRepairAndSelectRepairListTest() throws Exception {

        // repair list가 제대로 조회되는지 테스트 하기

        // 1. 먼저, repair를 삽입하기

        // given
        String url = "/repair";

        int randomInteger = numberUtil.getRandomNumber();

        InsertRepairApplyDTO insertRepairApplyDTO = InsertRepairApplyDTO.builder()
                .repairTypeNo(1)
                .repairLocationNo(1)
                .repairStateNo(1)
                .userTypeEnum(UserTypeEnum.CLIENT)
                .phoneNumber("01088884444")
                .agreeOrNotEnum(AgreeOrNotEnum.Y)
                .explanation("테스트_데이터" + randomInteger)
                .build();

        String insertRepairDTO = new Gson().toJson(insertRepairApplyDTO);

        // when
        ResultActions resultActions = mockMvc
                .perform(
                        post(url)
                                .contentType(BaseConfigController.JSON_FORMAT)
                                .content(insertRepairDTO)
                )
                .andDo(print());

        // then
        resultActions
                .andExpect(status().is(200))
                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT));

        /////////////////////////////////////////////////////////

        // 2. 그 후, repair list 조회해서 방금 전 삽입한 repair가 제대로 삽입되었는지 확인하기
        // (단일 조회는 아래에서 테스트 예정입니다)

        // given
        String url2 = "/repair/list";

        // when
        ResultActions resultActions2 = mockMvc
                .perform(
                        get(url2)
                        .contentType(BaseConfigController.JSON_FORMAT)
                )
                .andDo(print());

        // then
        resultActions2
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.[0].explanation").exists())
                .andExpect(jsonPath("$.[0].explanation").isString())
                .andExpect(jsonPath("$.[0].explanation", is(containsString("테스트_데이터" + randomInteger))));
    }

    @Test
    public void insertAndSelectRepairTest() throws Exception {

        // 단일 repair가 조회되는지 확인하기

        // 1. 먼저, repair를 삽입하기

        // given
        String url = "/repair";

        int randomInteger = numberUtil.getRandomNumber();

        InsertRepairApplyDTO insertRepairApplyDTO = InsertRepairApplyDTO.builder()
                .repairTypeNo(1)
                .repairLocationNo(1)
                .repairStateNo(1)
                .userTypeEnum(UserTypeEnum.CLIENT)
                .phoneNumber("01088884444")
                .agreeOrNotEnum(AgreeOrNotEnum.Y)
                .explanation("테스트_데이터" + randomInteger)
                .build();

        String insertRepairDTO = new Gson().toJson(insertRepairApplyDTO);

        // when
        ResultActions resultActions = mockMvc
                .perform(
                        post(url)
                                .contentType(BaseConfigController.JSON_FORMAT)
                                .content(insertRepairDTO)
                )
                .andDo(print());

        // then
        resultActions
                .andExpect(status().is(200))
                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT));

        MvcResult mvcResult = resultActions.andReturn();

        String mvcResultToString = mvcResult.getResponse().getContentAsString();

        logger.info("mvcResultToString: {}", mvcResultToString);

        /////////////////////////////////////////////////////////

        // 2. 단일 repair 조회로 검증

        // given
        String repairNo = mvcResultToString;

        String url2 = "/repair?repairApplyNo=" + repairNo;

        // when
        ResultActions resultActions2 = mockMvc
                .perform(get(url2))
                .andDo(print());

        // then
        resultActions2
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.explanation").exists())
                .andExpect(jsonPath("$.explanation").isString())
                .andExpect(jsonPath("$.explanation", is(containsString("테스트_데이터" + randomInteger))));
    }

    @Test
    public void insertRepair() throws Exception {

       // 위의 테스트에서 검증했으므로 추가 검증은 하지 않도록 하겠습니다.
    }

    @Test
    public void updateRepair() throws Exception {

        // 삽입한 repair를 수정한 후, 제대로 조회가 되는지 확인

        // 1. 먼저, repair를 삽입하기

        // given
        String url = "/repair";

        int randomInteger = numberUtil.getRandomNumber();

        InsertRepairApplyDTO insertRepairApplyDTO = InsertRepairApplyDTO.builder()
                .repairTypeNo(1)
                .repairLocationNo(1)
                .repairStateNo(1)
                .userTypeEnum(UserTypeEnum.CLIENT)
                .phoneNumber("01088884444")
                .agreeOrNotEnum(AgreeOrNotEnum.Y)
                .explanation("테스트_데이터" + randomInteger)
                .build();

        String insertRepairDTO = new Gson().toJson(insertRepairApplyDTO);

        // when
        ResultActions resultActions = mockMvc
                .perform(
                        post(url)
                                .contentType(BaseConfigController.JSON_FORMAT)
                                .content(insertRepairDTO)
                )
                .andDo(print());

        // then
        resultActions
                .andExpect(status().is(200))
                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT));

        MvcResult mvcResult = resultActions.andReturn();

        String mvcResultToString = mvcResult.getResponse().getContentAsString();

        logger.info("mvcResultToString: {}", mvcResultToString);

        /////////////////////////////////////////////////////////

        // 2. 수정하기

        // given
        UpdateRepairApplyDTO updateRepairApplyDTO = UpdateRepairApplyDTO.builder()
                .repairApplyNo(Integer.valueOf(mvcResultToString))
                .repairTypeNo(1)
                .repairLocationNo(1)
                .repairStateNo(1)
                .userTypeEnum(UserTypeEnum.CLIENT)
                .phoneNumber("01088884444")
                .explanation("수정_테스트_데이터" + randomInteger)
                .build();

        String updateRepairDTO = new Gson().toJson(updateRepairApplyDTO);

        // when
        ResultActions resultActions2 = mockMvc
                .perform(
                        put(url)
                            .contentType(BaseConfigController.JSON_FORMAT)
                            .content(updateRepairDTO)
                )
                .andDo(print());

        // then
        resultActions2
                .andExpect(status().is(200));

        /////////////////////////////////////////////////////////

        // 3. 단일 조회로 수정된 데이터 검증

        // given
        String url3 = "/repair?repairApplyNo=" + mvcResultToString;

        // when
        ResultActions resultActions3 = mockMvc
                .perform(get(url3))
                .andDo(print());

        // then
        resultActions3
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.explanation").exists())
                .andExpect(jsonPath("$.explanation").isString())
                .andExpect(jsonPath("$.explanation", is(containsString("수정_테스트_데이터" + randomInteger))));
    }

    @Test
    public void deleteAndSelectRepair() throws Exception {

        // 삽입한 repair의 상태를 수정한 후, 제대로 조회가 되는지 확인

        // 1. 먼저, repair를 삽입하기

        // given
        String url = "/repair";

        int randomInteger = numberUtil.getRandomNumber();

        InsertRepairApplyDTO insertRepairApplyDTO = InsertRepairApplyDTO.builder()
                .repairTypeNo(1)
                .repairLocationNo(1)
                .repairStateNo(1)
                .userTypeEnum(UserTypeEnum.CLIENT)
                .phoneNumber("01088884444")
                .agreeOrNotEnum(AgreeOrNotEnum.Y)
                .explanation("테스트_데이터" + randomInteger)
                .build();

        String insertRepairDTO = new Gson().toJson(insertRepairApplyDTO);

        // when
        ResultActions resultActions = mockMvc
                .perform(
                        post(url)
                                .contentType(BaseConfigController.JSON_FORMAT)
                                .content(insertRepairDTO)
                )
                .andDo(print());

        // then
        resultActions
                .andExpect(status().is(200))
                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT));

        MvcResult mvcResult = resultActions.andReturn();

        String mvcResultToString = mvcResult.getResponse().getContentAsString();

        logger.info("mvcResultToString: {}", mvcResultToString);

        /////////////////////////////////////////////////////

        // 2. 그 후 삭제합니다

        String url2 = "/repair?repairApplyNo=" + mvcResultToString;

        ResultActions resultActions2 = mockMvc
                .perform(
                        delete(url2)
                            .contentType(BaseConfigController.JSON_FORMAT)
                )
                .andDo(print());

        resultActions2
                .andExpect(status().is(200));

        /////////////////////////////////////////////////////

        // 3. 삭제가 제대로 되었는지 단일 조회로 검증합니다.

        // given
        String url3 = "/repair?repairApplyNo=" + mvcResultToString;

        // when
        ResultActions resultActions3 = mockMvc
                .perform(get(url3))
                .andDo(print());

        // then
        resultActions3
                .andExpect(status().is(200))
                .andExpect(result -> assertNull(result.getResolvedException()));
    }
}
