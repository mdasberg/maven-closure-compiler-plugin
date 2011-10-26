maven-closure-compiler-plugin
=========================

**Motivation**
Google Closure-compiler doesn't have a maven plugin yet.

**Usage**
Add the following to your maven build plugins.

<plugin>
    <groupId>com.dasberg.maven.plugins</groupId>
    <artifactId>maven-closure-compiler-plugin</artifactId>
    <version>${maven-closure-compiler-plugin.version}</version>
    <configuration>
        <compilation_level>ADVANCED_OPTIMIZATIONS</compilation_level> <!-- default is SIMPLE_OPTIMIZATIONS -->
        <js_dir>src/test/process</js_dir>
        <js_dir>src/test/resources</js_dir>
        <version>1.0.0</version>
    </configuration>
</plugin>

As you can see there are 4 parameters to the widget:
1: **compilation_level** is a optional parameter which uses SIMPLE_OPTIMIZATIONS as default.
2: **js_dir** is a required parameter which specifies the location of the uncompiled files.
3. **js_output_dir** is a required parameter which specifies the location where the compiled files are put.
4. **version** is an optional parameter which is used to add a version number to the compiled files.