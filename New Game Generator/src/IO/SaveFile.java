package IO;

public enum SaveFile
{
    SAVE_FILE("./gameProgress.txt", "gameProgress.txt");
    public final String path;
    public final String name;
    SaveFile(String path, String name)
    {
        this.path = path;
        this.name = name;
    }
    public String getPath() {
        return this.path;
    }
    public String getName() {
        return this.name;
    }
}

