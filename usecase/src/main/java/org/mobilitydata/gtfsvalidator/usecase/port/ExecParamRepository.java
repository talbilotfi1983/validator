/*
 * Copyright (c) 2020. MobilityData IO.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mobilitydata.gtfsvalidator.usecase.port;

import org.apache.commons.cli.Options;
import org.mobilitydata.gtfsvalidator.domain.entity.ExecParam;

import java.io.IOException;
import java.util.Map;

/**
 * This holds the execution parameters passed as parameters of the main execution method from a .json file or from
 * Apache command line.
 */
public interface ExecParamRepository {
    String HELP_KEY = "help";
    String EXTRACT_KEY = "extract";
    String OUTPUT_KEY = "output";
    String PROTO_KEY = "proto";
    String URL_KEY = "url";
    String INPUT_KEY = "input";
    String EXCLUSION_KEY = "exclude";
    String ABORT_ON_ERROR = "abort_on_error";
    String BEAUTIFY_KEY = "beautify";
    int MAX_CHARS_NUM = 22; // empirically defined

    ExecParam getExecParamByKey(final String optionName);

    Map<String, ExecParam> getExecParamCollection();

    ExecParam addExecParam(final ExecParam newExecParam) throws IllegalArgumentException;

    boolean hasExecParam(final String key);

    boolean hasExecParamValue(final String key);

    ExecParamParser getParser(String jsonString);

    ExecParamParser getParser(String[] argStringArray);

    String getExecParamValue(final String key) throws IllegalArgumentException;

    Options getOptions();


    boolean isEmpty();

    interface ExecParamParser {

        Map<String, ExecParam> parse() throws IOException;
    }
}
