package com.epicode.U5D2;

import com.epicode.U5D2.entities.Order;
import com.epicode.U5D2.entities.Pizza;
import com.epicode.U5D2.entities.Table;
import com.epicode.U5D2.entities.Topping;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Esercizio30102023ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void calculatePizzaPrice(){
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(U5D2Application.class);
		Pizza margherita = (Pizza) ctx.getBean("pizza_margherita");
		assertEquals(margherita.getPrice(),4.99 );

	}

	@Test
	void calculatePizzaCalories(){
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(U5D2Application.class);
		Pizza hawaiana = (Pizza) ctx.getBean("hawaiian_pizza");
		assertEquals(hawaiana.getCalories(), 851);
	}

	void addItem(){
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(U5D2Application.class);
		Table table = (Table) ctx.getBean("Tavolo1");
		Pizza margherita = (Pizza) ctx.getBean("pizza_margherita");
		Order order = new Order(1, table);
		order.addItem(margherita);
		order.addItem(margherita);
		order.addItem(margherita);
		assertEquals(order.getOrderedProducts().size(), 3);
	}


	@ParameterizedTest
	@CsvSource({"Tavolo1, pizza_margherita, 6.99", "Tavolo3, hawaiian_pizza, 8.77"})
	void calculateTotal(String tavolo, String pizza, double cost){
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(U5D2Application.class);
		Table table = (Table) ctx.getBean(tavolo);
		Pizza pizzaTipo = (Pizza) ctx.getBean(pizza);
		Order order = new Order(1, table);
		order.addItem(pizzaTipo);
		assertEquals(order.getTotal(),cost);

	}

	@Test
	void correctToppingsList(){
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(U5D2Application.class);
		List<Topping> toppings = (List<Topping>) ctx.getBean("toppings");
		assertEquals(toppings.size(), 5);
	}

}
