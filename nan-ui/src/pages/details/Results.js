import React, { useState, useEffect } from "react";
import IndividualSummary from "./components/IndividualSummary";
import TestGroup from "./components/TestsGroup";
import Axios from "axios";

function ResultDetails() {
  const [results, setResults] = useState([]);

  useEffect(() => {
    Axios.get("/v1/junit/report/details/1").then(s => setResults(s.data));
  }, []);

  const summary = results?.summary;

  return (
    <div className="Report-Summary tracking-tight">
      <div className="container mx-auto my-10">
        <div className="header-area flex items-center">
          <div className="test-name w-6/12">
            <div className="text-indigo-600 font-medium text-2xl">
              {summary?.name}
            </div>
            <IndividualSummary summary={summary}></IndividualSummary>
          </div>
          <div className="success-rate w-6/12">
            <div className="text-xl  text-teal-600">
              98% <span className="text-base">Success rate</span>
            </div>
            <div className="progress-bard h-4 mt-4 rounded-md border border-gray-400">
              <div className="fill h-4 rounded-md bg-gray-600 w-8/12"></div>
            </div>
          </div>
        </div>

        <TestGroup results={results}></TestGroup>
      </div>
    </div>
  );
}

export default ResultDetails;
