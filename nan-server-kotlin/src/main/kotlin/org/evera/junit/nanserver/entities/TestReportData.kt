package org.evera.junit.nanserver.entities

import org.junit.platform.engine.TestExecutionResult

class TestReportData {
    var name: String = ""
    var duration: Long = 0
    private var children: MutableSet<TestReportData>? = null
    var status: String = TestExecutionResult.successful().toString()
    var throwable: String? = null
    var isContainer = false

    var passed = 0;
    var failed = 0;
    var skipped = 0;

    fun addChildren(data: TestReportData) {
        if (children == null) {
            children = mutableSetOf()
        }
        children!!.add(data)
    }

    fun getChildren(): Set<TestReportData>? {
        return children
    }

    fun setChildren(children: MutableSet<TestReportData>?) {
        this.children = children
    }

}