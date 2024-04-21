package com.example.nanarback;

import com.example.nanarback.exceptions.ExceptionHandlingAdvice;
import com.example.nanarback.exceptions.ResourceAlreadyExistsException;
import com.example.nanarback.exceptions.ResourceNotFoundException;
import com.example.nanarback.nanar.Nanar;
import com.example.nanarback.nanar.NanarController;
import com.example.nanarback.nanar.NanarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class NanarControllerTest {


    @ExtendWith(SpringExtension.class)
    @WebMvcTest
    @ContextConfiguration(classes = NanarController.class)
    @Import(ExceptionHandlingAdvice.class)
    public class ExemplaireControllerTest {

        @Autowired
        MockMvc mockMvc;

        @MockBean
        NanarService nanarService;

        private List<Nanar> nanars;

        @BeforeEach
        void setUp() {
            nanars = new ArrayList<>() {{
                add(new Nanar(1, "nanar1", 1, 5));
                add(new Nanar(2, "nanar2", 2, 4));
                add(new Nanar(3, "nanar3", 5, 5));
                add(new Nanar(4, "nanar4", 3, 2));
                add(new Nanar(5, "nanar5", 1, 1));
            }};
            when(nanarService.getAll()).thenReturn(nanars);
            when(nanarService.getById(3L)).thenReturn(nanars.get(2));
            when(nanarService.getById(49L)).thenThrow(ResourceNotFoundException.class);
        }

        @Test
        void whenGettingAll_shouldGet5_andBe200() throws Exception {
            mockMvc.perform(get("/nanars")
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isOk()
            ).andExpect(jsonPath("$", hasSize(5))
            ).andDo(print());
        }

        @Test
        void whenGettingUnexistingId_should404() throws Exception {
            mockMvc.perform(get("/nanars/49")
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isNotFound()
            ).andDo(print());
        }

        @Test
        void whenCreatingNew_shouldReturnLink_andShouldBeStatusCreated() throws Exception {
            Nanar nanar = new Nanar();
            ArgumentCaptor<Nanar> nanar_received = ArgumentCaptor.forClass(Nanar.class);
            when(nanarService.create(any())).thenReturn(nanar);

            mockMvc.perform(post("/nanars")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(nanar))
            ).andExpect(status().isCreated()
            ).andExpect(header().string("Location", "/nanars/"+nanar.getIdNanar())
            ).andDo(print());

            verify(nanarService).create(nanar_received.capture());
            assertEquals(nanar, nanar_received.getValue());
        }

        @Test
        void whenCreatingWithExistingId_should404() throws Exception {
            when(nanarService.create(any())).thenThrow(ResourceAlreadyExistsException.class);
            mockMvc.perform(post("/nanars")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(this.nanars.get(2)))
            ).andExpect(status().isConflict()
            ).andDo(print());
        }

        @Test
        void whenUpdating_shouldReceiveUserToUpdate_andReturnNoContent() throws Exception {
            Nanar initial_nanar = nanars.get(1);
            Nanar updated_nanar = new Nanar();
            ArgumentCaptor<Nanar> nanar_received = ArgumentCaptor.forClass(Nanar.class);

            mockMvc.perform(put("/nanars/"+initial_nanar.getIdNanar())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(updated_nanar))
            ).andExpect(status().isNoContent());

            verify(nanarService).update(anyLong(), nanar_received.capture());
            assertEquals(updated_nanar, nanar_received.getValue());
        }


    }
}
