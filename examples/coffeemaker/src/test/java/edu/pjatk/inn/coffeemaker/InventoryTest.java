package edu.pjatk.inn.coffeemaker;


import edu.pjatk.inn.coffeemaker.impl.CoffeeMaker;
import edu.pjatk.inn.coffeemaker.impl.Inventory;
import edu.pjatk.inn.coffeemaker.impl.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sorcer.test.ProjectContext;
import org.sorcer.test.SorcerTestRunner;
import sorcer.service.ContextException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SorcerTestRunner.class)
@ProjectContext("examples/coffeemaker")
public class InventoryTest {
    private final static Logger logger = LoggerFactory.getLogger(CoffeeMakerTest.class);

    private CoffeeMaker coffeeMaker;
    private Inventory inventory;
    private Recipe espresso, mocha, macchiato, americano;

    @Before
    public void setUp() throws ContextException {
        coffeeMaker = new CoffeeMaker();
        inventory = coffeeMaker.checkInventory();

        espresso = new Recipe();
        espresso.setName("espresso");
        espresso.setPrice(50);
        espresso.setAmtCoffee(6);
        espresso.setAmtMilk(1);
        espresso.setAmtSugar(1);
        espresso.setAmtChocolate(0);

        mocha = new Recipe();
        mocha.setName("mocha");
        mocha.setPrice(100);
        mocha.setAmtCoffee(8);
        mocha.setAmtMilk(1);
        mocha.setAmtSugar(1);
        mocha.setAmtChocolate(2);

        macchiato = new Recipe();
        macchiato.setName("macchiato");
        macchiato.setPrice(40);
        macchiato.setAmtCoffee(7);
        macchiato.setAmtMilk(1);
        macchiato.setAmtSugar(2);
        macchiato.setAmtChocolate(0);

        americano = new Recipe();
        americano.setName("americano");
        americano.setPrice(40);
        americano.setAmtCoffee(7);
        americano.setAmtMilk(1);
        americano.setAmtSugar(2);
        americano.setAmtChocolate(0);
    }

}