package brproblems;

public class CurrencyArbitrage {
	
	static int arbitrage(String quote){
		String[] splitQuote = quote.split(" ");
		double usd = ((100000.00 / (Double.parseDouble(splitQuote[0]))) / Double.parseDouble(splitQuote[1])) / Double.parseDouble(splitQuote[2]);
		int a = (int)(usd - 100000.00);
		if(a > 0)
			return a;
		return 0;
	}
	static int[] arbitrage(String[] quotes){
		int[] output = new int[quotes.length];
		int index = 0;
		for(String q : quotes){
			output[index++] = arbitrage(q);
		}
		return output;
	}
	
	public static void main(String[] arg){
		System.out.println("Currency Arbitrage");
		String[] quotes = new String[] {"0.5795 0.5173 1.2223","0.7931 0.5663 1.0012",
				"1.4318 1.4705 1.3352", "1.3413 0.8072 1.0355", "1.2767 1.1696 0.5423",
				"0.6903 0.7520 0.5195", "1.3832 0.7238 1.4037", "0.5739 1.4304 0.9906",
				"0.5648 1.0071 1.7581", "0.8033 0.9961 1.2498"
			};
		int[] output = arbitrage(quotes);
		for(int out : output){
			System.out.println(out);
		}
	}
}
