package com.spring.cloud.warehouseservice.service;

import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.spring.cloud.warehouseservice.domain.entity.Box;
import com.spring.cloud.warehouseservice.domain.entity.Location;
import com.spring.cloud.warehouseservice.domain.entity.Product;
import com.spring.cloud.warehouseservice.domain.model.BoxRequest;
import com.spring.cloud.warehouseservice.domain.model.ProductType;
import com.spring.cloud.warehouseservice.exception.IdNotFoundException;
import com.spring.cloud.warehouseservice.repository.BoxRepository;
import com.spring.cloud.warehouseservice.repository.LocationRepository;
import com.spring.cloud.warehouseservice.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class BoxServiceTest
{
    private BoxService boxService;
    @Mock
    private BoxRepository boxRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private LocationRepository locationRepository;

    @BeforeEach
    void init()
    {
        boxService = new BoxService(boxRepository, locationRepository, productRepository);
    }

    @Test
    void shouldSaveBoxTest()
    {
        Product product = new Product(1L,"abc", ProductType.FINISHED_GOODS);
        Location location = new Location(1L, "Loc1");
        Box box = new Box(1L, "box1", product, location);

        when(productRepository.findById(any())).thenReturn(Optional.of(product));
        when(locationRepository.findById(any())).thenReturn(Optional.of(location));
        when(boxRepository.save(any())).thenReturn(box);

        Box result = boxService.save(new BoxRequest("box1", 1L, 1L));

        assertEquals("abc", result.getProduct().getName());
        assertEquals("Loc1", result.getLocation().getName());

        verify(boxRepository, times(1)).save(any(Box.class));
    }

    @Test
    void shouldThrowExceptionForInvalidProductTest()
    {
        Location location = new Location(1L, "Loc1");
        when(locationRepository.findById(any())).thenReturn(Optional.of(location));

        final IdNotFoundException caughtExceptionOnFetch = catchThrowableOfType(() -> boxService.save(new BoxRequest("box1", 0L, 1L)), IdNotFoundException.class);

        assertEquals("ProductId - 0 not found", caughtExceptionOnFetch.getMessage());
    }

    @Test
    void shouldThrowExceptionForInvalidLocationTest()
    {
        final IdNotFoundException caughtExceptionOnFetch = catchThrowableOfType(() -> boxService.save(new BoxRequest("box1", 1L, 0L)), IdNotFoundException.class);

        assertEquals("LocationId - 0 not found", caughtExceptionOnFetch.getMessage());
    }

    @Test
    void shouldReturnMatchingBoxForSearchQueryTest()
    {
        Box box = getBox();
        when(boxRepository.findBySearchQuery(any())).thenReturn(Collections.singletonList(box));
        List<Box> result = boxService.getBoxBySearchQuery("param");

        assertEquals(1, result.size());
        assertEquals(result.get(0).getProduct().getName(), box.getProduct().getName());
        assertEquals(result.get(0).getLocation().getName(), box.getLocation().getName());
    }

    @Test
    void shouldReturnAllBoxesTest()
    {
        Box box = getBox();
        when(boxRepository.findAll()).thenReturn(Collections.singletonList(box));
        List<Box> result = boxService.getAll();

        assertEquals(1, result.size());
        assertEquals(result.get(0).getProduct().getName(), box.getProduct().getName());
        assertEquals(result.get(0).getLocation().getName(), box.getLocation().getName());
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
