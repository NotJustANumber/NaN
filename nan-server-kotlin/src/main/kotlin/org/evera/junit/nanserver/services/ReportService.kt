package org.evera.junit.nanserver.services

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import org.evera.junit.nanserver.entities.JunitReportData
import org.evera.junit.nanserver.entities.JunitReportDetails
import org.evera.junit.nanserver.entities.JunitReportSummary
import org.evera.junit.nanserver.entities.legacy.LegacyTestCase
import org.evera.junit.nanserver.entities.legacy.LegacyTestSuite
import org.evera.junit.nanserver.repository.JunitReportDetailsRepository
import org.evera.junit.nanserver.repository.JunitReportSummaryRepository
import org.junit.platform.engine.TestExecutionResult
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Stream
import java.util.stream.StreamSupport

@Service
class ReportService(private val resultRepository: JunitReportSummaryRepository,
                    private val detailsRepository: JunitReportDetailsRepository) {

    val mapper = Jackson2ObjectMapperBuilder().createXmlMapper(true).build<XmlMapper>();

    fun saveReport(inputData: Set<JunitReportData>) {
        val savedSummary: Set<JunitReportSummary> = resultRepository
                .saveAll(inputData.map { data: JunitReportData -> reportToSummaryMapper(data) }).toHashSet()
        val summaryMap = savedSummary.map { it.name to it }.toMap()
        detailsRepository
                .saveAll(inputData.map { s: JunitReportData -> reportToDetailsMapper(s, summaryMap) })
    }

    fun reportToSummaryMapper(data: JunitReportData): JunitReportSummary {
        buildIndividualParentData(data);
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

    private fun buildIndividualParentData(data: JunitReportData) {
        data.getChildren()?.let { calculateTestData(data, it) }
    }

    private fun calculateTestData(data: JunitReportData, children: Set<JunitReportData>) {
        children.filter { it.isContainer }.forEach { it -> it.getChildren()?.let { it1 -> calculateTestData(it, it1) } }
        val individual = children.filter { it -> !it.isContainer }.groupBy { it -> it.status }
        data.passed = individual.getOrElse(TestExecutionResult.Status.SUCCESSFUL.toString()) { emptyList() }.size
        data.failed = individual.getOrElse(TestExecutionResult.Status.FAILED.toString()) { emptyList() }.size
        data.skipped = individual.getOrElse("SKIPPED") { emptyList() }.size
        children.filter { it.isContainer }.forEach {
            run {
                data.passed = data.passed + it.passed
                data.skipped = data.skipped + it.skipped
                data.failed = data.failed + it.failed
            }
        }
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

    fun getSummary(): Stream<JunitReportSummary?> {
        return StreamSupport.stream(resultRepository.findAll().spliterator(), false)
    }

    fun getTotalCount(): Optional<Any?> {
        return resultRepository.totalCount
    }

    fun saveXmlReport(xmlString: String) {
        val legacySuite = mapper.readValue<LegacyTestSuite>(xmlString, LegacyTestSuite::class.java)
        val report = convertToJunitReport(legacySuite);
        saveReport(setOf(report))
    }

    private fun convertToJunitReport(legacySuite: LegacyTestSuite): JunitReportData {
        val reportData = JunitReportData()
        reportData.name = legacySuite.name;
        reportData.duration = (legacySuite.time * 1000).toLong()
        reportData.isContainer = true
        reportData.setChildren(legacySuite.testCases.map { s -> covertCaseToReport(s) }.toHashSet())
        return reportData;

    }

    private fun covertCaseToReport(testCase: LegacyTestCase): JunitReportData {
        val reportData = JunitReportData()
        reportData.name = testCase.name;
        reportData.duration = (testCase.time * 1000).toLong()
        reportData.isContainer = false
        reportData.status = TestExecutionResult.Status.SUCCESSFUL.toString()
        if (testCase.skipped) {
            reportData.status = "SKIPPED"
        }
        if (!testCase.failure.isBlank()) {
            reportData.status = TestExecutionResult.Status.FAILED.toString()
        }
        return reportData;
    }

}