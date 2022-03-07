/* Name: Hunain Khalid
 * Date: Feb, 4th, 2022
 * Purpose: Simulate "grep" command in java
 * Filename: javaGrepImp.java
 */
package ca.jrvs.apps.grep;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaGrepImp implements JavaGrep {

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);
  private String regex;
  private String rootPath;
  private String outFile;

  public JavaGrepImp() {}
  public JavaGrepImp(String regex, String rootPath, String outFile)
  {
    setRegex(regex);
    setOutFile(outFile);
    setRootPath(rootPath);
  }

  // Main method to take 3 arguments, processed via helper methods
  public static void main(String[] args) {
    // Fail-Fast, Fail safe
    if (args.length != 3)
    {
      throw new IllegalArgumentException("USAGE: JavaGrep [regex] [rootPath] [outFile]");
    }

    BasicConfigurator.configure();
    // Create new instance of class JavaGrepImp and pass cmd line args
    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    // Attempt processing of data, handle any errors
    try {
      javaGrepImp.process();
    } catch (Exception ex) {
      javaGrepImp.logger.error("Error: Unable to process: ", ex);
    }
  }

  /* Attempt and create a new list to store matched lines, if failed
     then throw an IOException, stating processing of files failed. */

  @Override
  public void process() throws IOException
  {
    try
    {
      List<String> linesMatched = new ArrayList<String>();
      for (File f : listFiles(getRootPath())) {
        for (String line : readLines(f)) {
          if (containsPattern(line)) {
            linesMatched.add(line);
          }
        }
      }
    writetoFile(linesMatched);
  }
  catch (IOException e) {
    logger.error("Error: Unable to process files: ", e);
    throw new IOException("Unable to handle processing of files");
  }
}

  /*
     Find files in the root directory, and add
     to fileList. only if directories have files.
     Else, log error "e" and throw new FileNotFoundException.
     */
  @Override
  public List<File> listFiles(String rootDir) throws FileNotFoundException
  {
    List<File> fileList = new ArrayList<File>();
    try
    {
      Files.walk(Paths.get(rootDir)).forEach(dir ->
      {
        if (dir.toFile().isFile())
        {
          fileList.add(dir.toFile());
        }
      });
    }
    catch (IOException e)
    {
      logger.error("Error: ", e);
      throw new FileNotFoundException("File Was Not Found");
    }
    return fileList;
  }

  /*
     Read each line from input then write each line to file
     of choice. If failed throw an IOException, and log
     there was an error writing to file.
  */
  @Override
  public void writetoFile(List<String> lines) throws IOException
  {
    BufferedWriter streamOut = new BufferedWriter(new FileWriter(this.getOutFile()));
    try
    {
      for (String line : lines)
      {
        streamOut.write(line);
        streamOut.newLine();
      }
      logger.debug("File written successfully");
      streamOut.close();
    }
    catch (IOException e)
    {
      logger.error("Error writing to file", e);
    }
  }

  /*
     Read each line from input then write each line to file
     of choice. If failed throw an IOException, and log
     there was an error writing to file.
  */
  @Override
  public List<String> readLines(File inputfile)
  {
    List<String> lineOfFile = new ArrayList<String>();

    try
    {
      BufferedReader br = new BufferedReader(new FileReader(inputfile));
      String nextLine;

      while ((nextLine = br.readLine()) != null)
      {
        lineOfFile.add(nextLine);
      }
      br.close();
    }
    catch (IOException e)
    {
      logger.error("Error, failed in reading of lines", e);
    }

    return lineOfFile;
  }

   /*
   Method used to determine if there is a matching pattern,
   returns match.find() returns true or false as a result.
   */
  @Override
  public boolean containsPattern(String line)
  {
    Pattern regex = Pattern.compile(getRegex());
    Matcher match = regex.matcher(line);

    return match.find();
  }

  /* Below are all getters/setters of private vars "regex",
  "rootpath", "outfile" */
  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getRegex() {
    return this.regex;
  }

  @Override
  public String getRootPath() {
    return rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getOutFile() {
    return this.outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }
}
