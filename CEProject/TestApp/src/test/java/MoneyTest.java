import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collection;
import java.util.Currency;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

@RunWith(org.junit.experimental.runners.Enclosed.class)
public class MoneyTest {

	public static class MoneySingleTest {
		/*
		 * Test instantiation of Money with null value
		 */
		@Test(expected = NullPointerException.class)
		public void testInstantiationWithNull() {
			Money.dollars(null);
		}

		/*
		 * Test money value rounding
		 */
		@Test
		public void testInstantiationWithNegativePrecision() {
			Money m = Money.dollars(new BigDecimal("-90.498"));
			BigDecimal expected = (new BigDecimal(-90.5)).setScale(2, RoundingMode.HALF_EVEN);
			assertEquals(expected, m.getAmount());
			assertEquals(Currency.getInstance("USD"), m.getCurrency());

			Money m2 = Money.dollars(new BigDecimal("90.498"));
			BigDecimal expected2 = (new BigDecimal(90.5)).setScale(2, RoundingMode.HALF_EVEN);
			assertEquals(expected2, m2.getAmount());
			assertEquals(Currency.getInstance("USD"), m2.getCurrency());

		}
	}

	@RunWith(Parameterized.class)
	public static class MoneyParameterizedTests {
		private int expected;
		private BigDecimal value1;
		private BigDecimal value2;

		@SuppressWarnings("rawtypes")
		@Parameters
		public static Collection inputParameters() {
			return Arrays.asList(new Object[][] { { new BigDecimal("100.49"), new BigDecimal("100.50"), -1 },
					{ new BigDecimal("100"), new BigDecimal("101"), -1 },
					{ new BigDecimal("-100"), new BigDecimal("-99.8"), -1 },
					{ new BigDecimal("-0.00"), new BigDecimal("0.00"), 0 },
					{ new BigDecimal("0.00"), new BigDecimal("0.00"), 0 },
					{ new BigDecimal("0.001"), new BigDecimal("0.00"), 0 },
					{ new BigDecimal("100.503"), new BigDecimal("100.50"), 0 },
					{ new BigDecimal("1.07"), new BigDecimal("1.07"), 0 },
					{ new BigDecimal("1.07"), new BigDecimal("1.06"), 1 },
					{ new BigDecimal("-1.06"), new BigDecimal("-1.07"), 1 }, });
		}

		public MoneyParameterizedTests(BigDecimal value1, BigDecimal value2, int expected) {
			this.value1 = value1;
			this.value2 = value2;
			this.expected = expected;
		}

		/*
		 * Test money value comparison
		 */
		@Test
		public void moneyEquality() {
			Money m1 = Money.dollars(value1);
			Money m2 = Money.dollars(value2);
			assertEquals(expected, m1.compareTo(m2));
		}
	}
}