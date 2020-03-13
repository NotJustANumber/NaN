package org.evera.junit.nanserver.entities

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*

/**
 * @author ABIR
 */
@Entity
@EntityListeners(AuditingEntityListener::class)
class JunitReportSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    var name: String = ""
    var duration: Long = 0

    @JsonProperty("total_tests")
    var totalTests: Int = 0
        get() = passedTests + failedTests + skippedTests

    @JsonProperty("passed_tests")
    var passedTests = 0

    @JsonProperty("failed_tests")
    var failedTests = 0

    @JsonProperty("skipped_tests")
    var skippedTests = 0

    @CreatedDate
    @JsonProperty("created")
    var createdDate: Date? = null

    @LastModifiedDate
    @JsonProperty("modified")
    var modifiedDate: Date? = null

}