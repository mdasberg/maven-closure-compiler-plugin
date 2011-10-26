package com.dasberg.maven.plugins;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Filename filter that only accepts Javascript files.
 * @author mischa
 */
public class JsFilenameFilter implements FilenameFilter {
    private static final String EXTENSION = ".js";

    /** Constructor. */
    public JsFilenameFilter() {
    }

    /** {@inheritDoc}. */
    public boolean accept(final File dir, final String name) {
        return name.toLowerCase().endsWith(EXTENSION);
    }
}
