package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface JavaGrep {

  /**
   * Top level search code
   *
   * @throws IOException
   */
  void process() throws IOException;

  /**
   * Traverse a given directory and return all files
   *
   * @param rootDir : input directory
   * @return files under the rootDir
   */
  List<File> listFiles(String rootDir);

  /**
   * Read a file and return all lines
   *
   * @param inputfile : file to be read
   * @return lines
   * @throws IllegalArgumentException if the given inputFile is not a file.
   */
  List<String> readLines(File inputfile);

  /**
   * Check if a line contains the regex pattern passed by the user
   *
   * @param line: input string
   * @return true if there is a match, false otherwise
   */
  boolean containsPattern(String line);

  /**
   * Write lines to file
   *
   * @param lines : matched line
   * @throws IOException if write fails
   */
  void writetoFile(List<String> lines) throws IOException;

  String getRootPath();

  void setRootPath(String rootPath);

  String getRegex();

  void setRegex(String regex);

  String getOutFile();

  void setOutFile(String outfile);
}
