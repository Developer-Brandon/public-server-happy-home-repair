package com.dev.webApp.util.page;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class PageHandlerTest{

    // 전체 게시물이 250개이고 현재의 페이지가 1일때, 나머지 설정들이 제대로 설정되었는지
    @Test
    public void paginationTest() {

        PageHandler pageHandler = new PageHandler(250,1);

        assertThat(pageHandler.getBeginPage(), is(1));
        assertThat(pageHandler.getEndPage(), is(10));
        assertThat(pageHandler.getShowPrev(), is(false));
        assertThat(pageHandler.getShowNext(), is(true));
    }

    // 전체 게시물이 250개이고 현재의 페이지가 11일때, 나머지 설정들이 제대로 설정되었는지
    @Test
    public void paginationTest2() {

        PageHandler pageHandler = new PageHandler(250,11);

        assertThat(pageHandler.getBeginPage(), is(11));
        assertThat(pageHandler.getEndPage(), is(20));
        assertThat(pageHandler.getShowPrev(), is(true));
        assertThat(pageHandler.getShowNext(), is(true));
    }

    // 전체 게시물이 250개이고 현재의 페이지가 25(마지막 페이지)일때, 나머지 설정들이 제대로 설정되었는지
    @Test
    public void paginationTest3() {

        PageHandler pageHandler = new PageHandler(250,25);

        assertThat(pageHandler.getBeginPage(), is(21));
        assertThat(pageHandler.getEndPage(), is(25));
        assertThat(pageHandler.getShowPrev(), is(true));
        assertThat(pageHandler.getShowNext(), is(false));
    }

}
