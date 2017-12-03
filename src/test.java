import java.lang.*;
import java.io.*;
import java.util.*;

public class test{

	//use globals for now, will replace later!!
	static JavaFile java = new JavaFile();
	static CppFile cpp = new CppFile();
	static PythonFile python = new PythonFile();
	static InvalidFile invalidFile = new InvalidFile();

	/**
	*	See if the string argument lets us know the type of file we are compiling
	*	and assign it in the case that we find it
	*
	*	@param fileNames will hold the file names
	*	@param command will be the string we check
	*	@return the corresponding FileType object
	*/
	static FileType assignFile(Set<String> fileNames, String compiler)
	{
		//A mapping of the format <file extension, FileType object>
		Map<String, FileType> supportedTypes = new HashMap<String, FileType>();
		supportedTypes.put("javac", java);
		supportedTypes.put("g++", cpp);
		supportedTypes.put("gcc", cpp);
		supportedTypes.put("clang", cpp);
		supportedTypes.put("python", python);
		supportedTypes.put("python3", python);

		if(supportedTypes.containsKey(compiler))
			return supportedTypes.get(compiler);

		return invalidFile;
	}

	public static void main(String[] args) throws Exception
	{
		//get the compile statement from arguments
		List<String> commands = new ArrayList<String>();
		for(int i=0; i<args.length; i++)
			commands.add(args[i]);

		//will contain the File names
		Set<String> fileNames= new HashSet<String>();


		/*	Find out what type of file(s) is being compiled by checking the compiler used
			if it is a supported type we will add it to fileNames
			Otherwise we leave it empty and print that the file type is not currently
			supported
		*/

		FileType file = assignFile(fileNames, commands.get(0));

		if(file == invalidFile)
			System.out.println("Not supported file type");


		//compile the program
		ProcessBuilder ps=new ProcessBuilder(commands);

		//leave this as true for now, we want to see error messages from test.java
		//so we can bugfix!!
		ps.redirectErrorStream(true);

		//compile the program
		Process pr = ps.start();  

		BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		String line;
		StringBuffer output = new StringBuffer(); //hold the error message

		while ((line = in.readLine()) != null) {
		    System.out.println(line);
		    output.append(line);
		    output.append("\n");
		}
		pr.waitFor();

		if (output.toString().isEmpty())
			System.out.println("compilation complete!");
		else
			System.out.println("compilation errors!");

		in.close();

		file.parseOutput(output.toString());
		file.printErrors();

		System.exit(0);

	}
}

abstract class FileType{
	List<String> errors;

	public FileType(){
		errors = new ArrayList<String>();
	}
	
	//helper function for parseOutput
	protected void addError(String message){
		errors.add(message);
	}

	//helper function for JavaFile.parseOutput
	protected void removeLastError(){
		errors.remove(errors.size()-1);
	}

	//tester method
	public void printErrors(){
		for(String s: errors)
			System.out.println(s);
	}

	/**
	*	@param output is the terminal output we will parse
	*	@param fileNames contains all the filenames
	*	@effects Will read in the terminal output and accordingly place them into errors
	*/
	public abstract void parseOutput(String output);
	public abstract void searchErrors();
}

class CppFile extends FileType{
	public CppFile(){
		super();
	}

	/**
	*
	*	@param output is what we have left of the terminal output to search through
	*/
	public void parseOutput(String output){
		int substrStart = output.indexOf("error");

		while(substrStart>=0)
		{
			int substrEnd = output.substring(substrStart).indexOf("\n") + substrStart;

			if(substrEnd<0)
				substrEnd = output.length()-1;

			addError(output.substring(substrStart,substrEnd));
			output = output.substring(substrEnd);

			substrStart = output.indexOf("error");
		}
	}

	public void searchErrors(){
		
	}
}

class JavaFile extends FileType{
	public JavaFile(){
		super();
	}

	/**
	*
	*	@param output is what we have left of the terminal output to search through
	*/
	public void parseOutput(String output){
		int substrStart = output.indexOf("error");

		while(substrStart>=0)
		{
			int substrEnd = output.substring(substrStart).indexOf("\n") + substrStart;

			if(substrEnd<0)
				substrEnd = output.length()-1;

			String error = output.substring(substrStart,substrEnd);

			//if there is an Exception, show the source
			if(error.contains("Exception"))
				error+= output.substring(substrEnd+1, output.substring(substrEnd+1).indexOf("\n"));

			addError(error);
			output = output.substring(substrEnd);

			substrStart = output.indexOf("error");
		}

		//remove last error statement since it is just an error count
		if(errors.size()>1)
			removeLastError();
	}

	public void searchErrors(){
		
	}
}

class PythonFile extends FileType{
	public PythonFile(){
		super();
	}

	//have to watch out for exceptions
	public void parseOutput(String output){
		//if it is an exception, just put in the whole message
		if(output.contains("Exception"))
			addError(output);

		else
			addError(output.substring(output.indexOf(">")+1));
	}

	public void searchErrors(){
		
	}
}

class InvalidFile extends FileType{
	public InvalidFile(){
		super();
	}

	public void parseOutput(String output){
		
	}

	public void searchErrors(){
		
	}
}