package org.evera.junit.nanserver.repository

import org.evera.junit.nanserver.entities.TestReportData
import org.evera.junit.nanserver.entities.TestReportSummary
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
import java.util.stream.Stream

@Repository
interface TestReportSummaryRepository : CrudRepository<TestReportSummary?, Long?> {

    fun findAllByNameOrderByCreatedDesc(name: String): Stream<TestReportData?>?


    fun findByName(name: String?): Optional<TestReportSummary?>

    @get:Query(value = "SELECT count(*),sum(total),sum(passed),sum(failed),sum(skipped) FROM TEST_REPORT_SUMMARY ", nativeQuery = true)
    val totalCount: Optional<Any?>
}