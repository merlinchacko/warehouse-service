package com.spring.cloud.warehouseservice.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.spring.cloud.warehouseservice.domain.entity.Box;
import com.spring.cloud.warehouseservice.domain.entity.Location;
import com.spring.cloud.warehouseservice.domain.entity.Product;
import com.spring.cloud.warehouseservice.domain.model.ProductType;
import com.spring.cloud.warehouseservice.service.BoxService;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@WithMockUser(username = "admin", roles = {"WAREHOUSE_ADMIN"})
class BoxControllerTest
{

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<Box> json;

    @MockBean
    private BoxService boxService;

    @Test
    void createBoxTest() throws Exception
    {
        Box box = getBox();
        given(boxService.save(any())).willReturn(box);
        mvc.perform(
                post(new URI("/warehouse/box"))
                    .content(json.write(box).getJson())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void getBoxesTest() throws Exception
    {
        Box box = getBox();
        given(boxService.getAll()).willReturn(Collections.singletonList(box));
        mvc.perform(
                get(new URI("/warehouse/box"))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].id", is(box.getId().intValue())))
            .andExpect(jsonPath("$[0].description", is(box.getDescription())))
            .andExpect(jsonPath("$[0].product.name", is(box.getProduct().getName())));

        verify(boxService, times(1)).getAll();

    }

    @Test
    void searchBoxTest() throws Exception
    {
        Box box = getBox();
        given(boxService.getBoxBySearchQuery(any())).willReturn(Collections.singletonList(box));
        mvc.perform(
                get(new URI("/warehouse/box/search"))
                    .param("query","wo")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id", is(box.getId().intValue())))
            .andExpect(jsonPath("$[0].description", is(box.getDescription())))
            .andExpect(jsonPath("$[0].product.name", is(box.getProduct().getName())));

        verify(boxService, times(1)).getBoxBySearchQuery("wo");
    }

    private Box getBox()
    {
        Box box = new Box();

        box.setId(1L);
        box.setLocation(new Location(1L, "Loc1"));
        box.setDescription("box1");
        box.setProduct(new Product("wood", ProductType.RAW_MATERIALS));

        return box;
    }
}