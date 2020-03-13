package org.evera.junit.nanserver.controller

import org.evera.junit.nanserver.entities.JunitReportDetails
import org.evera.junit.nanserver.entities.JunitReportSummary
import org.evera.junit.nanserver.repository.JunitReportDetailsRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/junit/report/details")
class JunitReportDetailsEndpoint(private val detailsRepository: JunitReportDetailsRepository) {
    @GetMapping("/{id}")
    fun getDetails(@PathVariable id: Long): JunitReportDetails {
        return detailsRepository.findBySummary_Id(id).get();
    }
}