package com.dasberg.maven.plugins;

import org.apache.maven.plugin.Mojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;

import java.io.File;

/**
 * Test class for Mojo.
 * @author mischa
 */
public class MojoTest extends AbstractMojoTestCase {
    private static final String TARGET_DIR = "src/test/resources";
    private static final String SOURCE_DIR = "src/test/process";
    private File testPom;
    private MojoLogger mojoLogger;
    private Mojo mojo;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        testPom = new File(MojoTest.class.getResource("/test-pom.xml").getFile());
        mojoLogger = new MojoLogger();
        mojo = lookupMojo("compile", testPom);
        setVariableValueToObject(mojo, "compilation_level", "SIMPLE_OPTIMIZATIONS");
        mojo.setLog(mojoLogger);
        assertNotNull(mojo);
    }

    public void testShouldFailToExecuteBecauseConfigurationIsNotValid() throws MojoExecutionException, MojoFailureException, IllegalAccessException {
        setVariableValueToObject(mojo, "js_dir", null);
        setVariableValueToObject(mojo, "js_output_dir", null);
        // target configuration param not set
        setVariableValueToObject(mojo, "js_dir", new File(SOURCE_DIR));
        mojo.execute();
        assertEquals("The given directories are not valid or are missing. Please check the configuration.", mojoLogger.error());
        mojoLogger.reset();
        // source configuration param not set
        setVariableValueToObject(mojo, "js_dir", null);
        setVariableValueToObject(mojo, "js_output_dir", new File(TARGET_DIR));
        mojo.execute();
        assertEquals("The given directories are not valid or are missing. Please check the configuration.", mojoLogger.error());
        mojoLogger.reset();
        // invalid source configuration param not set
        setVariableValueToObject(mojo, "js_dir", new File("invalid"));
        setVariableValueToObject(mojo, "js_output_dir", new File(TARGET_DIR));
        mojo.execute();
        assertEquals("The given directories are not valid or are missing. Please check the configuration.", mojoLogger.error());
         mojoLogger.reset();
        // invalid target configuration param not set
        setVariableValueToObject(mojo, "js_dir", new File(SOURCE_DIR));
        setVariableValueToObject(mojo, "js_output_dir", new File("invalid"));
        mojo.execute();
        assertEquals("The given directories are not valid or are missing. Please check the configuration.", mojoLogger.error());
    }

    public void testExecuteSuccessful() throws IllegalAccessException, MojoExecutionException, MojoFailureException {
        setVariableValueToObject(mojo, "js_dir", new File(SOURCE_DIR));
        setVariableValueToObject(mojo, "js_output_dir", new File(TARGET_DIR));
        mojo.execute();
        assertEquals("", mojoLogger.error());
        File[] compiledFiles = getCompiledFiles();
        assertEquals(2, compiledFiles.length);
        assertEquals("one.js", compiledFiles[0].getName());
        assertEquals("two.js", compiledFiles[1].getName());
        cleanTargetDirectory();
        // with version
        setVariableValueToObject(mojo, "version", "1.0.0");
        mojo.execute();
        assertEquals("", mojoLogger.error());
        compiledFiles = getCompiledFiles();
        assertEquals(2, compiledFiles.length);
        assertEquals("one-1.0.0.js", compiledFiles[0].getName());
        assertEquals("two-1.0.0.js", compiledFiles[1].getName());

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        cleanTargetDirectory();
    }

    /** Cleans the target directory by removing all compiled files. */
    private void cleanTargetDirectory() {
        for (File file : getCompiledFiles()) {
            file.delete();
        }
    }

    /** Get all the compiled files. */
    private File[] getCompiledFiles() {
        final File targetDir = new File(TARGET_DIR);
        return targetDir.listFiles(new JsFilenameFilter());
    }
}
