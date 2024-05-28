/*
 * Copyright (c) 2024 vitasystems GmbH.
 *
 * This file is part of project EHRbase
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific LANGUAGE governing permissions and
 * limitations under the License.
 */

-- the index contains vo_id as second expression to support stable and performant pagination
CREATE INDEX IF NOT EXISTS comp_data_start_time_desc_idx ON comp_data USING btree
    ((jsonb_dv_ordered_magnitude(data -> 'st')::numeric) DESC, vo_id ASC) WHERE rm_entity = 'EC';
