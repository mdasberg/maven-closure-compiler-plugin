package com.dasberg.maven.plugins;

import com.google.javascript.jscomp.CommandLineRunner;

/**
 * ClosureCompilerRunner compiles the given file to the
 * target file.
 * @author mischa
 */
public class ClosureCompilerRunner extends CommandLineRunner {
    private static final String COMPILATION_LEVEL = "--compilation_level";
    private static final String JS = "--js";
    private static final String JS_OUTPUT_FILE = "--js_output_file";

    /**
     * Constructor.
     * @param compilation_level The compilation level.
     * @param original The original.
     * @param target The target.
     */
    public ClosureCompilerRunner(final String compilation_level, final String original, final String target) {
        super(new String[]{
                COMPILATION_LEVEL, compilation_level,
                JS, original,
                JS_OUTPUT_FILE, target});
    }
}
