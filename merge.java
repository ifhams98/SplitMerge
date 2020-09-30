package test1;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

/**
 *
 * @author Ifham Shahid
 */
//This code merges the files!
//new comment added 30-9-2020 16:32

//This is a test comments, pls do not remove it.
//eyes chico, they never lie-Scarface

public class merge {
    public static void mergeFiles( java.util.List<File> files, File into)
    throws IOException {
    try{
    FileOutputStream fos = new FileOutputStream(into);
    BufferedOutputStream mergingStream = new BufferedOutputStream(fos);
    for (File f : files) {
    Files.copy(f.toPath(), mergingStream);
    }
    }catch(IOException e){}
    }
    public static java.util.List<File> listOfFilesToMerge(File oneOfFiles) {
    String tmpName = oneOfFiles.getName();//{name}.{number}
    String destFileName = tmpName.substring(0, tmpName.lastIndexOf('.'));//remove .{number}
    File[] files = oneOfFiles.getParentFile().listFiles(
            (File dir, String name) -> name.matches(destFileName + "[.]\\d+"));
    Arrays.sort(files);//ensuring order 001, 002, ..., 010, ...
    return Arrays.asList(files);
    }
    
    public static void mergeFiles(File oneOfFiles, File into)
        throws IOException {
    mergeFiles(listOfFilesToMerge(oneOfFiles), into);
    }
    
    public static java.util.List<File> listOfFilesToMerge(String oneOfFiles) {
    return listOfFilesToMerge(new File(oneOfFiles));
    }
    
    public static void mergeFiles(String oneOfFiles, String into) throws IOException{
        mergeFiles(new File(oneOfFiles), new File(into));
    }
    
    public static void main(String[] args) throws IOException {
        mergeFiles(new File("E:\\test.JPG.001"), new File("E:\\moarij"));
        
    }
    
}
