/*
 * Copyright (c) 2023 OceanBase.
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
package com.oceanbase.odc.service.db.schema;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.oceanbase.odc.core.shared.exception.BadRequestException;

public class MetadataRuntimeManagerTest {

    @Test
    public void should_cap_browse_items() {
        MetadataRuntimeProperties properties = new MetadataRuntimeProperties();
        properties.setMaxBrowseItemsPerRequest(2);
        MetadataRuntimeManager manager = new MetadataRuntimeManager(properties);

        List<Integer> actual = manager.capBrowseItems(Arrays.asList(1, 2, 3, 4));

        Assert.assertEquals(Arrays.asList(1, 2), actual);
    }

    @Test
    public void should_cap_page_size() {
        MetadataRuntimeProperties properties = new MetadataRuntimeProperties();
        properties.setMaxDatasourcePageSize(100);
        MetadataRuntimeManager manager = new MetadataRuntimeManager(properties);

        Pageable pageable = manager.capDatasourcePage(PageRequest.of(0, 500));

        Assert.assertEquals(100, pageable.getPageSize());
    }

    @Test(expected = BadRequestException.class)
    public void should_reject_oversized_manual_sync() {
        MetadataRuntimeProperties properties = new MetadataRuntimeProperties();
        properties.setMaxManualSyncDatabases(3);
        MetadataRuntimeManager manager = new MetadataRuntimeManager(properties);

        manager.assertManualSyncSize("datasource-1", 4);
    }

    @Test(expected = BadRequestException.class)
    public void should_reject_bulk_visible_sync_when_disabled() {
        MetadataRuntimeProperties properties = new MetadataRuntimeProperties();
        properties.setAllowBulkVisibleDatabaseSync(false);
        MetadataRuntimeManager manager = new MetadataRuntimeManager(properties);

        manager.assertBulkVisibleSyncAllowed();
    }
}
