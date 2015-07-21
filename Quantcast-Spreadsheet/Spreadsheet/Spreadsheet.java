package Spreadsheet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

class Cell
{
	public enum Status
	{
		INITIALIZED,
		INPROGRESS,
		PROCESSED
	}
	
	private List<Token> expressionTokens = null;
	public Double value;
	public Status status;
	public String expString;
	int index;
	public Cell(String expString, int index)
	{
		this.expString = expString;
		this.index = index;
		expressionTokens = Token.tokenize(expString, " ");
		status = Status.INITIALIZED;
		value = null;
	}
	
	public List<Token> getExpressionTokens()
	{
		return expressionTokens;
	}
	
	@Override
	public String toString()
	{
		return String.format("%.5f", value);
	}
	
	public String print()
	{
		return "["+index+"]  " + expString;
	}
}

public class Spreadsheet 
{
	private Cell[] cells;
	private int rows, cols, totalcells;
	private Queue<Cell> topologicalCellOrdering;
	private Spreadsheet(String line)
	{
		topologicalCellOrdering = new LinkedList<Cell>();
		
		try
		{
			String[] splitLine = line.trim().split(" ");
			
			if(splitLine.length != 2)
				throw new RuntimeException("Invalid first line - expected two integers separated by space");
			
			//Width
			cols = Integer.parseInt(splitLine[0]);
			if(cols <= 0)
				throw new RuntimeException("Invalid first line - number of cols should be greater than 0");
			//Height
			rows = Integer.parseInt(splitLine[1]);
			if(rows > 26 || rows <=0)
				throw new RuntimeException("Invalid first line - number of rows should be in range [0,26]");
			
			totalcells = rows * cols;
			cells = new Cell[totalcells];
		}
		catch(Exception ex)
		{
			throw new RuntimeException("Spreadsheet Initialization failed: " + ex.getMessage());
		}
	}
	private void addCell(int index, String line)
	{
		cells[index] = new Cell(line, index);
	}
	private void readCells(BufferedReader br) throws IOException
	{
		String line = null;
		int lineIndex = 0;
		while (lineIndex < totalcells)
	    {
			line = br.readLine();
			if(null == line)
				throw new RuntimeException("Not enough lines in input file, expected atleast " + (totalcells + 1) + " lines");;
			
			//System.out.println(line);
			addCell(lineIndex, line);
			++lineIndex;
	    }
	}
	
	private void evaluate()
	{	
		for(Cell cell : cells)
			if(cell.status != Cell.Status.PROCESSED)
				evaluate(cell);
	}
	
	private void topologicalSort()
	{
		for(Cell cell : cells)
			if(cell.status != Cell.Status.PROCESSED)
				topologicalSort(cell);
		
	}
	
	private void evaluate(Cell cellInAction)
	{
		cellInAction.status = Cell.Status.INPROGRESS;
		Stack<Double> valuationStack = new Stack<Double>(); 
		for(Token token : cellInAction.getExpressionTokens())
		{
			if(token.getType() == Token.TokenType.CELL)
			{
				int adjacentIndex = getCellIndex(token.getValue());
				Cell adjacentCell = cells[adjacentIndex];
				if(adjacentCell.status == Cell.Status.INPROGRESS)
					throw new RuntimeException("Cycle found between cells while evaluating: " + token.getValue());
				if(adjacentCell.status == Cell.Status.INITIALIZED)
					evaluate(adjacentCell);
				valuationStack.push(adjacentCell.value);
			}
			else if(token.getType() == Token.TokenType.INTEGER)
				valuationStack.push(Double.parseDouble(token.getValue()));
			else if(token.getType() == Token.TokenType.OPERATOR)
			{
				Double opnd2 = valuationStack.pop();
				Double opnd1 = valuationStack.pop();
				valuationStack.push(ExpressionUtil.evaluate(opnd1, opnd2, token.getValue()));
			}
			else
				throw new RuntimeException("This should not happen");
		}
		
		//Here stack should have ONLY one element, else throw exception
		//Assign value to cell
		cellInAction.value = valuationStack.pop();
		cellInAction.status = Cell.Status.PROCESSED;
		topologicalCellOrdering.add(cellInAction);
	}
	
