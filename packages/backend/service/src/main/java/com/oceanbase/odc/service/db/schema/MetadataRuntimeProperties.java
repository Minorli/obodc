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

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * Runtime protections for large-scale metadata browsing and synchronization.
 */
@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "odc.database.metadata-runtime")
public class MetadataRuntimeProperties {

    /**
     * Hard cap for current-database browse responses used by v2 metadata tree browsing.
     */
    private int maxBrowseItemsPerRequest = 200;

    /**
     * Hard cap for legacy v1/vsession list-style metadata endpoints.
     */
    private int maxLegacyListItemsPerRequest = 200;

    /**
     * Safe upper bound for datasource listing page size.
     */
    private int maxDatasourcePageSize = 100;

    /**
     * Safe upper bound for database listing page size.
     */
    private int maxDatabasePageSize = 100;

    /**
     * Whether browse requests may synchronously trigger metadata sync when cache and live metadata
     * mismatch.
     */
    private boolean allowRequestTriggeredSync = false;

    /**
     * Whether the current-user-visible bulk sync endpoint is allowed.
     */
    private boolean allowBulkVisibleDatabaseSync = false;

    /**
     * Manual sync requests targeting more than this number of databases are rejected.
     */
    private int maxManualSyncDatabases = 50;

    /**
     * Scheduled datasource sync job will submit at most this many datasources in one cycle.
     */
    private int maxAutoSyncDataSourcesPerCycle = 50;

    /**
     * Background schema sync will submit at most this many databases for one datasource in one cycle.
     */
    private int maxAutoSyncDatabasesPerDataSource = 50;
}
