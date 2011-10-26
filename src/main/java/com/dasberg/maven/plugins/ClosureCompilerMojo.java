package com.dasberg.maven.plugins;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;

import static org.apache.commons.lang.StringUtils.isEmpty;

/**
 * Goal closure compile
 * @goal compile
 * @phase process-sources
 */
public class ClosureCompilerMojo extends AbstractMojo {
    /**
     * Compilation level.
     * @parameter expression="${compilation.level}" default-value="SIMPLE_OPTIMIZATIONS"
     */
    private String compilation_level;

    /**
     * Js directory.
     * @parameter expression="${js_dir}"
     */
    private File js_dir;

    /**
     * Js output directory.
     * @parameter expression="${js_output_dir}"
     */
    private File js_output_dir;

    /**
     * Version.
     * @parameter expression="${version}"
     */
    private String version;


    /**
     * Execute.
     * @throws org.apache.maven.plugin.MojoExecutionException The exception.
     */
    public void execute() throws MojoExecutionException {
        SecurityManager defaultSecurityManager = System.getSecurityManager();
        System.setSecurityManager(new NoExitSecurityManager()); // needed because System.exit is called by the runner.
        if (isValid(js_dir) && isValid(js_output_dir)) {
            for (File js : js_dir.listFiles(new JsFilenameFilter())) {
                compile(js);
            }
        } else {
            getLog().error("The given directories are not valid or are missing. Please check the configuration.");
        }
        System.setSecurityManager(defaultSecurityManager); // set back to original security manager.
    }

    /**
     * Run the compile.
     * @param js The js to compile.
     */
    private void compile(final File js) {
        final Boolean useVersion = isEmpty(version) ? false : true;
        final String fileName = js.getName();
        final String target = targetPath(useVersion, fileName);
        final String source = sourcePath(js);
        final ClosureCompilerRunner runner = new ClosureCompilerRunner(compilation_level, source, target);
        if (runner.shouldRunCompiler()) {
            try {
                runner.run();
            } catch (SecurityException e) {
                // expected throw when run finishes it calls System.exit.
            }
        }
        getLog().debug("Compiled file: " + source + " to file: " + target);
    }

    /**
     * Gets the source path.
     * @param source The source file.
     * @return path The source path.
     */
    private String sourcePath(final File source) {
        return source.getPath();
    }

    /**
     * Gets the target path.
     * @param useVersion Indicates use version.
     * @param fileName The filename.
     * @return target The target.
     */
    private String targetPath(final Boolean useVersion, final String fileName) {
        final StringBuilder builder = new StringBuilder(js_output_dir.getPath());
        builder.append(File.separator);
        builder.append(useVersion ? fileName.replace(".js", "-" + version + ".js") : fileName);
        return builder.toString();
    }

    /**
     * Indicates if the given directory path is a valid file directory.
     * @param file The file.
     * @return <code>true</code> if the directory exists and is a directory, else <code>false</code>.
     */
    private boolean isValid(final File file) {
        return file != null && file.exists() && file.isDirectory();
    }
}
