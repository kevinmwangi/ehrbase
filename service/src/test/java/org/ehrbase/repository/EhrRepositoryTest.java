/*
 * Copyright (c) 2019-2024 vitasystems GmbH.
 *
 * This file is part of project EHRbase
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.repository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import com.nedap.archie.rm.ehr.EhrStatus;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.ehrbase.jooq.pg.tables.records.EhrStatusVersionHistoryRecord;

class EhrRepositoryTest
        extends AbstractVersionedObjectRepositoryUpdateTest<EhrRepository, EhrStatus, EhrStatusVersionHistoryRecord> {

    private static EhrStatus ehrStatus(ObjectVersionId versionId) {
        EhrStatus ehrStatus = new EhrStatus();
        ehrStatus.setUid(versionId);
        ehrStatus.setModifiable(true);
        ehrStatus.setQueryable(true);
        return ehrStatus;
    }

    public EhrRepositoryTest() {
        super(spy(new EhrRepository(mock(), mock(), () -> SYSTEM_ID, OffsetDateTime::now)));
    }

    @Override
    protected EhrStatus versionedObject(ObjectVersionId objectVersionId) {
        return ehrStatus(objectVersionId);
    }

    @Override
    protected String formatUpdateErrorNotExistMessage() {
        return "No EHR_STATUS in ehr: %s".formatted(EHR_ID);
    }

    @Override
    protected EhrStatusVersionHistoryRecord versionRecord() {
        return new EhrStatusVersionHistoryRecord();
    }

    @Override
    protected void callUpdate(EhrRepository repository, UUID ehrId, EhrStatus versionedObject) {
        repository.update(ehrId, versionedObject, null, null);
    }
}
