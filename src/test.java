import java.lang.*;
import java.io.*;
import java.util.*;

public class test{

	//use globals for now, will replace later!!
	static JavaFile java = new JavaFile();
	static CppFile cpp = new CppFile();
	static CFile c = new CFile();
	static CsFile cs = new CsFile();
	static PythonFile python = new PythonFile();
	static InvalidFile invalidFile = new InvalidFile();

	/**
	*	See if the string argument lets us know the type of file we are compiling
	*	and assign it in the case that we find it
	*
	*	@param fileNames will hold the file names
	*	@param command will be the string we check
<<<<<<< HEAD
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
		supportedTypes.put("csc", cs);
		supportedTypes.put("python", python);
		supportedTypes.put("python3", python);

		if(fileNames.containsKey(compiler))
			retun supportedTypes(compiler);

		return invalidFile;
=======
	*	@returb the corresponding FileType object
	*/
	static FileType assignFile(Set<String> fileNames, List<String> command)
	{
		//A mapping of the format <file extension, FileType object>
		Map<String, FileType> supportedTypes = new HashMap<String, FileType>();
		supportedTypes.put(".java", java);
		supportedTypes.put(".cpp", cpp);
		supportedTypes.put(".h", cpp);
		supportedTypes.put(".c", c);
		supportedTypes.put(".cs", cs);
		supportedTypes.put(".py", python);

		FileType file= invalidFile;

		for(String arg: command)
			for(String type: supportedTypes.keySet())
				if(arg.contains(type))
				{
					fileNames.add(arg);
					file = supportedTypes.get(type);
				}

		return file;
>>>>>>> fd7715e9dba56ff512315539b60b4e00dadf84be
	}

	public static void main(String[] args) throws Exception
	{
		//take in the compile command for our desired file  as input
		Scanner readCommand = new Scanner(System.in);
		String[] tempCommands = readCommand.nextLine().split("[ ]");

		//parse the line into a List
		List<String> commands = new ArrayList<String>();
		for(int i=0; i<tempCommands.length; i++)
			commands.add(tempCommands[i]);

		//will contain the File names
		Set<String> fileNames= new HashSet<String>();

<<<<<<< HEAD
		/*	Find out what type of file(s) is being compiled by checking the compiler used
=======
		/*	Find out what type of file(s) is being compiled,
>>>>>>> fd7715e9dba56ff512315539b60b4e00dadf84be
			if it is a supported type we will add it to fileNames
			Otherwise we leave it empty and print that the file type is not currently
			supported
		*/
<<<<<<< HEAD
		FileType file = assignFile(fileNames, commands[0]);
=======
		FileType file = assignFile(fileNames, commands);
>>>>>>> fd7715e9dba56ff512315539b60b4e00dadf84be

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
		}
		pr.waitFor();
		System.out.println("compilation complete!");

		in.close();

<<<<<<< HEAD
		file.parseOutput(output.toString(), fileNames);

=======
>>>>>>> fd7715e9dba56ff512315539b60b4e00dadf84be
		System.exit(0);

	}
}

abstract class FileType{
	List<String> errors;

	public FileType(){
		errors = new ArrayList<String>();
	}
	
	//helper function for parseOutput
	private void addError(String message){
		errors.add(message);
	}

	/**
	*	@param output is the terminal output we will parse
	*	@param fileNames contains all the filenames
	*	@effects Will read in the terminal output and accordingly place them into errors
	*/
	public abstract void parseOutput(String output, Set<String> fileNames);
	public abstract void searchErrors();
}

class CppFile extends FileType{
	public CppFile(){
		super();
	}

	public void parseOutput(String output, Set<String> fileNames){

	}

	public void searchErrors(){
		
	}
}

class CFile extends FileType{
	public CFile(){
		super();
	}

	public void parseOutput(String output, Set<String> fileNames){
		
	}

	public void searchErrors(){
		
	}
}

class CsFile extends FileType{
	public CsFile(){
		super();
	}

	public void parseOutput(String output, Set<String> fileNames){
		
	}

	public void searchErrors(){
		
	}
}

class JavaFile extends FileType{
	public JavaFile(){
		super();
	}

	public void parseOutput(String output, Set<String> fileNames){
		
	}

	public void searchErrors(){
		
	}
}

class PythonFile extends FileType{
	public PythonFile(){
		super();
	}

<<<<<<< HEAD
	public void parseOutput(String output, Set<String> fileNames){
		
	}

	public void searchErrors(){
		
	}
}

=======
>>>>>>> fd7715e9dba56ff512315539b60b4e00dadf84be
class InvalidFile extends FileType{
	public InvalidFile(){
		super();
	}

	public void parseOutput(String output, Set<String> fileNames){
		
	}

	public void searchErrors(){
		
	}
}