public class Main {
    public static void main(String[] args) {
        FileCommander fileCommander=new FileCommander();
        System.out.println(fileCommander.ls());
        FileCommanderCLI fileCommanderCLI = new FileCommanderCLI(System.in, System.out);
        fileCommanderCLI.eventLoop();
    }
}