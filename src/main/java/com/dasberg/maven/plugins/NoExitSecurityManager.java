package com.dasberg.maven.plugins;

import java.security.Permission;

/**
 * NoExitSecurityManager enables the mojo to run multiple ClosureCompilerRunner.
 * Because System.exit(..) is called by the CommandLineRunner.run() when it completes,
 * the mojo would exit after one ClosureCompilerRunner has finished its run.
 * It only exits when given the status 0.
 * @author mischa
 */
public class NoExitSecurityManager extends SecurityManager {
    @Override
    public void checkPermission(Permission perm) {
    }

    @Override
    public void checkPermission(Permission perm, Object context) {
    }

    @Override
    public void checkExit(final int status) {
        throw new SecurityException("ClosureCompilerRunner called System.exit(..). Ignoring!");
    }
}