/*
 *  Copyright (c) 2020. MobilityData IO.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.mobilitydata.gtfsvalidator.web;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("actions")
@Component
public class GtfsValidatorController {
    private final ServiceManager serviceManager;

    public GtfsValidatorController(final ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }


    @RequestMapping(path = "validate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String validate(@RequestBody Map<String, String> execParamAsJsonString) throws Exception {

        try {
            serviceManager.initializeConfig(null);
        } catch (IOException e) {
            throw e;
        }
        List<String> listArgs = new ArrayList();
        for (Map.Entry<String, String> element : execParamAsJsonString.entrySet()) {
            listArgs.add(element.getKey());
            listArgs.add(element.getValue());
        }
        String[] args = listArgs.toArray(new String[0]);
        Validator.validate(args);

        return  serviceManager.displayReport();
    }

}
