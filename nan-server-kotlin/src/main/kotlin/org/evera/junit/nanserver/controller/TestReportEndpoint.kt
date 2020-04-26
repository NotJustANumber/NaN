package org.evera.junit.nanserver.controller

import org.evera.junit.nanserver.entities.TestReportData
import org.evera.junit.nanserver.entities.TestReportSummary
import org.evera.junit.nanserver.services.ReportService
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Stream
import javax.validation.Valid

/**
 * @author ABIR
 */
@RestController
@RequestMapping("/v1/test/report")
class TestReportEndpoint(private val service: ReportService) {
    @PostMapping("/upload")
    fun uploadTestData(@Valid @RequestBody inputData: Set<TestReportData>) {
        service.saveReport(inputData)
    }

    @get:GetMapping
    val testData: Stream<TestReportSummary?>
        get() = service.getSummary()

    @get:GetMapping("/total")
    val totalReport: Optional<Any?>
        get() = service.getTotalCount()

    @PostMapping("/legacy")
    fun uploadXmlString(@RequestBody xmlString: String) {
        service.saveXmlReport(xmlString)
    }


}