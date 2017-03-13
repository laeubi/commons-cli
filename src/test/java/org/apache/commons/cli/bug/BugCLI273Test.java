/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.cli.bug;

import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.junit.Test;

public class BugCLI273Test {

    private static final Option singularOption = Option.builder("dryRun").hasArg(false).build();

    @Test
    public void testFromArgs() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption(singularOption);
        String[] args = new String[] { "-dryRun" };
        CommandLine line = parser.parse(options, args);
        assertTrue("Singular Option not present!", line.hasOption(singularOption.getOpt()));
    }

    @Test
    public void testFromPropsWithValueTrue() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption(singularOption);
        Properties properties = new Properties();
        properties.setProperty("dryRun", "true");
        String[] args = new String[] {};
        CommandLine line = parser.parse(options, args, properties);
        assertTrue("Singular Option not present!", line.hasOption(singularOption.getOpt()));
    }

    @Test
    public void testFromPropsWithValueFalse() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption(singularOption);
        Properties properties = new Properties();
        properties.setProperty("dryRun", "false");
        String[] args = new String[] {};
        CommandLine line = parser.parse(options, args, properties);
        assertTrue("Singular Option not present!", line.hasOption(singularOption.getOpt()));
    }

    @Test
    public void testFromProps() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption(singularOption);
        Properties properties = new Properties();
        properties.setProperty("dryRun", "");
        String[] args = new String[] {};
        CommandLine line = parser.parse(options, args, properties);
        assertTrue("Singular Option not present!", line.hasOption(singularOption.getOpt()));
    }
}
