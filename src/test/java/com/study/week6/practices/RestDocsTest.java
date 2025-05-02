package com.study.week6.practices;

import com.study.week6.practices.dto.ProductDTO;
import com.study.week6.practices.entity.Products;
import com.study.week6.practices.repository.ProductsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureRestDocs
@AutoConfigureMockMvc
public class RestDocsTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("상품_생성_API_문서화")
    void productsPostDocs() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setCategory("샘플 카테고리");
        productDTO.setProductName("샘플 상품");
        productDTO.setPrice(9900);
        productDTO.setStock(10);

        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andDo(document("product-post", // 스니펫이 저장될 경로가 된다.
                        preprocessRequest(prettyPrint()), // json 을 보기 편한 방식으로 바꿔준다.
                        preprocessResponse(prettyPrint()),
                        requestFields( // 요청에 필요한 json 필드를 적는다.
                                fieldWithPath("category").description("상품 카테고리"),
                                fieldWithPath("productName").description("상품명"),
                                fieldWithPath("price").description("상품 가격"),
                                fieldWithPath("stock").description("상품 재고")
                        ),
                        responseFields( // 응답으로 들어오는 필드를 전부 적는다.
                                fieldWithPath("category").description("상품 카테고리"),
                                fieldWithPath("productName").description("상품명"),
                                fieldWithPath("price").description("상품 가격"),
                                fieldWithPath("stock").description("상품 재고")
                        )
                ));
    }

    @Test
    @DisplayName("상품_조회_API_문서화")
    void productsGetDocs() throws Exception {
        // 테스트용 더미 데이터 생성
        Products products = new Products();
        products.setCategory("food");
        products.setProductName("cake");
        products.setPrice(6000);
        products.setStock(10);
        products.setDescription("choco-cake");
        productsRepository.save(products);

        mockMvc.perform(get("/api/v1/products/{id}", 1L))
                .andExpect(status().isOk())
                .andDo(document("product-get", // 스니펫이 저장될 경로가 된다.
                        preprocessRequest(prettyPrint()), // json 을 보기 편한 방식으로 바꿔준다.
                        preprocessResponse(prettyPrint()),
                        pathParameters( // 요청에 필요한 파라미터를 적어준다.
                                parameterWithName("id").description("조회할 상품의 id")
                        ),
                        responseFields( // 응답으로 받는 내용을 전부 적는다.
                                fieldWithPath("category").description("상품 카테고리"),
                                fieldWithPath("productName").description("상품명"),
                                fieldWithPath("price").description("상품 가격"),
                                fieldWithPath("stock").description("상품 재고")
                        )
                        ));
    }

}
