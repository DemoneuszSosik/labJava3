import java.io.*;
import java.nio.file.Path;

public class FileCommanderCLI {
    private FileCommander fileCommander;
    private BufferedReader reader;
    private BufferedWriter writer;
    public FileCommanderCLI(InputStream in, PrintStream out){
        fileCommander=new FileCommander();
        reader=new BufferedReader(new InputStreamReader(in));
        writer=new BufferedWriter(new OutputStreamWriter(out));
    }
    public void eventLoop(){
        while(true){
            try{
                String line= reader.readLine();
                runCommand(line);
            }
            catch(IOException e){
                throw new RuntimeException(e);
            }
        }
    }
    private void runCommand(String line) throws IOException {
        String[] params = line.split(" ");
        if(params.length == 0)
            return;
        switch (params[0]) {
            case "ls" -> {writer.write(fileCommander.ls(String::toString).toString());writer.flush();}
            case "pwd" -> System.out.println(fileCommander.pwd());
            case "find" ->System.out.println(fileCommander.find(params[0]));
            case "cd" -> fileCommander.cd(Path.of(params[1]));
        }

    }
}
