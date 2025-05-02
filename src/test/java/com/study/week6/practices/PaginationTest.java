package com.study.week6.practices;

import com.study.week6.practices.dto.CursorPageDTO;
import com.study.week6.practices.entity.Products;
import com.study.week6.practices.repository.ProductsRepository;
import com.study.week6.practices.service.ProductsServiceImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PaginationTest {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ProductsServiceImpl productsService;

    @BeforeEach
    void setUp() {
        // 더미 데이터 50개 삽입
        for (int i = 1; i <= 50; i++) {
            Products products = new Products();
            products.setProductName("products" + i);
            productsRepository.save(products);
        }
    }

    @Test
    @DisplayName("OFFSET 기반 페이지네이션 테스트")
    void getOffsetPagesTest() {
        int page = 1; // 2번째 페이지에서
        int size = 10; // 10개 데이터를 가져와야 한다

        Page<Products> result = productsService.getOffsetPages(page, size);

        assertEquals(10, result.getNumberOfElements()); // 페이징한 데이터 수
        assertEquals(1, result.getNumber()); // 현재 페이지 번호
        assertEquals(5, result.getTotalPages()); // 총 페이지 수
        assertEquals(50, result.getTotalElements()); // 총 항목 수

        // 내림차순 정렬 확인
        List<Products> productsPage = result.getContent();
        assertTrue(productsPage.get(0).getId() > productsPage.get(1).getId());
    }

    @Test
    @DisplayName("CURSOR 기반 페이지네이션 테스트")
    void getCursorPagesTest() {
        Long cursor = 40L; // 2번째 페이지에서
        int size = 10; // 10개 데이터를 가져와야 한다

        CursorPageDTO<Products> result = productsService.getCursorPages(cursor, size);

        assertEquals(10, result.getContent().size()); // 페이징한 데이터 수
        assertEquals(30L, result.getNextCursor()); // nextCursor : 40 - 10 = 30L
    }

}
