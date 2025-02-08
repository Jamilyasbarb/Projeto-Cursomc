package com.jamily.projetocursomc.repositories;

import com.jamily.projetocursomc.domain.Categoria;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest//para indicar que se trata de uma classe que testa métodos jpa (repositories)
@ActiveProfiles("test")//para pegar o properties de test, e não o de prod ou de dev
class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("Deve retornar sucesso ao buscar categoria pelo nome que existe") //descricao do teste (o que ele faz)
    void findByNomeSuccess(){
        String name = "Material Escolar";
        Categoria categoria = new Categoria(null,name);
        this.createCategory(categoria);
        Optional<Categoria> result = categoriaRepository.findByNome(name);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Deve falhar ao buscar categoria pelo nome que não existe") //descricao do teste (o que ele faz)
    void findByNomeFail(){
        String name = "Material Escolar";
        Optional<Categoria> result = categoriaRepository.findByNome(name);

        assertThat(result.isEmpty()).isTrue();
    }

    private Categoria createCategory(Categoria newCategoria){
        Categoria categoria = new Categoria(newCategoria.getId(), newCategoria.getNome());
        this.entityManager.persist(categoria);
        return categoria;
    }
}