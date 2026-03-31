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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.oceanbase.odc.core.shared.exception.BadRequestException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MetadataRuntimeManager {

    private final MetadataRuntimeProperties properties;

    public MetadataRuntimeProperties properties() {
        return properties;
    }

    public boolean isRequestTriggeredSyncAllowed() {
        return properties.isAllowRequestTriggeredSync();
    }

    public boolean isBulkVisibleDatabaseSyncAllowed() {
        return properties.isAllowBulkVisibleDatabaseSync();
    }

    public <T> List<T> capBrowseItems(Collection<T> items) {
        return cap(items, properties.getMaxBrowseItemsPerRequest());
    }

    public <T> List<T> capLegacyList(Collection<T> items) {
        return cap(items, properties.getMaxLegacyListItemsPerRequest());
    }

    public <T> List<T> capAutoSyncDataSources(Collection<T> items) {
        return cap(items, properties.getMaxAutoSyncDataSourcesPerCycle());
    }

    public <T> List<T> capAutoSyncDatabases(Collection<T> items) {
        return cap(items, properties.getMaxAutoSyncDatabasesPerDataSource());
    }

    public Pageable capDatasourcePage(Pageable pageable) {
        return capPage(pageable, properties.getMaxDatasourcePageSize());
    }

    public Pageable capDatabasePage(Pageable pageable) {
        return capPage(pageable, properties.getMaxDatabasePageSize());
    }

    public void assertManualSyncSize(String scope, int databaseCount) {
        if (databaseCount > properties.getMaxManualSyncDatabases()) {
            throw new BadRequestException(String.format(
                    "metadata sync for %s is too large: target databases=%d exceeds the safe limit=%d",
                    scope, databaseCount, properties.getMaxManualSyncDatabases()));
        }
    }

    public void assertBulkVisibleSyncAllowed() {
        if (!properties.isAllowBulkVisibleDatabaseSync()) {
            throw new BadRequestException(
                    "bulk visible-database metadata sync is disabled in large-scale metadata runtime mode");
        }
    }

    private Pageable capPage(Pageable pageable, int maxSize) {
        if (pageable == null || pageable.isUnpaged()) {
            return PageRequest.of(0, maxSize);
        }
        int safeSize = Math.min(Math.max(1, pageable.getPageSize()), maxSize);
        return PageRequest.of(pageable.getPageNumber(), safeSize, pageable.getSort());
    }

    private <T> List<T> cap(Collection<T> items, int maxSize) {
        if (items == null || items.isEmpty()) {
            return Collections.emptyList();
        }
        int safeSize = Math.max(1, maxSize);
        if (items instanceof List) {
            List<T> list = (List<T>) items;
            if (list.size() <= safeSize) {
                return list;
            }
            return new ArrayList<>(list.subList(0, safeSize));
        }
        ArrayList<T> list = new ArrayList<>(items);
        if (list.size() <= safeSize) {
            return list;
        }
        return new ArrayList<>(list.subList(0, safeSize));
    }
}
