package com.dev.webApp.service;

import com.dev.webApp.domain.dto.InsertBlogPostingListDTO;
import com.dev.webApp.domain.vo.BlogPostingVO;
import com.dev.webApp.domain.vo.RawBlogPostingVO;
import com.dev.webApp.mapper.BlogMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService{

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

    public List<RawBlogPostingVO> getRawBlogPostingListByCrawling() throws Exception{


        // 1. 메인에서 타이틀과 이미지 경로를 불러옵니다.
        ArrayList<RawBlogPostingVO> blogInfoMapListForImage = new ArrayList<RawBlogPostingVO>();

        ArrayList<RawBlogPostingVO> finalBlogInfoMapListForImage = new ArrayList<RawBlogPostingVO>();

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

        blogInfoMapListForImage = (ArrayList<RawBlogPostingVO>) finalBlogInfoMapListForImage.stream().limit(15).collect(Collectors.toList());


        // 2. 메인에서 날짜를 불러옵니다.(1번에서 불러온 태그에 존재하지 않았습니다)
        ArrayList<RawBlogPostingVO> blogInfoMapListForDateTime = new ArrayList<RawBlogPostingVO>();

        ArrayList<RawBlogPostingVO> finalBlogInfoMapListForDateTime = new ArrayList<RawBlogPostingVO>();

        getMainPostDateSpanElements().forEach(
                Element -> {

                    RawBlogPostingVO blogInfoMap = new RawBlogPostingVO();

                    blogInfoMap.setPostingRegDt(Element.text());

                    //
                    finalBlogInfoMapListForDateTime.add(blogInfoMap);
                });

        blogInfoMapListForDateTime = (ArrayList<RawBlogPostingVO>) finalBlogInfoMapListForDateTime.stream()
                .limit(15)
                .collect(Collectors.toList());


        /////////////////////////////////////////////////////////////////////////

        // 3.두개의 리스트를 합칩니다.
        // (다만, 날짜만 합칩니다)
        for(Integer j = 0; j < blogInfoMapListForImage.size(); j++){

            for(Integer i = 1; i < blogInfoMapListForDateTime.size(); i++) {

                String regDt = blogInfoMapListForDateTime.get(i).getPostingRegDt();

                // N시간 전 <- 이런식으로 posting 되는 string 방지
                // if(!regDt.matches(".*[시간]+.*")) {
                blogInfoMapListForImage.get(i).setPostingRegDt(regDt);
            }
        };

        blogInfoMapListForImage.forEach(System.out::println);

        return blogInfoMapListForImage;
    }

    @Override
    public List<BlogPostingVO> getBlogList() throws Exception {
        return blogMapper.selectBlogPostingList();
    }

    @Override
    public void setBlogList() throws Exception {

        if(getBlogList().size() == 0) {

            List<RawBlogPostingVO> rawBlogPostingVOList = getRawBlogPostingListByCrawling();

            /*
            rawBlogPostingVOList = rawBlogPostingVOList
                    .stream()
                    .map(e -> {
                        String regDt = e.getPostingRegDt();

                        if(regDt != null || regDt != "null" || !regDt.isEmpty()) {
                            return e;
                        } else {

                            e.setPostingRegDt("최근");

                            return e;
                        }
                    })
                    .collect(Collectors.toList());
            */

            // 찾다 찾다가 안나오니까 임시로....가장 최근의 element는 삭제하는 것으로....
            rawBlogPostingVOList.remove(0);

            InsertBlogPostingListDTO insertBlogPostingListDTO = InsertBlogPostingListDTO.builder()
                    .insertBlogPostingDTOList(rawBlogPostingVOList)
                    .build();

            if(blogMapper.insertBlogPostingList(insertBlogPostingListDTO) == 0) {
                throw new Exception();
            }
        } else {

            throw new Exception();
        }
    }

    @Override
    public void setDifferentBlogList() throws Exception {

        // todo: 아래 두개의 메소드를 비교할 때, 조회할때 포스팅이 등록된 날짜 기준으로 제대로 조회하고 있는지 확인이 필요합니다.

        List<RawBlogPostingVO> rawBlogPostingVOListFromDB = getBlogList()
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

        List<RawBlogPostingVO> rawBlogPostingVOListByCrawling = getRawBlogPostingListByCrawling()
                .stream()
                .peek(e -> e.setPostingTypeNo(1))
                .collect(Collectors.toList());

        // 여기서도 임시로...
        rawBlogPostingVOListByCrawling.remove(0);

        // 1. list 두개의 제목을 비교합니다
        RawBlogPostingVO topRawBlogPostingVO = rawBlogPostingVOListFromDB.get(0);

        RawBlogPostingVO topRawBlogPostingVOByCrawling = rawBlogPostingVOListByCrawling.get(0);

        // 1-1. 만약, 가장 상단에 있는 아이템을 하나씩 뺴서 비교한후 다르다면?
        if(!topRawBlogPostingVO.getTitle().equals(topRawBlogPostingVOByCrawling.getTitle())) {

            // 2. 다른 리스트가 하나라도 있다면, 기존의 불러온 crawling list를 기준으로 db에서 불러온 list만큼 삭제시켜줍니다.
                rawBlogPostingVOListByCrawling.removeAll(rawBlogPostingVOListFromDB);

                // 4. 해당 리스트를 전부, insert해줍니다.
                InsertBlogPostingListDTO insertBlogPostingListDTO = InsertBlogPostingListDTO.builder()
                        .insertBlogPostingDTOList(rawBlogPostingVOListByCrawling)
                        .build();

                if(blogMapper.insertBlogPostingList(insertBlogPostingListDTO) == 0) {
                    throw new Exception();
                }


        } else {

            // 3. 만약 다른 리스트가 하나도 없다면, 아무것도 하지 않습니다.
        }
    }
}
