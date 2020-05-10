package org.evera.junit.nanserver.services

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import org.evera.junit.nanserver.entities.TestReportData
import org.evera.junit.nanserver.entities.TestReportDetails
import org.evera.junit.nanserver.entities.TestReportSummary
import org.evera.junit.nanserver.entities.legacy.LegacyTestCase
import org.evera.junit.nanserver.entities.legacy.LegacyTestSuite
import org.evera.junit.nanserver.repository.TestReportDetailsRepository
import org.evera.junit.nanserver.repository.TestReportSummaryRepository
import org.junit.platform.engine.TestExecutionResult
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Stream
import java.util.stream.StreamSupport

@Service
class ReportService(private val resultRepository: TestReportSummaryRepository,
                    private val detailsRepository: TestReportDetailsRepository) {

    val mapper = Jackson2ObjectMapperBuilder().createXmlMapper(true).build<XmlMapper>();

    fun saveReport(inputData: Set<TestReportData>) {
        val summaryMap = saveSummary(inputData)
        detailsRepository
                .saveAll(inputData.map { s: TestReportData -> reportToDetailsMapper(s, summaryMap) })
    }

    private fun saveSummary(inputData: Set<TestReportData>): Map<String, TestReportSummary> {

        val savedSummary: Set<TestReportSummary> = inputData.map { data -> reportToSummaryMapper(data) }
                .map { s -> resultRepository.save(s) }
                .toHashSet()
//        val savedSummary: Set<TestReportSummary> = resultRepository
//                .saveAll(inputData.map { data: TestReportData -> reportToSummaryMapper(data) }).toHashSet()
        return savedSummary.map { it.name to it }.toMap()
    }

    fun reportToSummaryMapper(data: TestReportData): TestReportSummary {
//        buildIndividualParentData(data)
        val summary: TestReportSummary = resultRepository.findByName(data.name).orElse(TestReportSummary())!!
        summary.name = data.name
        summary.passed = data.passed
        summary.skipped = data.skipped
        summary.failed = data.failed
        summary.duration = data.duration
        summary.total = data.passed + data.skipped + data.failed
        return summary
    }

    private fun calculateCount(data: TestReportData, summary: TestReportSummary) {
        val totalTests: Map<String, Int> = getRecursively(data.getChildren(), data.getChildren()).filter { !it.isContainer }.distinct().groupingBy { it.status }.eachCount()
        summary.passed = getCount(totalTests, TestExecutionResult.Status.SUCCESSFUL.toString())
        summary.failed = (getCount(totalTests, TestExecutionResult.Status.FAILED.toString())
                + getCount(totalTests, TestExecutionResult.Status.ABORTED.toString()))
        summary.skipped = getCount(totalTests, "SKIPPED")
        summary.total = summary.failed + summary.passed + summary.skipped
    }

    private fun buildIndividualParentData(data: TestReportData) {
        data.getChildren()?.let { calculateTestData(data, it) }
    }

    private fun calculateTestData(data: TestReportData, children: Set<TestReportData>) {
        children.filter { it.isContainer }.forEach { it.getChildren()?.let { it1 -> calculateTestData(it, it1) } }
        val individual = children.filter { !it.isContainer }.groupBy { it -> it.status }
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

    private fun getRecursively(parent: Set<TestReportData>?, children: Set<TestReportData>?): Set<TestReportData> {
        if (children != null) {
            return children.flatMap { getRecursively(children, it.getChildren()) }.toHashSet()
        }
        return parent!!
    }

    private fun reportToDetailsMapper(data: TestReportData, summaryMap: Map<String, TestReportSummary>): TestReportDetails {
        val details = TestReportDetails()
        details.result = data.getChildren()
        details.status = data.failed == 0
        details.summary = summaryMap[data.name]
        return details
    }


    private fun getCount(totalTests: Map<String, Int>, failed: String): Int {
        return Optional.ofNullable(totalTests[failed]).orElseGet { 0 }
    }

    fun getSummary(): Stream<TestReportSummary?> {
        return StreamSupport.stream(resultRepository.findAll().spliterator(), false)
    }

    fun getTotalCount(): Optional<Any?> {
        return resultRepository.totalCount
    }

    fun saveXmlReport(xmlString: String) {
        val legacySuite = mapper.readValue(xmlString, LegacyTestSuite::class.java)
        val report = convertToJunitReport(legacySuite)
        saveReport(setOf(report))
    }

    private fun convertToJunitReport(legacySuite: LegacyTestSuite): TestReportData {
        val reportData = TestReportData()
        reportData.name = legacySuite.name;
        reportData.duration = (legacySuite.time * 1000).toLong()
        reportData.isContainer = true
        reportData.setChildren(legacySuite.testCases.map { s -> covertCaseToReport(s) }.toHashSet())
        return reportData;

    }

    private fun covertCaseToReport(testCase: LegacyTestCase): TestReportData {
        val reportData = TestReportData()
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