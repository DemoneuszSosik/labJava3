import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileCommander {
    private Path path;
    public FileCommander(){
        this.path=Path.of(System.getProperty("user.home"));
    }
    public String pwd(){
        return path.toString();
    }
    public void cd(Path path){
        this.path=this.path.resolve(path).normalize();
    }
    public List<String> ls(Function<String,String> f){
        try{
            Comparator<Path> comp=(a,b)->Boolean.compare(Files.isDirectory(a),Files.isDirectory(b));
            return Files.list(path)
                    .sorted(comp)
                    .map(o->{
                        if(Files.isDirectory(o))
                            return f.apply(o.getFileName().toString());
                        else return o.getFileName().toString();
                    })
                    .collect(Collectors.toList());
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    public Function<String,String> bracket(){

    }
    public List<String> find(String search){
        try{
            return Files.walk(path)
                    .filter(o->o.getFileName().toString().contains(search))
                    .map(Path::toString)
                    .collect(Collectors.toList());
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
