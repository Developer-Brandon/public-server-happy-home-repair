package com.dev.webApp.util.page;

import org.springframework.web.util.UriComponentsBuilder;

public class PageHandler {

    private SearchCondition sc;

    // 하나의 네비게이션의 사이즈e
    public final int NAV_SIZE = 10;

    // 화면에 보여줄(Navigation의) 첫 페이지
    private int beginPage;

    // 화면에 보여줄(Navigation의) 마지막 페이지
    private int endPage;

    // 게시물의 총 갯수
    private int totalCnt;

    // 전체 페이지의 갯수
    private int totalPage;

    // 이후를 보여줄지의 여부. endPage==totalPage이면, showNext는 false
    private boolean showNext = false;

    // 이전을 보여줄지의 여부. beginPage==1이 아니면 showPrev는 false
    private boolean showPrev = false;

    public PageHandler(int totalCnt, Integer currentPage) {

        this(totalCnt, new SearchCondition(currentPage, 10));
    }

    public PageHandler(int totalCnt, Integer currentPage, Integer pageSize) {

        // 총 게시물의 수, 현재 페이지, 페이지 사이즈
        this(totalCnt, new SearchCondition(currentPage, pageSize));
    }

    public PageHandler(int totalCnt, SearchCondition sc) {

        this.totalCnt = totalCnt;
        this.sc = sc;

        doPaging(totalCnt, sc);
    }

    private void doPaging(int totalCnt, SearchCondition sc) {

        // [총 페이지의 개수를 구하는 식]
        // 1. 전체 게시물의 개수를 하나의 네비게이션에서 보여주고자 하는 개수만큼 나눕니다.
        // 2. 전체 게시물의 개수에서 하나의 네비게이션에서 보여주고자 하는 개수만큼 나누었을때 나머지가 0이 아니라면 1, 맞다면 0으로 값을 바꾼 후에
        // 하나의 페이지를 더 더해줍니다(totalCnt % sc.getPageSize() == 0 ? 0 : 1)
        System.out.println("totalCnt: " + totalCnt);
        System.out.println("pageSize: " + sc.getPageSize());

        this.totalPage = totalCnt / sc.getPageSize() + (totalCnt % sc.getPageSize() == 0 ? 0 : 1);

        // [현재의 page가 totalPage보다 크지 않게 조정해줍니다]
        //
        this.sc.setPage(Math.min(sc.getPage(), totalPage));

        // [beginPage 구하는 식]
        // 현재의 페이지가 5면, beginPage는 1
        // 현재의 페이지가 11이면, beginPage는 11
        // 현재의 페이지가 23이면, beginPage는 21

        // 나누기 10을하고, 곱하기 10을 하면 1의자리수가 날아갑니다.
        this.beginPage = (this.sc.getPage() - 1) / NAV_SIZE * NAV_SIZE + 1;

        // [endPage를 구하는 식]
        // 가장 마지막 페이지를 구할 때에, 지금 현재 페이지의 beginPage에서 보여주고자 하는 navSize를 더해서 endPage를 구합니다.
        // 단, totalPage 보다 크면안되니까, 둘중 비교해서 작은값으로 endPage를 setting 해줍니다.
        this.endPage = Math.min(beginPage + NAV_SIZE - 1, totalPage);

        System.out.println("beginPage: " + beginPage);
        System.out.println("endPage: " + endPage);

        // todo: 이거 계속 false로 뜨는것 부터가 문제라서 이것부터 고치기
        System.out.println("setShowPrev: " + (this.beginPage != 1));
        System.out.println("setShowNext: " + (this.endPage != this.totalPage));

        this.setShowPrev(this.beginPage != 1);

        this.setShowNext(this.endPage != this.totalPage);

        print();
    }

    public String getQueryString() {
        return getQueryString(this.sc.getPage());
    }

    public String getQueryString(Integer page) {

        // ?page=10&pageSize=10&option=A&keyword=title
        return UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("pageSize", sc.getPageSize())
                .queryParam("option", sc.getOption())
                .queryParam("keyword", sc.getKeyword())
                .build().toString();
    }

    void print() {

        System.out.println("page=" + sc.getPage());

        System.out.print(isShowPrev() ? "PREV " : "");

        for (int i = beginPage; i <= endPage; i++) {
            System.out.print(i + " ");
        }

        System.out.println(isShowPrev() ? " NEXT" : "");
    }

    public SearchCondition getSc() {
        return sc;
    }

    public void setSc(SearchCondition sc) {
        this.sc = sc;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public boolean isShowNext() {
        return this.showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getNAV_SIZE() {
        return NAV_SIZE;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isShowPrev() {
        return this.showPrev;
    }

    public void setShowPrev(boolean showPrev) {
        this.showPrev = showPrev;
    }

    @Override
    public String toString() {
        return "PageHandler{" +
                "sc=" + sc +
                ", totalCnt=" + totalCnt +
                ", showNext=" + showNext +
                ", beginPage=" + beginPage +
                ", NAV_SIZE=" + NAV_SIZE +
                ", totalPage=" + totalPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                '}';
    }
}
