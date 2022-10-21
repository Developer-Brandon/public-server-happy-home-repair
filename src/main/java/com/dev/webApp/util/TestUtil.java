package com.dev.webApp.util;

import com.dev.webApp.domain.vo.FaqVO;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class TestUtil {

    private String faqDummyTitle = "테스트_자주하는질문_제목";

    private String faqDummyExplain = "테스트_자주하는질문_내용";

    public String getFaqDummyTitle() {
        return faqDummyTitle;
    }

    public void setFaqDummyTitle(String faqDummyTitle) {
        this.faqDummyTitle = faqDummyTitle;
    }

    public String getFaqDummyExplain() {
        return faqDummyExplain;
    }

    public void setFaqDummyExplain(String faqDummyExplain) {
        this.faqDummyExplain = faqDummyExplain;
    }

    public FaqVO getDummyFaqVO() {
        return FaqVO.builder()
                .title(getFaqDummyTitle())
                .content(getFaqDummyExplain())
                .build();
    }

    public String getDummyFaqVOToJson() {
        return new Gson().toJson(getDummyFaqVO());
    }
}
