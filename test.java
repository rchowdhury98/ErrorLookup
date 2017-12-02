import java.lang.*;
import java.io.*;
import java.util.*;

public class test{

	//use globals for now, will replace later!!
	static JavaFile java = new JavaFile();
	static CppFile cpp = new CppFile();
	static CFile c = new CFile();
	static CsFile cs = new CsFile();
	static GoFile go = new GoFile();
	static PythonFile python = new PythonFile();
	static InvalidFile invalidFile = new InvalidFile();

	/**
	*	@param fileType is the type of file we will try to return
	*	@return the corresponding filetype if it is valid, else an invalidFile 
	*/
	static FileType assignFile(String fileType)
	{
		Map<String, FileType> types = new HashMap<String, FileType>();
		types.put(".java", java);
		types.put(".cpp", cpp);
		types.put(".c", c);
		types.put(".cs", cs);
		types.put(".go", go);
		types.put(".py", python);
		types.put("", invalidFile);

		return types.get(fileType);
	}

	/**
	*	See if the string argument lets us know the type of file we are compiling
	*	and assign it in the case that we find it
	*
	*	@param fileType will hold the proper fileType
	*	@param command will be the string we check
	*/
	static boolean containsFileType(String fileType, String command)
	{
		//A mapping of the format <file extension, FileType object>
		Set<String> supportedTypes = new HashSet<String>();
		supportedTypes.add(".java");
		supportedTypes.add(".cpp");
		supportedTypes.add(".c");
		supportedTypes.add(".cs");
		supportedTypes.add(".go");
		supportedTypes.add(".py");

		for(String s: supportedTypes)
			if(command.contains(s))
			{
				fileType = s;
				return true;
			}

		return false;
	}

	public static void main(String[] args) throws Exception
	{
		//take in the compile command for our desired file  as input
		Scanner readCommand = new Scanner(System.in);
		String[] tempCommands = readCommand.nextLine().split("[ ]");

		//parse the line into a List
		List<String> commands = new ArrayList<String>();
		for(int i=0; i<tempCommands.length; i++)
			commands.add(commands.get(i));

		//will contain the File type
		String fileType="";
		FileType file;

		/*	Find out what type of file is being compiled,
			if it is a supported type we will assign it to the string fileType
			Otherwise we leave it empty and print that the file type is not currently
			supported
		*/
		for(int i=0; i<commands.size(); i++)
		{
			if(containsFileType(fileType, commands.get(i)))
			{
				file = assignFile(fileType);
				break;
			}

			if(i==commands.size()-1)
			{
				System.out.println("Not supported file type");
			}
		}

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

		

		System.exit(0);
	}
}

abstract class FileType{
	List<String> errors;
	List<String> warnings;

	public abstract void addError(String message);
	public abstract void addWarning(String message);
	public abstract void searchErrors();
}

class CppFile extends FileType{
	public void addError(String message){

	}

	public void addWarning(String message){

	}

	public void searchErrors(){
		
	}
}

class CFile extends FileType{
	public void addError(String message){
		
	}

	public void addWarning(String message){

	}

	public void searchErrors(){
		
	}
}

class CsFile extends FileType{
	public void addError(String message){
		
	}

	public void addWarning(String message){

	}

	public void searchErrors(){
		
	}
}

class JavaFile extends FileType{
	public void addError(String message){
		
	}

	public void addWarning(String message){

	}

	public void searchErrors(){
		
	}
}

class PythonFile extends FileType{
	public void addError(String message){
		
	}

	public void addWarning(String message){

	}

	public void searchErrors(){
		
	}
}

class GoFile extends FileType{
	public void addError(String message){
		
	}

	public void addWarning(String message){

	}

	public void searchErrors(){
		
	}
}

class InvalidFile extends FileType{
	public void addError(String message){
		
	}

	public void addWarning(String message){

	}

	public void searchErrors(){
		
	}
}