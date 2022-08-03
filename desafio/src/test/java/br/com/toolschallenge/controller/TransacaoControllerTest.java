package br.com.toolschallenge.controller;

import br.com.toolschallenge.dto.DescricaoTransacaoDto;
import br.com.toolschallenge.dto.FormaPagamentoDto;
import br.com.toolschallenge.dto.TransacaoDto;
import br.com.toolschallenge.enums.TipoFormaPagamento;
import br.com.toolschallenge.enums.TipoStatusTransacao;
import br.com.toolschallenge.repository.TransacaoRepository;
import br.com.toolschallenge.service.TransacaoService;
import br.com.toolschallenge.util.gsonAdapter.LocalDateTimeAdapter;
import br.com.toolschallenge.util.jsonDeserializer.TransacaoDtoDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@EnableAutoConfiguration
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
class TransacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransacaoService service;

    @Autowired
    private TransacaoRepository repository;

    private TypeToken transacaoDtoType = new TypeToken<List<TransacaoDto>>() {};

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(TransacaoDto.class, new TransacaoDtoDeserializer())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter().nullSafe()).create();

    @AfterEach
    public void afterEach() {
        repository.deleteAll();
    }

    private final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    @Test
    @DisplayName(value = "Deve mostrar a lista com todos os pagamentos")
    void deveMostrarTodosOsContatos() throws Exception {
        service.processarTransacao(new TransacaoDto("0", "7894********2417", new DescricaoTransacaoDto(new BigDecimal("150.00"), LocalDateTime.now(), "Estabelecimento", null, null, TipoStatusTransacao.AUTORIZADO), new FormaPagamentoDto(TipoFormaPagamento.AVISTA, 1L)));
        service.processarTransacao(new TransacaoDto("0", "7894********2415", new DescricaoTransacaoDto(new BigDecimal("150.00"), LocalDateTime.now(), "Estabelecimento", null, null, TipoStatusTransacao.NEGADO), new FormaPagamentoDto(TipoFormaPagamento.AVISTA, 1L)));
        service.processarTransacao(new TransacaoDto("0", "7894********2415", new DescricaoTransacaoDto(new BigDecimal("150.00"), LocalDateTime.now(), "Estabelecimento", null, null, TipoStatusTransacao.CANCELADO), new FormaPagamentoDto(TipoFormaPagamento.AVISTA, 1L)));

        MvcResult mvcResult = mockMvc.perform(get("/transacao/todosPagamento").accept(APPLICATION_JSON_UTF8).contentType(APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<TransacaoDto> resultado = gson.fromJson(mvcResult.getResponse().getContentAsString(), transacaoDtoType.getType());

        Assertions.assertFalse(resultado.isEmpty());
        Assertions.assertEquals(2, resultado.size());
    }

    @Test
    @DisplayName(value = "Deve retornar erro ao tentar consultar lista se nao houver informacao")
    void deveInformarErroNaSolicitacaoTodosPagamento() throws Exception {
        mockMvc.perform(get("/transacao/todosPagamento").accept(APPLICATION_JSON_UTF8).contentType(APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
    }

    @Test
    @DisplayName(value = "Deve retornar o pagamento com status autorizado consultado pelo seu id")
    void buscarPagamentoPorIdAutorizado() throws Exception {
        String id = service.processarTransacao(new TransacaoDto("0", "7894********2417", new DescricaoTransacaoDto(new BigDecimal("150.00"), LocalDateTime.now(), "Estabelecimento", null, null, null), new FormaPagamentoDto(TipoFormaPagamento.AVISTA, 1L))).id();

        MvcResult mvcResult = mockMvc.perform(get("/transacao/pagamento/{id}", id).accept(APPLICATION_JSON_UTF8).contentType(APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        TransacaoDto resultado = gson.fromJson(mvcResult.getResponse().getContentAsString(), TransacaoDto.class);

        Assertions.assertEquals(id, resultado.id());
        Assertions.assertEquals(TipoStatusTransacao.AUTORIZADO, resultado.descricao().status());
    }

    @Test
    @DisplayName(value = "Deve retornar o pagamento com status negado consultado pelo seu id")
    void buscarPagamentoPorIdNegado() throws Exception {
        String id = service.processarTransacao(new TransacaoDto("0", "7894********2417", new DescricaoTransacaoDto(new BigDecimal("150.00"), LocalDateTime.now(), "Estabelecimento", null, null, TipoStatusTransacao.NEGADO), new FormaPagamentoDto(TipoFormaPagamento.AVISTA, 1L))).id();

        MvcResult mvcResult = mockMvc.perform(get("/transacao/pagamento/{id}", id).accept(APPLICATION_JSON_UTF8).contentType(APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        TransacaoDto resultado = gson.fromJson(mvcResult.getResponse().getContentAsString(), TransacaoDto.class);

        Assertions.assertEquals(id, resultado.id());
        Assertions.assertEquals(TipoStatusTransacao.NEGADO, resultado.descricao().status());
    }

    @Test
    @DisplayName(value = "Deve retornar erro ao tentar consultado o pagamento ao nao encontrar a informacao")
    void deveInformarErroBuscarPagamentoPorId() throws Exception {
        mockMvc.perform(get("/transacao/pagamento/{id}", "8").accept(APPLICATION_JSON_UTF8).contentType(APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
    }

    @Test
    @DisplayName(value = "Deve retornar estorno conforme o ID informado")
    void deveBuscarUmEstornoPorId() throws Exception {
        String id = service.processarTransacao(new TransacaoDto("0", "7894********2417", new DescricaoTransacaoDto(new BigDecimal("150.00"), LocalDateTime.now(), "Estabelecimento", null, null, TipoStatusTransacao.CANCELADO), new FormaPagamentoDto(TipoFormaPagamento.AVISTA, 1L))).id();

        MvcResult mvcResult = mockMvc.perform(get("/transacao/estorno/{id}", id).accept(APPLICATION_JSON_UTF8).contentType(APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        TransacaoDto resultado = gson.fromJson(mvcResult.getResponse().getContentAsString(), TransacaoDto.class);

        Assertions.assertEquals(id, resultado.id());
        Assertions.assertEquals(TipoStatusTransacao.CANCELADO, resultado.descricao().status());
    }

    @Test
    @DisplayName(value = "Deve retornar erro ao tentar estornor e nao encontrar a informacao")
    void deveInformarErroNaSolicitacaoEstorno() throws Exception {
        mockMvc.perform(get("/transacao/estorno/{id}", "4585").accept(APPLICATION_JSON_UTF8).contentType(APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
    }

    @Test
    @DisplayName(value = "Deve salvar um novo pagamento com status Autorizado")
    void deveSalvarUmPagamento() throws Exception {
        TransacaoDto dto = new TransacaoDto("0", "7894********2417", new DescricaoTransacaoDto(new BigDecimal("150.00"), LocalDateTime.now(), "Estabelecimento", null, null, null), new FormaPagamentoDto(TipoFormaPagamento.AVISTA, 1L));

        MvcResult mvcResult = mockMvc.perform(post("/transacao/pagamento").content(gson.toJson(dto)).accept(APPLICATION_JSON_UTF8).contentType(APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        TransacaoDto resultado = gson.fromJson(mvcResult.getResponse().getContentAsString(), TransacaoDto.class);

        Assertions.assertNotNull(resultado.id());
        Assertions.assertNotNull(resultado.descricao().status());
        Assertions.assertNotNull(resultado.descricao().nsu());
        Assertions.assertNotNull(resultado.descricao().codigoAutorizacao());
        Assertions.assertEquals(TipoStatusTransacao.AUTORIZADO, resultado.descricao().status());
        Assertions.assertEquals(dto.descricao().estabelecimento(), resultado.descricao().estabelecimento());
        Assertions.assertEquals(dto.formaPagamento().tipo(), resultado.formaPagamento().tipo());
    }

}
