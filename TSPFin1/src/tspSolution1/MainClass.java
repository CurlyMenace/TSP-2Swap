package tspSolution1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class MainClass {

	public static void main(String[] args) 
	{
		File file;
		if (args.length > 0)
		{
			file = new File(args[0]);
		}
		
		else 
		{
			JFileChooser chooser = new JFileChooser();
			
			int response = chooser.showOpenDialog(null);
			if(response != JFileChooser.APPROVE_OPTION)
				return;
			
			file = chooser.getSelectedFile();
		}
		
		BufferedReader reader = null;
		
		try
		{
			reader = new BufferedReader(new FileReader(file));
			
		}
		
		catch(IOException e)
		{
			System.exit(1);
		}
		
		int dimension = 0;
		try
		{
			String line;
			while(!(line = reader.readLine()).equals("NODE_COORD_SECTION"))
					{
						String[] entry = line.split(": ", 1);
						switch (entry[0].trim())
						{
						case "TYPE":
							if(!entry[1].trim().equals("TSP"))
								throw new Exception("File not in TSP format");
							break;
						case "DIMENSION":
							dimension = Integer.parseInt(entry[1]);
							break;
						}
					}
			
		}
		catch(Exception e)
		{
			System.exit(1);
		}
		
		ArrayList<Node> nodes = new ArrayList<Node>(dimension);
		
		try
		{
			String line;
			while((line = reader.readLine()) != null && !line.equals("EOF"))
			{
				line = line.trim();
				line.replaceAll("  ", " ");
				line.replaceAll("  ", " ");
				
				String[] entry = line.split(" ");
				
				nodes.add(new Node(entry[0], Double.parseDouble(entry[1]), Double.parseDouble(entry[2])));
			}
			reader.close();
		}
		
		
		catch(Exception e)
		{
			System.exit(1);
		}

		SolverClass solution = new SolverClass(nodes);
		
		solution.Solver();
		
	}

}
