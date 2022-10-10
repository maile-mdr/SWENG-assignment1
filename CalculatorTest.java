import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import org.junit.Assert; 
import org.junit.Before;

public class CalculatorTest {
	private Calculator calc1;

	@Before
	public void setUp() {
		//Arrange
		calc1 = new Calculator();
	}

	@Test
	void checkValidInput() { 
		assertEquals(true, calc1.checkValidInput("4+8*7") );
	}
	@Test
	void checkResult() { 
		assertEquals(10, calc1.computeEquation("1+ 3 +4 + 2"));
		assertEquals(-8, calc1.computeEquation("1 - 3 - 4- 2"));
		assertEquals(15, calc1.computeEquation("1+ 3 *4 + 2"));
		assertEquals(6, calc1.computeEquation("1- 3 +4 * 2"));
		
	}

}