	private void topologicalSort(Cell cellInAction)
	{
		cellInAction.status = Cell.Status.INPROGRESS;
		for(Token token : cellInAction.getExpressionTokens())
		{
			if(token.getType() == Token.TokenType.CELL)
			{
				int adjacentIndex = getCellIndex(token.getValue());
				Cell adjacentCell = cells[adjacentIndex];
				if(adjacentCell.status == Cell.Status.INPROGRESS)
					throw new RuntimeException("Cycle found between cells while topological sort: " + token.getValue());
				if(adjacentCell.status == Cell.Status.INITIALIZED)
					topologicalSort(adjacentCell);
			}
		}
		cellInAction.status = Cell.Status.PROCESSED;
		topologicalCellOrdering.add(cellInAction);
	}
	
	private void evaluateTopological()
	{
		Stack<Double> valuationStack = new Stack<Double>(); 
		
		for(Cell cell : topologicalCellOrdering)
		{
			for(Token token : cell.getExpressionTokens())
			{
				if(token.getType() == Token.TokenType.CELL)
				{
					int adjacentIndex = getCellIndex(token.getValue());
					Cell adjacentCell = cells[adjacentIndex];
					valuationStack.push(adjacentCell.value);
				}
				else if(token.getType() == Token.TokenType.INTEGER)
					valuationStack.push(Double.parseDouble(token.getValue()));
				else if(token.getType() == Token.TokenType.OPERATOR)
				{
					Double opnd2 = valuationStack.pop();
					Double opnd1 = valuationStack.pop();
					valuationStack.push(ExpressionUtil.evaluate(opnd1, opnd2, token.getValue()));
				}
				else
					throw new RuntimeException("This should not happen");
			}
			
			cell.value = valuationStack.pop();
		}
	}
	
	private void output()
	{
		System.out.println(cols +" "+rows);
		for(Cell cell : cells)
			System.out.println(cell);
	}
	
	private int getCellIndex(String cellIndexToken)
	{
		int rowNum = cellIndexToken.charAt(0) - 'A';
		int colNum = Integer.parseInt(cellIndexToken.substring(1)) - 1;
		if(rowNum >= rows || colNum >= cols)
			throw new RuntimeException("Cell reference specified is out of bound: " + cellIndexToken);
		return rowNum * cols + colNum;
	}
	
	private void printTopologicalSortedOrdering()
	{
		for(Cell c : topologicalCellOrdering)
			System.out.println(c.print());
	}
	
	public static void runDFS()
	{
		try
		{			
			BufferedReader br = 
                    new BufferedReader(new InputStreamReader(System.in));
			        //new BufferedReader(new FileReader("inputfile.txt"));
			
			String line = null;
			line = br.readLine();
			Spreadsheet sp = new Spreadsheet(line);
			sp.readCells(br);
			sp.evaluate();
			sp.output();
		}
		catch(IOException ie)  
		{  
			ie.printStackTrace();
			System.exit(1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static void runTopological()
	{
		try
		{			
			BufferedReader br = 
                    new BufferedReader(new InputStreamReader(System.in));
			        //new BufferedReader(new FileReader("inputfile.txt"));
			
			String line = null;
			line = br.readLine();
			Spreadsheet sp = new Spreadsheet(line);
			sp.readCells(br);
			sp.topologicalSort();
			sp.printTopologicalSortedOrdering();
			System.out.println("==============");
			sp.evaluateTopological();
			sp.output();
		}
		catch(IOException ie)  
		{  
			ie.printStackTrace();
			System.exit(1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static void main(String[] args) 
	{
		if(args != null && args.length > 0)
			runDFS();
		else
			runTopological();
	}
}
