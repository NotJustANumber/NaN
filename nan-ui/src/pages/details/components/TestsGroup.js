import React from "react";
import TestResult from "./TestResult";

function TestGroup(props) {
  return (
    <div className="mt-10 w-11/12">
      <div className="header flex p-5 text-gray-600 flex justify-between items-center">
        <div className="column-1 w-6/12 text-xl">Feature</div>
        <div className="count-header w-6/12 flex">
          <div className="w-2/12">Total</div>
          <div className="w-2/12">Passed</div>
          <div className="w-2/12">Failed</div>
          <div className="w-2/12">Skipped</div>
          <div className="w-2/12">Duration</div>
        </div>
      </div>
      <div className="result-section  bg-white border shadow-lg   rounded-md">
        {props?.results?.resultString?.map(s => (
          <TestResult result={s} key={s.name}></TestResult>
        ))}
      </div>
    </div>
  );
}

export default TestGroup;
