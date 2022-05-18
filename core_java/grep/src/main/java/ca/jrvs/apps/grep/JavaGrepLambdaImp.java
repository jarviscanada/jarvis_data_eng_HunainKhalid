package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepLambdaImp extends JavaGrepImp {

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  public JavaGrepLambdaImp(String regex, String rootPath, String outFile) {
    super(regex, rootPath, outFile);
  }

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep [regex] [rootPath] [outFile]");
    }

    BasicConfigurator.configure();
    // Create new instance of class "JavaGrepImp" and pass cmd line args
    JavaGrepImp javaGrepLambdaImp = new JavaGrepLambdaImp(args[0], args[1], args[2]);

    // Attempt processing of data, handle any errors
    try {
      javaGrepLambdaImp.process();
    } catch (Exception ex) {
      javaGrepLambdaImp.logger.error("Error: Unable to process both "
          + "implementations of grep app: ", ex);
    }
  }

  /*
  Use lambda and stream for reading of lines, efficient
  for larger files.
  */
  @Override
  public List<String> readLines(File input) {
    List<String> res;
    try (Stream<String> lines = Files.lines(Paths.get(String.valueOf(input)))) {
      res = lines.collect(Collectors.toList());
    } catch (Exception e) {
      res = null;
      logger.error("Error reading lines: ", e);
    }
    return res;
  }

  /*
  Use lambda and stream for listing files, efficient
  for larger directories.
  */
  @Override
  public List<File> listFiles(String root) {
    List<File> fileList = new ArrayList<File>();

    try {
      Files.walk(Paths.get(root))
          .filter(Files::isRegularFile)
          .forEach(i -> {
            fileList.add(new File(i.toString()));
          });
    } catch (IOException e) {
      logger.error("Error listing files: ", e);
    }
    return fileList;
  }
}

