package com.dev.webApp.service;

import com.dev.webApp.domain.dto.InsertBlogPostingListDTO;
import com.dev.webApp.domain.dto.SelectBlogPostingPaginationDTO;
import com.dev.webApp.domain.vo.BlogPostingVO;
import com.dev.webApp.domain.vo.PaginationBlogPostingVO;
import com.dev.webApp.domain.vo.RawBlogPostingVO;
import com.dev.webApp.mapper.BlogMapper;
import com.dev.webApp.util.page.PageHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    private static final String testUrl = "https://blog.naver.com/prologue/PrologueList.naver?blogId=lain4444&amp;directAccess=true";

    private Document document;

    private static Document getConnection() throws IOException {
        return Jsoup.connect(testUrl).get();
    }

    private Elements getMainPostImageElements() throws IOException {
        return (Elements) getConnection().select("p.p_img a img");
    }

    private Elements getMainPostDateSpanElements() throws IOException {
        return (Elements) getConnection().select("li.p_date span");
    }

    public List<RawBlogPostingVO> getRawBlogPostingListByCrawling(Integer crawlingCnt) throws Exception {

        // 1. 메인에서 타이틀과 이미지 경로를 불러옵니다.
        ArrayList<RawBlogPostingVO> blogInfoMapListForImage = new ArrayList<RawBlogPostingVO>();

        ArrayList<RawBlogPostingVO> finalBlogInfoMapListForImage = new ArrayList<RawBlogPostingVO>();

        // [주의] image 태그 속에는 date가 없습니다.
        getMainPostImageElements()
                .forEach(
                        Element -> {

                            RawBlogPostingVO blogInfoMap = new RawBlogPostingVO();

                            blogInfoMap.setPostingTypeNo(1);
                            blogInfoMap.setTitle(Element.attr("title"));
                            blogInfoMap.setImgSrc(Element.attr("src"));

                            //
                            finalBlogInfoMapListForImage.add(blogInfoMap);
                        });

        // 15개만 뽑습니다. 3개씩 5줄이 출력되도록...
        blogInfoMapListForImage = (ArrayList<RawBlogPostingVO>) finalBlogInfoMapListForImage
                .stream()
                .limit(crawlingCnt)
                .collect(Collectors.toList());

        // 2. 메인에서 날짜를 불러옵니다.(1번에서 불러온 태그에 날짜가 존재하지 않았기때문....)
        ArrayList<RawBlogPostingVO> blogInfoMapListForDateTime = new ArrayList<RawBlogPostingVO>();

        ArrayList<RawBlogPostingVO> finalBlogInfoMapListForDateTime = new ArrayList<RawBlogPostingVO>();

        getMainPostDateSpanElements()
                .forEach(
                        Element -> {

                            RawBlogPostingVO blogInfoMap = new RawBlogPostingVO();

                            blogInfoMap.setPostingRegDt(Element.text());

                            //
                            finalBlogInfoMapListForDateTime.add(blogInfoMap);
                        });

        blogInfoMapListForDateTime = (ArrayList<RawBlogPostingVO>) finalBlogInfoMapListForDateTime
                .stream()
                .limit(crawlingCnt)
                .collect(Collectors.toList());

        // 임시코드
        //        finalBlogInfoMapListForDateTime
        //                .stream()
        //                .map(Element -> {
        //                    if(Element.getPostingRegDt().equals("2022. 9. 8.")) {
        //                        Element.setPostingRegDt("1시간 전");
        //                    }
        //                    return Element;
        //                })
        //                .collect(Collectors.toList());
        //        System.out.println("finalBlogInfoMapListForDateTime" + finalBlogInfoMapListForDateTime);

        /////////////////////////////////////////////////////////////////////////

        // 3.DateTime을 순회를 돌며, regDt만을 get하여 blogInfoMapListForImage에 날짜만을 순서대로 쌓습니다
        // (이렇게 되면 기존의 blogInfoMapListForImage에 날짜만 쌓일 것입니다.)
        for (Integer i = 0; i < blogInfoMapListForDateTime.size(); i++) {

            String regDt = blogInfoMapListForDateTime.get(i).getPostingRegDt();

            // N시간 전 <- 이런식으로 posting 되는 string을 방지하기 위한 로직
            // 만약, 따끈따끈한 포스팅이라면 현재 날짜를 가져와서, 원하는 포멧으로 치환해서 set 해준다.
            if (regDt.matches(".*[초]+.*") || regDt.matches(".*[분]+.*") || regDt.matches(".*[시간]+.*")) {

                Date currentTime = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장한다
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy. MM. dd.");

                String convertingFormat = simpleDateFormat.format(currentTime);

                blogInfoMapListForImage.get(i).setPostingRegDt(convertingFormat);
            } else {

                blogInfoMapListForImage.get(i).setPostingRegDt(regDt);
            }
        }

        return blogInfoMapListForImage;
    }

    @Override
    public List<BlogPostingVO> getAllBlogList() {
        return blogMapper.selectAllBlogPostingList();
    }

    @Override
    public PaginationBlogPostingVO getBlogList(SelectBlogPostingPaginationDTO selectBlogPostingPaginationDTO) throws Exception {

        int totalCnt = blogMapper.getTotalCnt();

        PageHandler pageHandler = new PageHandler(totalCnt, selectBlogPostingPaginationDTO.getCurrentPage());

        int offset = selectBlogPostingPaginationDTO.getCurrentPage() - 1;

        selectBlogPostingPaginationDTO.setOffset(offset * selectBlogPostingPaginationDTO.getPageSize());

        selectBlogPostingPaginationDTO.setPageSize(selectBlogPostingPaginationDTO.getPageSize());

        List<BlogPostingVO> blogPostingVOList = blogMapper.selectBlogPostingList(selectBlogPostingPaginationDTO);

        return PaginationBlogPostingVO.builder()
                .pageHandler(pageHandler)
                .blogPostingVOList(blogPostingVOList)
                .build();
    }

    @Override
    public void setBlogList() throws Exception {

        // 1. 처음 시작은 기존에 있는 데이터를 전부 밀어버리고 시작합니다.
        // 기존의 데이터를 전부 밀어도 상관없다고 판단한 이유가, 어짜피 블로그의 글들은 계속 업데이트 될 테고
        // 홈페이지는 단지 블로그로 넘어가기 전 징검다리 같은 역할만을 하는거니까....
        // todo: 하지만, 나중에 시간이 된다면 batch를 작성하여 기존의 data들을 주기적으로 백업하는 로직을 구성해보도록 하기.
        blogMapper.deleteAll();

        // 2. 그 후 다시 전체 게시물의 개수를 조회하고 validation 합니다.
        int totalCnt = blogMapper.getTotalCnt();

        // 3. 제대로 삭제되었다면 크롤링을 하여 대량 insert를 진행합니다.
        if (totalCnt == 0) {

            List<RawBlogPostingVO> rawBlogPostingVOList = getRawBlogPostingListByCrawling(15);

            InsertBlogPostingListDTO insertBlogPostingListDTO = InsertBlogPostingListDTO.builder()
                    .insertBlogPostingDTOList(rawBlogPostingVOList)
                    .build();

            if (blogMapper.insertBlogPostingList(insertBlogPostingListDTO) == 0) {
                throw new Exception("데이터를 대량으로 insert 하는 과정에서 문제가 발생하였습니다.");
            }
        } else {

            throw new Exception("데이터 초기화를 위한 삭제 과정에서 문제가 발생하였습니다.");
        }
    }

    @Override
    public void setOnlyDifferentBlogList() throws Exception {

        List<RawBlogPostingVO> rawBlogPostingVOListFromDB = getAllBlogList()
                .stream()
                .map(e -> {

                    RawBlogPostingVO rawBlogPostingVO = RawBlogPostingVO.builder()
                            .postingTypeNo(1)
                            .title(e.getTitle())
                            .imgSrc(e.getImgSrc())
                            .postingRegDt(e.getPostingRegDt())
                            .build();

                    return rawBlogPostingVO;
                })
                .collect(Collectors.toList());

        List<RawBlogPostingVO> rawBlogPostingVOListByCrawling = getRawBlogPostingListByCrawling(1000)
                .stream()
                .peek(e -> e.setPostingTypeNo(1))
                .collect(Collectors.toList());

        // 1. list 두개의 제목을 비교합니다
        RawBlogPostingVO topRawBlogPostingVO = rawBlogPostingVOListFromDB.get(0);

        RawBlogPostingVO topRawBlogPostingVOByCrawling = rawBlogPostingVOListByCrawling.get(0);

        // 1-1. 만약, 가장 상단에 있는 아이템을 하나씩 뺴서 비교한후 다르다면?
        if (!topRawBlogPostingVO.getTitle().equals(topRawBlogPostingVOByCrawling.getTitle())) {

            // 2. 다른 리스트가 하나라도 있다면, 기존의 불러온 crawling list를 기준으로 db에서 불러온 list만큼'만' 삭제시켜줍니다.
            rawBlogPostingVOListByCrawling.removeAll(rawBlogPostingVOListFromDB);

            // 4. 다른 리스트만큼만, insert 해줍니다.
            InsertBlogPostingListDTO insertBlogPostingListDTO = InsertBlogPostingListDTO.builder()
                    .insertBlogPostingDTOList(rawBlogPostingVOListByCrawling)
                    .build();

            if (blogMapper.insertBlogPostingList(insertBlogPostingListDTO) == 0) {
                throw new Exception("데이터를 대량으로 insert 하는 과정에서 문제가 발생하였습니다.");
            }

        } else {

            // 3. 만약 다른 리스트가 하나도 없다면, 아무것도 하지 않습니다.
        }
    }
}
