import React from "react";
import {
  TotalTestsIcon,
  FailIcon,
  DurationIcon,
} from "../../../components/Icons";

function IndividualSummary(props) {
  const summary = props.summary;
  return (
    <div>
      <div className="summary-area my-1 flex justify-start">
        <div className="test-unit-summary flex items-center mr-4">
          <TotalTestsIcon className="mr-1"></TotalTestsIcon>
          <div className="text-gray-600 text-center text-lg">
            {summary?.total}
          </div>
          <div className="text-gray-600 text-sm mx-1">Tests</div>
        </div>

        <div className="test-unit-summary flex items-center mr-4">
          <FailIcon className="mr-1"></FailIcon>
          <div className="text-gray-600 text-center text-lg">
            {summary?.failed}
          </div>
          <div className="text-gray-600 text-sm mx-1">Failed</div>
        </div>

        <div className="test-unit-summary flex items-center mr-4">
          <DurationIcon className=" text-gray-500 mr-1"></DurationIcon>
          <div className="text-gray-600 text-center text-lg">
            {summary?.duration}
          </div>
          <div className="text-gray-600 text-sm mx-1">Seconds</div>
        </div>
      </div>
    </div>
  );
}
export default IndividualSummary;
