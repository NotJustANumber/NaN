package org.evera.junit.nanserver.repository

import org.evera.junit.nanserver.entities.TestReportDetails
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

/**
 * @author ABIR
 */
@Repository
interface TestReportDetailsRepository : CrudRepository<TestReportDetails?, Long?> {

    fun findTop1BySummary_IdOrderByCreatedDesc(id: Long): Optional<TestReportDetails>;

    @Query(value = "SELECT status FROM TEST_REPORT_DETAILS where SUMMARY_ID = ?1 Limit 0, 5", nativeQuery = true)
    fun history(id: Long): List<Array<Any?>?>?
}