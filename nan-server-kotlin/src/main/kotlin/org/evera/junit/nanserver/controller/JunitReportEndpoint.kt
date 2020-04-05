package org.evera.junit.nanserver.controller

import org.evera.junit.nanserver.entities.JunitReportData
import org.evera.junit.nanserver.entities.JunitReportSummary
import org.evera.junit.nanserver.services.ReportService
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Stream
import javax.validation.Valid

/**
 * @author ABIR
 */
@RestController
@RequestMapping("/v1/junit/report")
class JunitReportEndpoint(private val service: ReportService) {
    @PostMapping("/upload")
    fun uploadJunitData(@Valid @RequestBody inputData: Set<JunitReportData>) {
        service.saveReport(inputData)
    }

    @get:GetMapping
    val junitData: Stream<JunitReportSummary?>
        get() = service.getSummary()

    @get:GetMapping("/total")
    val totalReport: Optional<Any?>
        get() = service.getTotalCount()

    @PostMapping("/legacy")
    fun uploadXmlString(@RequestBody xmlString: String) {
        service.saveXmlReport(xmlString)
    }


}