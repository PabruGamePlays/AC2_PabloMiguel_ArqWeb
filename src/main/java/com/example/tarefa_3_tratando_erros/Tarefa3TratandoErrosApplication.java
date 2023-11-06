package com.example.tarefa_3_tratando_erros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.tarefa_3_tratando_erros.models.CategoriaProduto;
import com.example.tarefa_3_tratando_erros.models.Produto;
import com.example.tarefa_3_tratando_erros.repository.CategoriaProdutoRepository;
import com.example.tarefa_3_tratando_erros.repository.ProdutoRepository;

@SpringBootApplication
public class Tarefa3TratandoErrosApplication {

	@Bean
	public CommandLineRunner init(
			@Autowired ProdutoRepository produtoRepository,
			@Autowired CategoriaProdutoRepository categoriaProdutoRepository) {
		return args -> {
			// INSERINDO PRODUTOS

			// PRODUTOS DE VESTUARIOS
			Produto p1 = new Produto(0, "Chinelo", 56.90);
			Produto p2 = new Produto(0, "Meia", 33.89);
			Produto p3 = new Produto(0, "Camiseta", 60.85);

			// PRODUTOS DE GAMES
			Produto p4 = new Produto(0, "PS5", 3700.0);
			Produto p5 = new Produto(0, "XBOX X", 3800.0);
			Produto p6 = new Produto(0, "XBOX S", 2800.0);

			System.out.println("Inserir Produto \n");
			System.out.println("Inserindo Vestuarios");
			// PRODUTO VESTUARIOS
			p1 = produtoRepository.save(p1);
			p2 = produtoRepository.save(p2);
			p3 = produtoRepository.save(p3);

			System.out.println("Inserindo Consoles");
			// PRODUTO GAMES
			p4 = produtoRepository.save(p4);
			p5 = produtoRepository.save(p5);
			p6 = produtoRepository.save(p6);

			System.out.println("Selecionar Preço acima do digitado");
			List<Produto> listaProdutos = produtoRepository.findByPrecoProdutos(2000.0);
			listaProdutos.forEach(System.out::println);

			// produtoRepository.delete(p7);
			// listaProdutos = produtoRepository.findAll();

			System.out.println("Selecionar igual ou abaixo do digitado");
			listaProdutos = produtoRepository.findByPreco(3700.0);
			listaProdutos.forEach(System.out::println);

			System.out.println("Selecionar todos os produtos por caracteres digitados");
			listaProdutos = produtoRepository.findByNomeLike("%L%");
			listaProdutos.forEach(System.out::println);

			System.out.println("Selecionar todos");
			listaProdutos = produtoRepository.findAll();
			listaProdutos.forEach(System.out::println);

			System.out.println("Adicionar Categoria Produto");
			CategoriaProduto cp1 = new CategoriaProduto(0, "Vestuarios");
			CategoriaProduto cp2 = new CategoriaProduto(0, "Consoles");
			CategoriaProduto cp3 = new CategoriaProduto(0, "Carros");

			cp1 = categoriaProdutoRepository.save(cp1);
			cp2 = categoriaProdutoRepository.save(cp2);
			cp3 = categoriaProdutoRepository.save(cp3);

			// Adicionando categoria Plantas
			listaProdutos.get(0).setCategoriaProduto(cp1);
			produtoRepository.save(listaProdutos.get(0));

			listaProdutos.get(1).setCategoriaProduto(cp1);
			produtoRepository.save(listaProdutos.get(1));

			listaProdutos.get(2).setCategoriaProduto(cp1);
			produtoRepository.save(listaProdutos.get(2));

			// // // Adicionando categoria Animais
			listaProdutos.get(3).setCategoriaProduto(cp2);
			produtoRepository.save(listaProdutos.get(3));

			listaProdutos.get(4).setCategoriaProduto(cp2);
			produtoRepository.save(listaProdutos.get(4));

			listaProdutos.get(5).setCategoriaProduto(cp2);
			produtoRepository.save(listaProdutos.get(5));

			System.out.println("Selecionar todas as categorias por caracteres digitados");
			List<CategoriaProduto> listaCategorias = categoriaProdutoRepository.findByNameStartingWith("A");
			listaCategorias.forEach(System.out::println);

			System.out.println("Produtos relacionado a uma determinada categoria");
			CategoriaProduto cp = categoriaProdutoRepository.findCategoriaProdutoFetchProduto(2);
			System.out.println(cp.getProdutos().toString());

		};
	};

	public static void main(String[] args) {
		SpringApplication.run(Tarefa3TratandoErrosApplication.class, args);
	}

}
