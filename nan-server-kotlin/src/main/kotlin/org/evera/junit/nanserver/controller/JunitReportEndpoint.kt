package org.evera.junit.nanserver.controller

import org.evera.junit.nanserver.entities.JunitReportData
import org.evera.junit.nanserver.entities.JunitReportDetails
import org.evera.junit.nanserver.entities.JunitReportSummary
import org.evera.junit.nanserver.repository.JunitReportDetailsRepository
import org.evera.junit.nanserver.repository.JunitReportSummaryRepository
import org.junit.platform.engine.TestExecutionResult
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Stream
import java.util.stream.StreamSupport

/**
 * @author ABIR
 */
@RestController
@RequestMapping("/v1/junit/report")
class JunitReportEndpoint(private val resultRepository: JunitReportSummaryRepository,
                          private val detailsRepository: JunitReportDetailsRepository) {
    @PostMapping("/upload")
    fun uploadJunitData(@RequestBody inputData: Set<JunitReportData>) {
        val savedSummary: Set<JunitReportSummary> = resultRepository
                .saveAll(inputData.map { data: JunitReportData -> reportToSummaryMapper(data) }).toHashSet()
        val summaryMap = savedSummary.map { it.name to it }.toMap()
        detailsRepository
                .saveAll(inputData.map { s: JunitReportData -> reportToDetailsMapper(s, summaryMap) })
    }

    @get:GetMapping
    val junitData: Stream<JunitReportSummary?>
        get() = StreamSupport.stream(resultRepository.findAll().spliterator(), false)

    @get:GetMapping("/total")
    val totalReport: Optional<Any?>
        get() = resultRepository.totalCount

    fun reportToSummaryMapper(data: JunitReportData): JunitReportSummary {
        val summary = resultRepository.findByName(data.name).orElse(JunitReportSummary())!!
        summary.name = data.name


        val totalTests: Map<String, Int> = getRecursively(data.getChildren(), data.getChildren()).filter { !it.isContainer }.distinct().groupingBy { it.status }.eachCount()
        summary.passedTests = getCount(totalTests, TestExecutionResult.Status.SUCCESSFUL.toString())
        summary.failedTests = (getCount(totalTests, TestExecutionResult.Status.FAILED.toString())
                + getCount(totalTests, TestExecutionResult.Status.ABORTED.toString()))
        summary.skippedTests = getCount(totalTests, "SKIPPED")
        summary.totalTests = summary.failedTests + summary.passedTests + summary.skippedTests
        summary.duration = data.duration
        return summary
    }

    private fun getRecursively(parent: Set<JunitReportData>?, children: Set<JunitReportData>?): Set<JunitReportData> {
        if (children != null) {
            return children.flatMap { getRecursively(children, it.getChildren()) }.toHashSet()
        }
        return parent!!
    }

    private fun reportToDetailsMapper(data: JunitReportData, summaryMap: Map<String, JunitReportSummary>): JunitReportDetails {
        val details = JunitReportDetails()
        details.result = data.getChildren()
        details.summary = summaryMap[data.name]
        return details
    }


    private fun getCount(totalTests: Map<String, Int>, failed: String): Int {
        return Optional.ofNullable(totalTests[failed]).orElseGet { 0 }
    }

}