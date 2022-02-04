/* Name: Hunain Khalid
 * Date: Feb, 4th, 2022
 * Purpose: Simulate "grep" command in java
 * Filename: javaGrepImp.java
 */

package ca.jrvs.apps.grep;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

// Main method to take 3 arguments, processed via helper methods
public class JavaGrepImp implements JavaGrep
{
    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    private String regex;
    private String rootPath;
    private String outFile;

    public static void main(String[] args)
    {
        if (args.length != 3)
        {
            throw new IllegalArgumentException("USAGE: JavaGrep [regex] [rootPath] [outFile]");
        }

        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);

        try
        {
            javaGrepImp.process();
        }
        catch (Exception ex)
        {
            javaGrepImp.logger.error("Error: Unable to process", ex);
        }

    }

    @Override
    public void process() throws IOException
    {

    }

    @Override
    public List<File> listFiles(String rootDir) {
        return null;
    }

    @Override
    public List<String> readLines(File inputfile) {
        return null;
    }

    @Override
    public boolean containsPattern(String line) {
        return false;
    }

    @Override
    public void writetoFile(List<String> lines) throws IOException {

    }

    @Override
    public void setRegex(String regex)
    {
        this.regex = regex;
    }

    @Override
    public String getRegex()
    {
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
    public void setOutFile(String outfile) {
        this.outFile = outfile;
    }
}
