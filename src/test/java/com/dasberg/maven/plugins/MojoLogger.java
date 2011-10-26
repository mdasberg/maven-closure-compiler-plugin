package com.dasberg.maven.plugins;

import org.apache.maven.plugin.logging.Log;

/**
 * Mojo logger.
 * @author mischa
 */
public class MojoLogger implements Log {
    private StringBuilder debug = new StringBuilder();
    private StringBuilder info = new StringBuilder();
    private StringBuilder warn = new StringBuilder();
    private StringBuilder error = new StringBuilder();

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public void debug(CharSequence charSequence) {
        debug.append(charSequence);
    }

    @Override
    public void debug(CharSequence charSequence, Throwable throwable) {
        debug.append(charSequence);
        debug.append(throwable.getMessage());
    }

    @Override
    public void debug(Throwable throwable) {
        debug.append(throwable.getMessage());
    }

    /**
     * Debug.
     * @return debug The debug.
     */
    public String debug() {
        return debug.toString();
    }

    @Override
    public boolean isInfoEnabled() {
        return true;
    }

    @Override
    public void info(CharSequence charSequence) {
        info.append(charSequence);
    }

    @Override
    public void info(CharSequence charSequence, Throwable throwable) {
        info.append(charSequence);
        info.append(throwable.getMessage());
    }

    @Override
    public void info(Throwable throwable) {
        info.append(throwable.getMessage());
    }

    /**
     * Info.
     * @return info The info.
     */
    public String info() {
        return info.toString();
    }

    @Override
    public boolean isWarnEnabled() {
        return true;
    }

    @Override
    public void warn(CharSequence charSequence) {
        warn.append(charSequence);
    }

    @Override
    public void warn(CharSequence charSequence, Throwable throwable) {
        warn.append(charSequence);
        warn.append(throwable.getMessage());
    }

    @Override
    public void warn(Throwable throwable) {
        warn.append(throwable.getMessage());
    }

    /**
     * Warn.
     * @return warn The warn.
     */
    public String warn() {
        return warn.toString();
    }

    @Override
    public boolean isErrorEnabled() {
        return true;
    }

    @Override
    public void error(CharSequence charSequence) {
        error.append(charSequence);
    }

    @Override
    public void error(CharSequence charSequence, Throwable throwable) {
        error.append(charSequence);
        error.append(throwable.getMessage());
    }

    @Override
    public void error(Throwable throwable) {
        error.append(throwable.getMessage());
    }

    /**
     * Error.
     * @return error The error.
     */
    public String error() {
        return error.toString();
    }

    public void reset() {
        debug = new StringBuilder();
        info = new StringBuilder();
        warn = new StringBuilder();
        error = new StringBuilder();
    }
}
