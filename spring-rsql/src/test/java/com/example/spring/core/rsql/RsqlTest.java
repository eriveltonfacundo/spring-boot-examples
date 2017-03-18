package com.example.spring.core.rsql;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIn.isIn;
import static org.hamcrest.core.IsNot.not;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.config.persistence.PersistenceJPAConfig;
import com.example.spring.model.Product;
import com.example.spring.repository.ProductRepository;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;

@Transactional
@RunWith(SpringRunner.class)
@DataJpaTest(showSql = false)
@ContextConfiguration(classes = { PersistenceJPAConfig.class })
public class RsqlTest {

	@Autowired
	private ProductRepository productRepository;

	private Product gol;
	private Product palio;

	@Before
	public void setUp() {
		gol = Product.builder().description("GOL").price(BigDecimal.TEN).build();
		productRepository.save(gol);
		palio = Product.builder().description("PALIO").price(BigDecimal.ONE).build();
		productRepository.save(palio);
	}

	@Test
	public void givenDescription_whenGettingListOfProduct_thenCorrect() {
		Node rootNode = new RSQLParser().parse("description == GOL , description == PALIO ");
		Specification<Product> spec = rootNode.accept(new CustomRsqlVisitor<Product>());
		List<Product> products = productRepository.findAll(spec);
		assertThat(gol, isIn(products));
		assertThat(palio, isIn(products));
	}
	
	@Test
	public void givenDescriptionStart_whenGettingListOfProduct_thenCorrect() {
		Node rootNode = new RSQLParser().parse("description == G*");
		Specification<Product> spec = rootNode.accept(new CustomRsqlVisitor<Product>());
		List<Product> products = productRepository.findAll(spec);
		assertThat(gol, isIn(products));
		assertThat(palio, not(isIn(products)));
	}
	
	@Test
	public void givenDescriptionNotEq_whenGettingListOfProduct_thenCorrect() {
		Node rootNode = new RSQLParser().parse("description != GOL");
		Specification<Product> spec = rootNode.accept(new CustomRsqlVisitor<Product>());
		List<Product> products = productRepository.findAll(spec);
		assertThat(palio, isIn(products));
		assertThat(gol, not(isIn(products)));
	}
	
	@Test
	public void givenPriceGT_whenGettingListOfProduct_thenCorrect() {
		Node rootNode = new RSQLParser().parse("price > 1");
		Specification<Product> spec = rootNode.accept(new CustomRsqlVisitor<Product>());
		List<Product> products = productRepository.findAll(spec);
		assertThat(gol, isIn(products));
		assertThat(palio, not(isIn(products)));
	}
	@Test
	public void givenPriceLT_whenGettingListOfProduct_thenCorrect() {
		Node rootNode = new RSQLParser().parse("price < 10");
		Specification<Product> spec = rootNode.accept(new CustomRsqlVisitor<Product>());
		List<Product> products = productRepository.findAll(spec);
		assertThat(palio, isIn(products));
		assertThat(gol, not(isIn(products)));
	}

}
