package org.evera.junit.nanserver.repository

import org.evera.junit.nanserver.entities.TestReportDetails
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

/**
 * @author ABIR
 */
@Repository
interface TestReportDetailsRepository : CrudRepository<TestReportDetails?, Long?> {

    fun findBySummary_Id(id: Long): Optional<TestReportDetails>;
}