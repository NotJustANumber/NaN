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
class TestReportSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    var name: String = ""
    var duration: Long = 0

    var total: Int = 0
        get() = passed + failed + skipped

    var passed = 0

    var failed = 0

    var skipped = 0

    @CreatedDate
    @JsonProperty("created")
    var created: Date? = null

    @LastModifiedDate
    @JsonProperty("modified")
    var modified: Date? = null

}