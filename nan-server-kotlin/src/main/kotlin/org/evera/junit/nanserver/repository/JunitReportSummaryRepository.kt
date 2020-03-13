package org.evera.junit.nanserver.repository

import org.evera.junit.nanserver.entities.JunitReportData
import org.evera.junit.nanserver.entities.JunitReportSummary
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
import java.util.stream.Stream

@Repository
interface JunitReportSummaryRepository : CrudRepository<JunitReportSummary?, Long?> {
    fun findAllByNameOrderByCreatedDateDesc(name: String): Stream<JunitReportData?>?

    fun findByName(name: String?): Optional<JunitReportSummary?>

    @get:Query(value = "SELECT count(*),sum(total_tests),sum(passed_tests),sum(failed_tests),sum(skipped_tests) FROM JUNIT_REPORT_SUMMARY ", nativeQuery = true)
    val totalCount: Optional<Any?>
}