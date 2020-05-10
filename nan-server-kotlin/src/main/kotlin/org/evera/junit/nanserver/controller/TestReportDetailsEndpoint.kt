package org.evera.junit.nanserver.controller

import org.evera.junit.nanserver.entities.TestReportDetails
import org.evera.junit.nanserver.repository.TestReportDetailsRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/test/report/details")
class TestReportDetailsEndpoint(private val detailsRepository: TestReportDetailsRepository) {
    @GetMapping("/{id}")
    fun getDetails(@PathVariable id: Long): TestReportDetails {
        return detailsRepository.findTop1BySummary_IdOrderByCreatedDateDesc(id).get()
    }

    @GetMapping("/{id}/history")
    fun getHistory(@PathVariable id: Long): List<Any?>? {
        return detailsRepository.history(id)
    }
}