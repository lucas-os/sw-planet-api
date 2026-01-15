package com.example.sw_planet_api.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static com.example.sw_planet_api.common.PlanetConstants.PLANET;
import static com.example.sw_planet_api.common.PlanetConstants.INVALID_PLANET;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

//@SpringBootTest(classes = PlanetService.class)
@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {

    //@Autowired
    @InjectMocks
    private PlanetService planetService; //ele é sobre teste entao nao pode criar duble para ele, tem que criar estancia valida

    //@MockitoBean
    @Mock
    private PlanetRepository planetRepository;

    // operacao_estado_retorno
    @Test
    public void createPlanet_WithValidData_ReturnsPlanet(){
        //AAA
        //Arrange - arruma os dados para o teste, coloca stub faz preparação
        when(planetRepository.save(PLANET)).thenReturn(PLANET); //quando pR.save for chamado com PLANET, entao retorne o PLANET (planeta), isso é um stub

        //Act - vai agir vai fazer operacao que quer testar, system under test - sistema sobre teste
        Planet sut = planetService.create(PLANET);

        //Assert - aferir se o sistema sobre teste é o que eu esperava
        assertThat(sut).isEqualTo(PLANET); //planeta retornado pelo serviço tem que ser igual o planeta que mandamos criar
    }

    @Test
    public void createPlanet_WithInvalidData_ThrowsException(){

        when(planetRepository.save(INVALID_PLANET)).thenThrow(RuntimeException.class);

        assertThatThrownBy(() ->  planetService.create(INVALID_PLANET)).isInstanceOf(RuntimeException.class);
    }
}
