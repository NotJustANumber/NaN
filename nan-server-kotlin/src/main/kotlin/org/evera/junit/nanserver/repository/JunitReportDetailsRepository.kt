package org.evera.junit.nanserver.repository

import org.evera.junit.nanserver.entities.JunitReportDetails
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

/**
 * @author ABIR
 */
@Repository
interface JunitReportDetailsRepository : CrudRepository<JunitReportDetails?, Long?> {

    fun findBySummary_Id(id: Long): Optional<JunitReportDetails>;
}