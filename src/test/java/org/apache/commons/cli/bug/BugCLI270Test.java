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

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.junit.Test;

public class BugCLI270Test {
    private static final Option headerOption = Option.builder("H").hasArgs().valueSeparator('=').build();

    @Test
    public void testFromArgs() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption(headerOption);
        String[] args = new String[] { "-Hheader1=1234", "-Hheader2=XXXX" };
        CommandLine line = parser.parse(options, args);
        Properties properties = line.getOptionProperties(headerOption.getOpt());
        assertEquals("1234", properties.getProperty("header1"));
        assertEquals("XXXX", properties.getProperty("header2"));
    }

    @Test
    public void testFromProperties() throws Exception {
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption(headerOption);
        String[] args = new String[] {};
        Properties cmdProps = new Properties();
        cmdProps.setProperty("Hheader1", "1234");
        cmdProps.setProperty("Hheader2", "XXXX");
        CommandLine line = parser.parse(options, args, cmdProps);
        Properties properties = line.getOptionProperties(headerOption.getOpt());
        assertEquals("1234", properties.getProperty("header1"));
        assertEquals("XXXX", properties.getProperty("header2"));
    }
}
