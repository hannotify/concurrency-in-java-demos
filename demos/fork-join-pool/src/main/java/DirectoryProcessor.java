import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DirectoryProcessor extends RecursiveTask<List<File>> {
    private File rootDirectory;
    private String fileExtension;

    public DirectoryProcessor(File rootDirectory, String fileExtension) {
        this.rootDirectory = rootDirectory;
        this.fileExtension = fileExtension;
    }

    @Override
    protected List<File> compute() {
        var files = new ArrayList<File>();
        var directoryProcessors = new ArrayList<DirectoryProcessor>();

        // BASE CASE - add files in root folder.
        var filesWithTheRightExtension = rootDirectory.listFiles((dir, name) -> name.toLowerCase().endsWith(fileExtension));
        files.addAll(Arrays.asList(filesWithTheRightExtension));

        // FORK for each subdirectory.
        var subDirectories = rootDirectory.listFiles(File::isDirectory);
        for (File subDirectory : subDirectories) {
            DirectoryProcessor subtask = new DirectoryProcessor(subDirectory, fileExtension);
            subtask.fork();
            directoryProcessors.add(subtask);
        }

        // JOIN all results.
        directoryProcessors.forEach(fp -> files.addAll(fp.join()));

        return files;
    }
}
