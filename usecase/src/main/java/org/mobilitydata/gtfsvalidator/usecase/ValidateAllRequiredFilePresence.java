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

package org.mobilitydata.gtfsvalidator.usecase;

import org.mobilitydata.gtfsvalidator.domain.entity.notice.error.MissingCalendarAndCalendarDateFilesNotice;
import org.mobilitydata.gtfsvalidator.domain.entity.notice.error.MissingRequiredFileNotice;
import org.mobilitydata.gtfsvalidator.usecase.port.GtfsSpecRepository;
import org.mobilitydata.gtfsvalidator.usecase.port.RawFileRepository;
import org.mobilitydata.gtfsvalidator.usecase.port.ValidationResultRepository;

/**
 * Use case to validate the presence of all required files. This use case checks the presence of `required` files within
 * a GTFS archive.
 */

public class ValidateAllRequiredFilePresence {

    private final GtfsSpecRepository specRepo;
    private final RawFileRepository rawFileRepo;
    private final ValidationResultRepository resultRepo;

    /**
     * @param specRepo    a repository storing information about the GTFS specification used
     * @param rawFileRepo a repository storing information about a GTFS dataset
     * @param resultRepo  a repository storing information about the validation process
     */
    public ValidateAllRequiredFilePresence(final GtfsSpecRepository specRepo,
                                           final RawFileRepository rawFileRepo,
                                           final ValidationResultRepository resultRepo) {
        this.specRepo = specRepo;
        this.rawFileRepo = rawFileRepo;
        this.resultRepo = resultRepo;
    }

    /**
     * Use case execution method: checks the presence of all required files in a {@link RawFileRepository} instance
     * A new notice is generated each time a file marked as "required" is missing from a {@link RawFileRepository}
     * instance. This notice is then added to the {@link ValidationResultRepository} provided in the constructor.
     * This method also checks the presence of files `calendar.txt` and `calendar_dates.txt` which are marked as
     * `optional` in the schema, but are actually conditionally required.
     */
    public void execute() {
        if (!rawFileRepo.getFilenameAll().containsAll(specRepo.getRequiredFilenameList())) {
            specRepo.getRequiredFilenameList().stream()
                    .filter(requiredFile -> !rawFileRepo.getFilenameAll().contains(requiredFile))
                    .forEach(missingFile -> resultRepo.addNotice(new MissingRequiredFileNotice(missingFile)));
        }
        // special case for files `calendar.txt` and `calendar_dates.txt` whose presence is conditionally required by
        // the absence of one another
        if (!rawFileRepo.getFilenameAll().contains("calendar.txt") &&
                !rawFileRepo.getFilenameAll().contains("calendar_dates.txt")) {
            resultRepo.addNotice(new MissingCalendarAndCalendarDateFilesNotice());
        }
           }
}