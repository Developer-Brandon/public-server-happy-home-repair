package com.dev.webApp.service;

import com.dev.webApp.domain.dto.InsertBlogPostingListDTO;
import com.dev.webApp.domain.vo.BlogPostingVO;
import com.dev.webApp.domain.vo.RawBlogPostingVO;
import com.dev.webApp.mapper.BlogMapper;
import lombok.RequiredArgsConstructor;
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

    private List<RawBlogPostingVO> getRawBlogPostingList() throws Exception{


        // 1. 메인에서 타이틀과 이미지 경로를 불러옵니다.
        ArrayList<RawBlogPostingVO> blogInfoMapListForImage = new ArrayList<RawBlogPostingVO>();

        ArrayList<RawBlogPostingVO> finalBlogInfoMapListForImage = new ArrayList<RawBlogPostingVO>();

        getMainPostImageElements()
                .forEach(
                        Element -> {

                            RawBlogPostingVO blogInfoMap = new RawBlogPostingVO();

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
                    blogInfoMapListForImage.get(i)
                            .setPostingRegDt(regDt);
            }
        };

        return blogInfoMapListForImage;
    }

    @Override
    public List<BlogPostingVO> getBlogList() throws Exception {
        return blogMapper.selectBlogPostingList();
    }

    @Override
    public void setBlogList() throws Exception {

        List<RawBlogPostingVO> rawBlogPostingVOList = getRawBlogPostingList();

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

        // 찾다 찾다가 안나오니까 임시로....
        rawBlogPostingVOList.remove(0);

        rawBlogPostingVOList.forEach(e -> {

            System.out.println("is it true? : " + e.getPostingRegDt());
        });

        InsertBlogPostingListDTO insertBlogPostingListDTO = InsertBlogPostingListDTO.builder()
                .insertBlogPostingDTOList(rawBlogPostingVOList)
                .build();


        if(blogMapper.insertBlogPostingList(insertBlogPostingListDTO) == 0) {
            throw new Exception();
        }
    }
}
